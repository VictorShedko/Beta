package by.victor.beta.service.util.mail;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

class MailSessionCreator {
    static final String EMAIL_BUNDLE_NAME = "C:\\Users\\ACER\\Documents\\Beta\\src\\main\\resources\\mailparameters.properties";

    Session createSession() {
        File file = new File(EMAIL_BUNDLE_NAME);
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(file));
        } catch (IOException e) {
            throw new ExceptionInInitializerError();
        }

        final String username = properties.getProperty("mail.user.name");
        final String password = properties.getProperty("mail.user.password");
        final String smtpPort = properties.getProperty("mail.smtp.port");
        final String smtpHost = properties.getProperty("mail.smtp.host");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", smtpPort);

        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", smtpHost);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.socketFactory.port", smtpPort);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.quitwait", "false");
        return Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

    }
}
