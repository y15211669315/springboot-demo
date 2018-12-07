package com.springbootmq;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootMqApplication {

    /**
     * 声明一个消息交换机1
     *
     * @return
     */
    @Bean
    public Queue helloQueue() {
        return new Queue("myMQ", true, false, false);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMqApplication.class, args);
    }
}
