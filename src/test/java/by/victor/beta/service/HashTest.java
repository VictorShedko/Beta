package by.victor.beta.service;

import by.victor.beta.service.orderservice.OrderManager;
import org.testng.Assert;

public class HashTest {
    @org.testng.annotations.Test
    public void lol() {
       HashGenerator generator=new HashGenerator();
        try {
            String result1=generator.getHash("lol");
            String result2=generator.getHash("lol");
            Assert.assertEquals(result1, result1);
        } catch (ServiceException e) {
            e.printStackTrace();
        }


    }
}