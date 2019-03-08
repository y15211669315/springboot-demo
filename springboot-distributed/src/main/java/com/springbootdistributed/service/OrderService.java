package com.springbootdistributed.service;

import com.springbootdistributed.entity.Order;
import com.springbootdistributed.repo.OrderRepo;
import com.springbootdistributed.repo.RedisRepo;
import com.springbootdistributed.utils.RedisLockUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class OrderService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private RedisRepo redisRepo;

    /**
     * 下订单
     *
     * @param request
     * @return
     */
    public String submitOrder(HttpServletRequest request) {
        RedisLockUtil orderLock = new RedisLockUtil(redisTemplate, "order");    // 获得订单锁实例
        try {
            if (orderLock.lock()) { // 开启分布式锁
                if (getInventory() < 1) {
                    orderLock.unlock();
                    return "不好意思您来晚了，已经卖完了";    // 库存是否还有货
                }
                redisRepo.decrement("inventory"); // 库存减1
                Order order = orderRepo.saveAndFlush(new Order());
                orderLock.unlock(); // 解锁分布式锁
                return "恭喜你下单成功！您的订单号：" + order.getTime();
            } else {
                return "访问的用户太多啦，请稍后再试哦！";
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "系统异常";
    }

    public String reInventory() {
        redisRepo.set("inventory", 10);
        return "重置成功！";
    }

    /**
     * 获得redis库存数量
     * @return
     */
    public int getInventory() {
        Object inventory = redisRepo.get("inventory");
        return inventory != null ? Integer.parseInt(inventory.toString()) : 0;
    }
}
