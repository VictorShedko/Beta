package by.victor.beta.validator;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Date;

import static org.testng.Assert.*;

public class ValidatorTest {
    private Validator validator;

    @BeforeTest
    public void setup(){
        validator=new Validator();
    }

    @DataProvider(name = "regForms")
    public Object[][] regForms(){
        return new Object[][]{
                {"aaaaaaaa","correctpas1","correcrlogin","123@gmail.com",true},
                {"#aaaaaa","correctpas1","correcrlogin","123@gmail.com",false},
                {"aaaaaaa","passsssss","correcrlogin","123@gmail.com",false},
                {"aaaaaaa","passsssss1","correcrlogin","@gmail.com",false}
        };
    }

    @Test(dataProvider = "regForms")
    public void testIsValidRegistrationForm(String username, String password, String login,String email,
                                            boolean expected) {
        boolean actual=validator.isValidRegistrationForm(username,password,login,email);
        Assert.assertEquals(expected,actual);

    }

    @DataProvider(name = "credit")
    public Object[][] credit(){
        return new Object[][]{
                {"5000",true},
                {"50 00",false},
                {"0",false},
                {"-50",false}
        };
    }

    @Test(dataProvider = "credit")
    public void testIsValidCreditSum(String sumAsString,boolean expected) {
        boolean actual=validator.isValidCreditSum(sumAsString);
        Assert.assertEquals(actual,expected);
    }

    @DataProvider(name = "orderForm")
    public Object[][] orderForm(){
        return new Object[][]{
                {new Date(),new Date((new Date()).getTime()+10000000),"some address 12","12",false},
                {new Date((new Date()).getTime()+10000000),new Date((new Date()).getTime()+10000000),
                        "some address 12","12",false},
                {new Date((new Date()).getTime()+10000000),new Date((new Date()).getTime()+100000000),
                        "some address 12","12",false},
                {new Date((new Date()).getTime()+10000000),new Date((new Date()).getTime()+10100000),
                        "some address 12","-12",false},
                {new Date((new Date()).getTime()+10000000),new Date((new Date()).getTime()+10100000),
                        "some address 12","12",true},
        };
    }

    @Test(dataProvider = "orderForm")
    public void testIsValidOrderForm(Date start, Date end, String address, String description,String sum,boolean expected) {
        boolean actual=validator.isValidOrderForm(start, end,  address, description,sum);
        Assert.assertEquals(expected,actual);
    }
}