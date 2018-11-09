package com.springbootmq.controller;

import com.springbootmq.config.HelloSender1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rabbit")
public class RabbitTest {

    @Autowired
    private HelloSender1 helloSender1;

    @GetMapping("/hello")
    public void hello() {
        helloSender1.send();
    }
}