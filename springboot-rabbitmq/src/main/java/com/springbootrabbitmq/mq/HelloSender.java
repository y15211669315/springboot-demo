package com.springbootrabbitmq.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author ShengGuang.Ye
 * @ven V1.0
 * @Description: 生产者
 * @date 2018/8/21 14:44
 */
@RestController
@RequestMapping("/test")
public class HelloSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @GetMapping
    public int send(@RequestParam String messageJson) {
        System.out.println("生产者收到内容: " + messageJson);
        this.rabbitTemplate.convertAndSend("AQueue", messageJson);
        return 200;
    }

}
