package by.victor.beta.service.util;


import by.victor.beta.entity.util.NotifyType;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Locale;

public class NotifyMessageBuilderTest {


    private  NotifyMessageBuilder notifyMessageBuilder;

    @BeforeClass
    void setup(){
        notifyMessageBuilder=new NotifyMessageBuilder();
    }


    @DataProvider(name = "build")
    public Object[][] build(){
        Locale be=new Locale("be_BY");
        Locale en=new Locale("en_EN");
        return new Object[][]{
                {List.of("1","2"),be, NotifyType.ORDER_EXECUTION_START,""},
                {List.of("1","2","3","4","5","6","7","8","9","10","11","12","13"),
                        be,NotifyType.ORDER_ACCEPTED,""},
                {List.of("1","2"),be,NotifyType.ORDER_EXECUTION_START,""},
                {List.of("1","2"),be,NotifyType.ORDER_EXECUTION_START,""}
        };
    }

    @Test(dataProvider = "build")
    public void testBuildByPatter(List<String> strings,Locale locale,NotifyType  type,String excepted) {
       String result=notifyMessageBuilder.buildByPattern(strings,type,locale);
       Assert.assertEquals(result,excepted);
    }


    @DataProvider(name = "values")
    public Object[][] values(){
        return new Object[][]{
                {"a$b",List.of("a","b")},
                {"a$$b",List.of("a","","b")},
                {"ab",List.of("ab")},
                {"a$b$cdef$ght",List.of("a","b","cdef","fght")}
        };
    }

    @Test(dataProvider = "values")
    public void testGetNotifyValues(String str,List<String> expected) {
       List<String> result = notifyMessageBuilder.getNotifyValues(str);
        Assert.assertEquals(expected,result);
    }

}