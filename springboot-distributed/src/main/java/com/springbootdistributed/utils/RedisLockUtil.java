package com.springbootdistributed.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author ShengGuang.Ye
 * @version V1.0
 * @Description: Redis分布式锁
 * @date 2019/1/8 16:58
 */
public class RedisLockUtil {

    private int expireTime = 60 * 1000;

    private int outTime = 5 * 1000;

    private String lockKey;

    private int DEFAULT_ACQUIRY_RESOLUTION_MILLIS = 100;

    // true则当前服务器拿到了redis的分布式锁  false：没有
    private boolean locked;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 获得某个事件的分布式锁
     *
     * @return
     * @throws InterruptedException
     */
    public boolean lock() throws InterruptedException {
        int timeout = outTime;
        while (timeout >= 0) {
            // 1.拿到锁
            String expireTimeStr = String.valueOf(System.currentTimeMillis() + (expireTime + 1));   // 独占锁有效时间
            if ((locked = setNx(expireTimeStr))) {    // 是否拿到锁
                return true;
            }
            // 2.未拿到锁则判断锁有效时间是否超时
            String redisLockValue = get(lockKey); // redis里锁的有效时间
            if (redisLockValue != null && Long.parseLong(redisLockValue) < System.currentTimeMillis()) {    // Redis锁有效时间小于当前时间则锁已经超时
                // 其它线程和当前线程同事进入此代码，不用急，Redis是单线程的
                // 假设A线程执行了getSet，那么B在执行的时候，也会成功，但是拿到的旧锁有效时间是A线程设置的，和之前拿到的redisLockValue不一样，那么B拿不到锁的占有者
                String oldLockValue = getSet(expireTimeStr);    // 重设新置Redis锁,并获得旧锁有效时间
                if ((locked = (oldLockValue != null && oldLockValue.equals(redisLockValue)))) {
                    return true;
                }
            }
            timeout -= DEFAULT_ACQUIRY_RESOLUTION_MILLIS;
            Thread.sleep(DEFAULT_ACQUIRY_RESOLUTION_MILLIS);
        }
        return false;
    }

    /**
     * 解开Redis锁
     */
    public void unlock() {
        if (locked) {
            redisTemplate.execute((RedisCallback<Boolean>) conn -> {
                Long num = conn.del(new StringRedisSerializer().serialize(lockKey));
                conn.close();
                return num > 0;
            });
            locked = false;
        }
    }

    /**
     * 实例化Redis锁
     *
     * @param redisTemplate
     * @param lockKey
     */
    public RedisLockUtil(RedisTemplate<String, Object> redisTemplate, String lockKey) {
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey + "_lock";
    }

    /**
     * 实例化Redis锁
     *
     * @param redisTemplate
     * @param lockKey
     */
    public RedisLockUtil(RedisTemplate<String, Object> redisTemplate, String lockKey, int expireTime) {
        this(redisTemplate, lockKey);
        this.expireTime = expireTime;
    }

    /**
     * 实例化Redis锁
     *
     * @param redisTemplate
     * @param lockKey
     */
    public RedisLockUtil(RedisTemplate<String, Object> redisTemplate, String lockKey, int expireTime, int outTime) {
        this(redisTemplate, lockKey, expireTime);
        this.outTime = outTime;
    }

    /**
     * 设置锁
     *
     * @param expireTime
     * @return true：获得锁，false：锁被占用
     */
    private boolean setNx(String expireTime) {
        return redisTemplate.execute((RedisCallback<Boolean>) conn -> {
            StringRedisSerializer serializer = new StringRedisSerializer();
            Boolean bol = conn.setNX(serializer.serialize(lockKey), serializer.serialize(expireTime));
            conn.close();
            return bol;
        });
    }

    /**
     * 根据Key获取Redis字符串数
     *
     * @param key
     * @return
     */
    public String get(String key) {
        return redisTemplate.execute((RedisCallback<String>) conn -> {
            StringRedisSerializer serializer = new StringRedisSerializer();
            String value = serializer.deserialize(conn.get(serializer.serialize(key)));
            conn.close();
            return value;
        });
    }

    public Boolean set(String key, Object value) {
        return redisTemplate.execute((RedisCallback<Boolean>) conn -> {
            StringRedisSerializer serializer = new StringRedisSerializer();
            Boolean bool = conn.set(serializer.serialize(key), serializer.serialize(value.toString()));
            conn.close();
            return bool;
        });
    }

    /**
     * 重新设置Redis锁有效时间并返回旧的有效时间
     *
     * @param expireTime
     * @return 返回设置之前的有效时间
     */
    private String getSet(String expireTime) {
        return redisTemplate.execute((RedisCallback<String>) conn -> {
            StringRedisSerializer serializer = new StringRedisSerializer();
            String value = serializer.deserialize(conn.getSet(serializer.serialize(lockKey), serializer.serialize(expireTime)));
            conn.close();
            return value;
        });
    }

}
