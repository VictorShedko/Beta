package by.victor.beta.command.impl;

import by.victor.beta.entity.*;
import by.victor.beta.command.AttributeName;
import by.victor.beta.command.CommandException;
import by.victor.beta.command.RequestSessionContent;
import by.victor.beta.command.Router;
import by.victor.beta.repository.impl.OrderRepository;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.ServiceFacade;

import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.support.membermodification.MemberModifier;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.*;

class TestFacade {
    private Optional<User> optionalUser;
    public Optional<User> login(String login, String password) throws ServiceException {
        return optionalUser;
    }

    public Optional<User> getOptionalUser() {
        return optionalUser;
    }

    public void setOptionalUser(Optional<User> optionalUser) {
        this.optionalUser = optionalUser;
    }
}


@PrepareForTest({ServiceFacade.class})
public class LoginCommandTest {
    private LoginCommand command;
    private ServiceFacade facade;
    @Mock
    private RequestSessionContent content;



    @BeforeTest
    public void setup(){
        MockitoAnnotations.initMocks(this);
        command=new LoginCommand();
    }
    @DataProvider(name = "content")
    public Object[][] content() {
        User testUser=new User();
        Optional<User> userOptional=Optional.of(testUser);
        return new Object[][]{
                {"login","123","",userOptional},
                {"login","123","",userOptional},
                {"login","123","",userOptional}
        };
    }



    @Test(dataProvider = "content")
    public void testExecute(String login, String pass, String expected, Optional<User> userOptional) {
        MemberModifier.replace(MemberModifier.method(ServiceFacade.class,"login")).with(MemberModifier.method(TestFacade.class,"login"));
      //  PowerMockito.when(ServiceFacade)
        Mockito.when(content.getSessionAttribute(AttributeName.LOGIN)).thenReturn(login);
        Mockito.when(content.getSessionAttribute(AttributeName.PASSWORD)).thenReturn(pass);
        try {
           Router router= command.execute(content);
            Assert.assertEquals(router.getPagePath(),expected);
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }
}