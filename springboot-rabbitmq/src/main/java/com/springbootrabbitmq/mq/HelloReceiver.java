package com.springbootrabbitmq.mq;

import com.alibaba.fastjson.JSON;
import com.springbootrabbitmq.entity.MessageEntity;
import com.springbootrabbitmq.socket.Socket;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Iterator;

@Component
@RabbitListener(queues = "AQueue")   // 路由键
public class HelloReceiver {

    @RabbitHandler
    public void process(String messageJson) throws InterruptedException, IOException {
        System.out.println("消费者收到消息: " + messageJson);
        Thread.sleep(((int) (Math.random() * 5000)) + 1000);//休眠当前线程、模仿业务操作
        //将消息推送给指定的socket客户端
        MessageEntity message = JSON.parseObject(messageJson, MessageEntity.class);
        sendMessage(message);
    }
// 发送消息
    private void sendMessage(MessageEntity entity) throws IOException {
        Iterator<Socket> its = Socket.vector.iterator();
        while (its.hasNext()) {
            Socket socket = its.next();
            if (socket.getUsername().equals(entity.getTo())) {
                String message = entity.getFrom() + "给您发送：" + entity.getContent();
                socket.getSession().getBasicRemote().sendText(message);
                break;
            }
        }
    }
}
