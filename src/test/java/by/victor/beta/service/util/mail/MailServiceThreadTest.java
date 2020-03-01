package by.victor.beta.service.util.mail;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MailServiceThreadTest {

    @Test
    public void testSendMessage() {
        MailServiceThread mailServiceThread = new MailServiceThread("<a href=\"http://localhost:8085/clean/cleaning\">ccakmrf</a>", "vchedko2000@mail.ru");
        mailServiceThread.run();
        System.out.println("<a href=\"http://localhost:8085/clean/cleaning\">");
    }
}