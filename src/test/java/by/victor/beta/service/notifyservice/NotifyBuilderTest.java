package by.victor.beta.service.notifyservice;

import by.victor.beta.entity.Order;
import by.victor.beta.entity.User;

public class NotifyBuilderTest {
    @org.testng.annotations.Test
    public void lol() {
        NotifyMessageBuilder notifyMessageBuilder=new NotifyMessageBuilder();
        User user=new User();
        user.setUsername("andre");
        Order order=new Order();
        order.setAddress("LA");
        order.setDescription("dskfksf");
        order.setPrice(10);
        String text=notifyMessageBuilder.orderRefuseMessage(user,order);
        text.length();
    }
}
