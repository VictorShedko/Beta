package by.victor.beta.service;

import by.victor.beta.entity.User;
import by.victor.beta.service.impl.NotifyService;
import by.victor.beta.service.impl.OrderService;
import by.victor.beta.service.impl.UserService;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@PrepareForTest({UserService.class,OrderService.class,NotifyService.class})
public class ServiceFacadeTest {

    UserService userService=null;
    @Mock
    OrderService orderService;
    @Mock
    NotifyService notifyService;

    @BeforeMethod
    public void setUpMethod() {
        MockitoAnnotations.initMocks(this);
    }

    @DataProvider(name = "names")
    public Object[][] names() {
        return new Object[][]{
                {"be_BY",true,"  "},
                {"test",false,"test"},
                {"test1",true,"   "}
        };
    }

    @Test(dataProvider = "names")
    public void testFindUserByUsername(String username,boolean isException,String login) {
        try {

            PowerMockito.whenNew(UserService.class).withNoArguments().thenReturn(userService);
            PowerMockito.whenNew(OrderService.class).withAnyArguments().thenReturn(orderService);
            PowerMockito.whenNew(NotifyService.class).withAnyArguments().thenReturn(notifyService);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            UserService ser=new UserService();
            User user=ServiceFacade.INSTANCE.findUserByUsername(username);
            Mockito.verify(userService.findUserByUsername(username));
            Assert.assertEquals(user.getLogin(),login);
        } catch (ServiceException e) {
            Assert.assertTrue(isException);
        }


    }

    @Test
    public void testResendVerifyMail() {
    }

    @Test
    public void testEmailVerify() {
    }

    @Test
    public void testLogin() {
    }

    @Test
    public void testRegisterUser() {
    }

    @Test
    public void testDeleteUser() {
    }

    @Test
    public void testShowUserByRole() {
    }

    @Test
    public void testShowUserByStatus() {
    }

    @Test
    public void testValidateUser() {
    }

    @Test
    public void testCreateOrder() {
    }

    @Test
    public void testAcceptOrder() {
    }

    @Test
    public void testCancelOrderByCustomer() {
    }

    @Test
    public void testRefuseOrderByExecutor() {
    }

    @Test
    public void testShowUserDocument() {
    }

    @Test
    public void testCheckDocument() {
    }

    @Test
    public void testShowNotifyList() {
    }

    @Test
    public void testShowCustomerOrderHistory() {
    }

    @Test
    public void testShowExecutorOrderHistory() {
    }

    @Test
    public void testShowAvailableOrder() {
    }

    @Test
    public void testUploadPhoto() {
    }

    @Test
    public void testCreditAccount() {
    }

    @Test
    public void testAddNotify() {
    }

    @Test
    public void testAddDocument() {
    }

    @Test
    public void testShowAllUserByRole() {
    }

    @Test
    public void testShowUserDocuments() {
    }

}