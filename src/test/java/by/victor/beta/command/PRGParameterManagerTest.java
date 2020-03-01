package by.victor.beta.command;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PRGParameterManagerTest {
    PRGParameterManager manager;
    @BeforeClass
    public void setUp() {
        manager=new PRGParameterManager();
    }

    @DataProvider(name = "pagePath")
    public Object[][] data() {
        return new Object[][]{
                {PagePath.PRG_RESULT,AttributeName.FEEDBACK,PageContentKey.INVALID_LOGIN_OR_PASSWORD,
                        "cleaning?command=to_result&feedback=feedback.invalid.passwordorlogin"},
                {PagePath.PRG_RESULT,AttributeName.COMMAND_RESULT,PageContentKey.SUCCESSFULLY,
                        "cleaning?command=to_result&commandResult=feedback.success"},
                {PagePath.PRG_RESULT,AttributeName.FEEDBACK,PageContentKey.INVALID_ADDRESS,
                        "cleaning?command=to_result&feedback=feedback.invalid.address"}
               };
    }


    @Test(dataProvider = "pagePath")
    public void testAddParameter(String path,String key,String value,String excepted) {
        String actual=manager.addParameter(path,key,value);
        Assert.assertEquals(actual,excepted);
    }
}