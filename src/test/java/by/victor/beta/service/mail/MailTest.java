package by.victor.beta.service.mail;



import by.victor.beta.service.impl.UserService;

import java.sql.Timestamp;
import java.util.Date;

import by.victor.beta.entity.*;
import org.testng.Assert;


public class MailTest {

        @org.testng.annotations.Test
        public void lol() {
            String str="h";
            System.out.println("he"=="he");
            System.out.println("he"==str+"e");

            Date date=new Date();
            Timestamp stamp=new Timestamp(date.getTime());
            Date newdate=new Date(stamp.getTime());
            MailService.init();

            User user=new User();
           //todo  service.sendRegistrationNotify(user);
         //   MailService.init();
         //   MailService service=new MailService();
         //   service.sendRegistrationNotify(new User());

        }
}
