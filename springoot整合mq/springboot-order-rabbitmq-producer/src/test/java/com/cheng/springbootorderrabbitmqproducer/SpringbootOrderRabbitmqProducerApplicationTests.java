package com.cheng.springbootorderrabbitmqproducer;

import com.cheng.springbootorderrabbitmqproducer.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootOrderRabbitmqProducerApplicationTests {
@Autowired
private OrderService orderService;
    @Test
    void contextLoads() {
        orderService.makeOrder("1","1",12);
    }
/*    @Test
    void testorderDirect() {
        orderService.makeOrderDirect("1","1",12);
    }
}*/
    @Test
    void testorderTopic() {
        orderService.makeOrdertopic("1","1",12);
    }
    @Test
    void testorderTtl() {
        for (int i = 0; i < 11; i++) {
            orderService.makeOrderTtl("1","1",12);
        }

    }

    @Test
    void testmakeOrderTtlMessage() {
        orderService.makeOrderTtlMessage("1","1",12);
    }
}
