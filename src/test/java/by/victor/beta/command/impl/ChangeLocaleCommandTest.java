package by.victor.beta.command.impl;

import by.victor.beta.command.AttributeName;
import by.victor.beta.command.RequestSessionContent;
import by.victor.beta.command.Router;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ChangeLocaleCommandTest {
    private ChangeLocaleCommand command;
    @Mock
    private RequestSessionContent content;

    @BeforeTest
    void setup(){
        MockitoAnnotations.initMocks(this);
        command=new ChangeLocaleCommand();
    }
    @DataProvider(name = "content")
    public Object[][] content() {
        return new Object[][]{
                {"be_BY","en_EN"},
                {"en_EN","be_BY"},
                {"asd", "be_BY"}
        };
    }

    @Test(dataProvider = "content")
    public void testExecute(String oldLocale,String newLocale) {
        Mockito.when(content.getSessionAttribute(AttributeName.LOCALE)).thenReturn(oldLocale);
        Router router=command.execute(content);
        Mockito.verify(content).setSessionAttribute(AttributeName.LOCALE,newLocale);
        Assert.assertEquals(router.getPagePath(),"jsp/common/userMenu.jsp");
    }
}