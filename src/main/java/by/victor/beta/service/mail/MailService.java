package by.victor.beta.service.mail;

import by.victor.beta.entity.User;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public enum  MailService {
    instance;
    private static final Logger logger= LogManager.getLogger(MailService.class);

    private static Session mailSession;

    MailService() {
        init();
    }

    private void sendMessage(String mailBody, String email){
        try {

            Message message = new MimeMessage(mailSession);
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Cleaning service");
            message.setText(mailBody);

            Transport.send(message);

            logger.log(Level.INFO,"");

        } catch (MessagingException e) {
            logger.log(Level.ERROR,"send message error",e);
            throw new RuntimeException(e);//todo
        }

    }
    public static void init(){
        MailSessionCreator creator=new MailSessionCreator();
        mailSession=creator.createSession();
    }

    public void sendMessage(User receiver,String text){
        sendMessage(text,receiver.getEmail());
    }
}