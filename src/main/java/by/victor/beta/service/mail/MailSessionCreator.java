package by.victor.beta.service.mail;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;
import java.util.ResourceBundle;

public class MailSessionCreator {
    public static final String EMAIL_BUNDLE_NAME = "mailparameters";

    public Session createSession() {
        ResourceBundle connectionInfo = ResourceBundle.getBundle(EMAIL_BUNDLE_NAME);
        final String username = connectionInfo.getString("mail.user.name");
        final String password = connectionInfo.getString("mail.user.password");
        final String smtpPort=connectionInfo.getString("mail.smtp.port");
        final String smtpHost=connectionInfo.getString(  "mail.smtp.host");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port",smtpPort);

// загрузка параметров почтового сервера в свойства почтовой сессии

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
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

    }
}
