package com.cheng.springbootorderrabbitmqconsumber.service.topic;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "email.topic.queue",durable = "true",autoDelete = "false"),
        exchange = @Exchange(value = "topic_order_exchange",type = ExchangeTypes.TOPIC),
        key = "*.email.#"
))
public class TopicEmailConsumer {
    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println("email topic---接收到了订单信息是：->" + message);
    }
}
