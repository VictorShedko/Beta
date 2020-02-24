package by.victor.beta.service.mail;

import by.victor.beta.entity.User;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailServiceThread extends Thread {
    private static final Logger logger = LogManager.getLogger(MailServiceThread.class);
    private static Session mailSession;

    private String mailBody;
    private String email;

    public MailServiceThread(String mailBody, String email) {
        this.mailBody = mailBody;
        this.email = email;
    }

    @Override
    public void run() {
        try {
            if(mailSession==null){
                init();
            }

            Message message = new MimeMessage(mailSession);
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Cleaning service");

            message.setText(mailBody);

            Transport.send(message);

            logger.log(Level.INFO, "email message " + message + " receiver:" + email);

        } catch (MessagingException e) {
            logger.log(Level.ERROR, "send message error", e);
            throw new RuntimeException(e);//todo
        }

    }

    public static void init() {

        MailSessionCreator creator = new MailSessionCreator();
        mailSession = creator.createSession();
    }

    public static void sendMessage(User receiver, String text) {

        MailServiceThread mailServiceThread=new MailServiceThread(text, receiver.getEmail());
        mailServiceThread.start();
    }
}
