package by.victor.beta.service.util;

import by.victor.beta.service.ServiceException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class HashTest {

    @DataProvider(name ="passAndSalt" )
    public Object[][] passAndSalt() {
        return new Object[][]{
                {"pass1","salt1","pass2","salt2"},
                {"pass1","salt1","pass2","salt1"},
                {"pass1","salt1","pass1","salt2"}
        };
    }






         @Test(dataProvider = "passAndSalt")
        public void alwaysEqualTest(String password1, String salt1, String password2, String salt2) throws ServiceException {
            byte[] hash1= HashService.INSTANCE.getHash(password1,salt1.getBytes());
            byte[] hash2= HashService.INSTANCE.getHash(password2,salt2.getBytes());
            Assert.assertNotEquals(hash1,hash2);
        }

    @Test(dataProvider = "passAndSalt")
    public void passwordDurability(String password1,String salt1,String password2,String salt2) throws ServiceException {
        byte[] hash1= HashService.INSTANCE.getHash(password1,salt1.getBytes());
        byte[] hash2= HashService.INSTANCE.getHash(password1,salt1.getBytes());
        Assert.assertEquals(hash1,hash2);
    }

    @Test
    public void unpredictabilitySalt(){
        byte[] hash1= HashService.INSTANCE.getNewSalt();
        byte[] hash2= HashService.INSTANCE.getNewSalt();
        Assert.assertNotEquals(hash1,hash2);
    }


}