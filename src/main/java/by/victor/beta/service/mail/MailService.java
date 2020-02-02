package by.victor.beta.service.mail;

import by.victor.beta.entity.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailService {


    private static Session mailSession;
    public void sendRegistrationNotify(User user){

        try {

            Message message = new MimeMessage(mailSession);
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("vitoreshedko@gmail.com"));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler,"
                    + "\n\n No spam to my email, please!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
    public static void init(){
        MailSessionCreator creator=new MailSessionCreator();
        mailSession=creator.createSession();
    }
}
