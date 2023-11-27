package com.lucasengcomp.junit.blog.negocio;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

public class EmailSendingManager {

    void enviarEmail(Message message) {
        try {
            Email email = new SimpleEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("lucasengcomp", ""));
            email.setSSLOnConnect(true);
            email.setFrom("lugalim@gmail.com");
            email.setSubject(message.getSubject());
            email.setMsg(message.getContent());
            email.addTo(message.getSubject());
            email.send();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
