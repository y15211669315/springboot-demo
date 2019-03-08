package com.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ShengGuang.Ye
 * @version V1.0
 * @Description: 队列配置
 * @date 2018/8/21 14:41
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue Queue() {
        return new Queue("AQueue", false);
    }


    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("AExchange",false,false);//交换器名称、是否持久化、是否自动删除
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("ARoutingKey");  //绑定队列交换器路由
    }

}
