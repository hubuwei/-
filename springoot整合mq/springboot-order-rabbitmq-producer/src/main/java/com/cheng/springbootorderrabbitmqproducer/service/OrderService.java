package com.cheng.springbootorderrabbitmqproducer.service;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void makeOrder(String userid, String productid, int num){
        // 1: 根据商品id查询库存是否充足
        // 2: 保存订单
        String order = UUID.randomUUID().toString();
        System.out.println("订单生产成功：" + order);
        // 3: 通过MQ来完成消息的分发
        // 参数1：交换机  参数2：路由key/queue队列名称 参数3：消息内容
        String exchangeName = "fanout_order_exchange";
        String routingKey = "";
        rabbitTemplate.convertAndSend(exchangeName,routingKey,order);
    }

    public void makeOrdertopic(String userid, String productid, int num){
        // 1: 根据商品id查询库存是否充足
        // 2: 保存订单
        String order = UUID.randomUUID().toString();
        System.out.println("订单生产成功：" + order);
        // 3: 通过MQ来完成消息的分发
        // 参数1：交换机  参数2：路由key/queue队列名称 参数3：消息内容
        String exchangeName = "topic_order_exchange";
        String routingkey = "com";
        rabbitTemplate.convertAndSend(exchangeName,routingkey,order);
    }

    public void makeOrderTtl(String userid, String productid, int num){
        // 1: 根据商品id查询库存是否充足
        // 2: 保存订单
        String order = UUID.randomUUID().toString();
        System.out.println("订单生产成功：" + order);
        // 3: 通过MQ来完成消息的分发
        // 参数1：交换机  参数2：路由key/queue队列名称 参数3：消息内容
        String exchangeName = "ttl_direct_exchange";
        String routingkey = "ttl";
        rabbitTemplate.convertAndSend(exchangeName,routingkey,order);
    }

    public void makeOrderTtlMessage(String userid, String productid, int num){
        // 1: 根据商品id查询库存是否充足
        // 2: 保存订单
        String order = UUID.randomUUID().toString();
        System.out.println("订单生产成功：" + order);
        // 3: 通过MQ来完成消息的分发
        // 参数1：交换机  参数2：路由key/queue队列名称 参数3：消息内容
        String exchangeName = "ttl_direct_exchange";
        String routingkey = "ttlmessage";
        // 给消息设置过期时间
        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("5000");
                message.getMessageProperties().setContentEncoding("UTF-8");
                return message;
            }
        };
        rabbitTemplate.convertAndSend(exchangeName,routingkey,order,messagePostProcessor);
    }
}
