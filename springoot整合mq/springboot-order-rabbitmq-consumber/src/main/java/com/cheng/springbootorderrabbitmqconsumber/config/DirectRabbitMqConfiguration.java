package com.cheng.springbootorderrabbitmqconsumber.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectRabbitMqConfiguration {
    // 1: 声明注册direct模式的交换机
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("direct_order_exchange",true,false);
    }
    // 2:声明队列 sms.direct.queue email.direct.queue，duanxin.direct.queue
    @Bean
    public Queue directsmsQueue(){
        return new Queue("sms.direct.queue",true);
    }
    @Bean
    public Queue directduanxiQueue(){
        return new Queue("duanxi.direct.queue",true);
    }
    @Bean
    public Queue directemailQueue(){
        return new Queue("email.direct.queue",true);
    }
    // 3:完成绑定关系（队列和交换机完成绑定关系）
    @Bean
    public Binding directsmsBingding(){
        return BindingBuilder.bind(directsmsQueue()).to(directExchange()).with("sms");
    }
    @Bean
    public Binding directemailBingding(){
        return BindingBuilder.bind(directemailQueue()).to(directExchange()).with("email");
    }
    @Bean
    public Binding directduanxiBingding(){
        return BindingBuilder.bind(directduanxiQueue()).to(directExchange()).with("duanxin");
    }
}