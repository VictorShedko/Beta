package by.victor.beta;

import by.victor.beta.entity.User;
import by.victor.beta.repository.impl.TimeRepository;
import by.victor.beta.service.mail.MailService;
import javax.mail.*;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;

public class Test {

    @org.testng.annotations.Test
    public void lol() {
        Date date=new Date();
        Timestamp stamp=new Timestamp(date.getTime());
        Date newdate=new Date(stamp.getTime());
        MailService.init();
        MailService service=new MailService();
        service.sendRegistrationNotify(new User());
    }

    @org.testng.annotations.Test
    public static void dd() {
        Properties props = new Properties();
        props.put("mail.smtp.host" , "smtp.gmail.com");
        props.put("mail.stmp.user" , "bandarlogvictor@gmail.com");

        //To use TLS
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.password", "iHearUouKaa2020");
        //To use SSL
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");


        Session session  = Session.getDefaultInstance( props ,     new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("bandarlogvictor@gmail.com", "iHearUouKaa2020");
            }
        });


        String to = "vitoreshedko@gmail.com";
        String from = "bandarlogvictor@gmail.com";
        String subject = "Testing...";
        Message msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(from));
            msg.setRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            msg.setSubject(subject);
            msg.setText("Working fine..!");
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.googlemail.com","bandarlogvictor@gmail.com", "iHearUouKaa2020");
            transport.send(msg);
            System.out.println("fine!!");
        }
        catch(Exception exc) {
            System.out.println(exc);
        }
    }
}