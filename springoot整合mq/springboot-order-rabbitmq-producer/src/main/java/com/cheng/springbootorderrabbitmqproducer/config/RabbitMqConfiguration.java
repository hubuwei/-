package com.cheng.springbootorderrabbitmqproducer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {
    // 1: 声明注册fanout模式的交换机
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanout_order_exchange",true,false);
    }
    // 2:声明队列 sms.fanout.queue email.fanout.queue，duanxin.fanout.queue
    @Bean
    public Queue smsQueue(){
        return new Queue("sms.fanout.queue",true);
    }
    @Bean
    public Queue duanxiQueue(){
        return new Queue("duanxi.fanout.queue",true);
    }
    @Bean
    public Queue emailQueue(){
        return new Queue("email.fanout.queue",true);
    }
    // 3:完成绑定关系（队列和交换机完成绑定关系）
    @Bean
    public Binding smsBingding(){
        return BindingBuilder.bind(smsQueue()).to(fanoutExchange());
    }
    @Bean
    public Binding emailBingding(){
        return BindingBuilder.bind(emailQueue()).to(fanoutExchange());
    }
    @Bean
    public Binding duanxiBingding(){
        return BindingBuilder.bind(duanxiQueue()).to(fanoutExchange());
    }
}
