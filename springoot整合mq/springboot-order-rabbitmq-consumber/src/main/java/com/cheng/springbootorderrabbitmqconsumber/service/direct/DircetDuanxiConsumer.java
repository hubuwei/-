package com.cheng.springbootorderrabbitmqconsumber.service.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = {"duanxi.direct.queue"})
public class DircetDuanxiConsumer {
    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println("duanxin direct---接收到了订单信息是：->" + message);
    }
}
