package by.victor.beta.service.notifyservice;

import by.victor.beta.entity.NotifyType;
import by.victor.beta.entity.Order;
import by.victor.beta.entity.User;
import by.victor.beta.service.util.NotifyMessageBuilder;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Locale;

public class NotificationBuilderTest {
    @org.testng.annotations.Test
    public void lol() {
        NotifyMessageBuilder notifyMessageBuilder=new NotifyMessageBuilder();
        User user=new User();
        String[] s="lol$ti$pidor".split("\\$");
        List<String> v=notifyMessageBuilder.getNotifyValues("lol$ti$pidor");
        user.setUsername("andre");
        Order order=new Order();
        order.setAddress("LA");
        order.setDescription("dskfksf");
        order.setPrice(10);
        String text=notifyMessageBuilder.orderRefuseMessage(user,order);
        text.length();
    }

    @DataProvider(name = "TetrahedronList")
    public Object[][] parameterXoZTestProvider() {
        return new Object[][]{
                {List.of("1","2","3","4","5"), NotifyType.ORDER_EXECUTION_START},
        };
    }

    @Test(dataProvider = "TetrahedronList")
    public void basicRepositoryTest(List<String> strings,NotifyType type) {
        NotifyMessageBuilder notifyMessageBuilder=new NotifyMessageBuilder();
        Locale locale=new Locale("en");
        List<String> lol=notifyMessageBuilder.getNotifyValues("Alpharius$1$$1$");

        notifyMessageBuilder.buildByPatter(lol,type,locale);

    }
}
