package by.victor.beta.service.util;


import by.victor.beta.entity.*;
import by.victor.beta.service.CleanerEntityProvider;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Locale;

import static org.testng.Assert.*;

public class NotifyMessageBuilderTest {


    private  NotifyMessageBuilder notifyMessageBuilder;

    @DataProvider(name = "TetrahedronList")
    public Object[][] parameterXoZTestProvider() {
        User executor ;
        User customer ;
        Order order;
        return new Object[][]{
                //{executor,customer,order},
        };
    }

    @Test
    public void buildMessageTest(List<String> strings,NotifyType type) {
        Locale locale=new Locale("en");
        List<String> lol=notifyMessageBuilder.getNotifyValues("Alpharius$1$$1$");

        notifyMessageBuilder.buildByPatter(lol,type,locale);

    }

    @Test
    public void testBuildByPatter() {
    }

    @Test
    public void testGetNotifyValues() {
    }

    @Test
    public void testRegistrationMessage() {
    }

    @Test
    public void testAdminValidateMessage() {
    }

    @Test
    public void testOrderNotClaimedMessage() {
    }

    @Test
    public void testOrderExecutionStartMessage() {
    }

    @Test
    public void testOrderExecutionFinishToCustomer() {
    }

    @Test
    public void testOrderExecutionFinishToExecutor() {
    }

    @Test
    public void testOrderRefuseMessage() {
    }

    @Test
    public void testOrderAcceptedMessage() {
    }

    @Test
    public void testOrderCanceledMessageToExecutor() {
    }

    @Test
    public void testOrderCanceledMessageToCustomer() {
    }

    @Test
    public void testOrderCreateMessage() {
    }

    @Test
    public void testTestBuildByPatter() {
    }

    @Test
    public void testBuildEmailVerification() {
    }
}