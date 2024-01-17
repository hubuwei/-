package com.cheng.springbootorderrabbitmqproducer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class TTLRabbitMqConfiguration {
    // 1: 声明注册direct模式的交换机
    @Bean
    public DirectExchange ttldirectExchange(){
        return new DirectExchange("ttl_direct_exchange",true,false);
    }
    // 队列的过期时间
    @Bean
    public Queue directttlQueue(){
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl",5000);// 这里一定是int类型
        args.put("x-max-length",5);// 这里一定是int类型
        args.put("x-dead-letter-exchange","dead_direct_exchange");
        args.put("x-dead-letter-routing-key","dead");//fanout模式不需要配置路由key
        return new Queue("ttl.direct.queue",true,false,false,args);
    }

    @Bean
    public Queue directttlMessageQueue(){
        return new Queue("ttl.message.direct.queue",true);
    }
    // 3:完成绑定关系（队列和交换机完成绑定关系）

    @Bean
    public Binding ttlbingds(){
        return BindingBuilder.bind(directttlQueue()).to(ttldirectExchange()).with("ttl");
    }

    @Bean
    public Binding ttlmsgbingds(){

        return BindingBuilder.bind(directttlMessageQueue()).to(ttldirectExchange()).with("ttlmessage");
    }
}