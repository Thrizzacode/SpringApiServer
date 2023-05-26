package server.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import server.api.model.SendMailRequest;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Service
public class MailService {

    private final Logger LOGGER = LoggerFactory.getLogger(MailService.class);

    private static final String HOST = "smtp.gmail.com";
    private static final int PORT = 587;
    private static final String PROTOCOL = "smtp";
    private static final boolean AUTH = true;
    private static final boolean ENABLED_STARTTLS = true;
    private static final String USERNAME = "mikework0814@gmail.com";
    private static final String PASSWORD = "mbgjcjqaegmmvpoe";
    private JavaMailSenderImpl mailSender;

    @PostConstruct
    private void init() {
        mailSender = new JavaMailSenderImpl();
        mailSender.setHost(HOST);
        mailSender.setPort(PORT);
        mailSender.setUsername(USERNAME);
        mailSender.setPassword(PASSWORD);
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", PROTOCOL);
        props.put("mail.smtp.auth", AUTH);
        props.put("mail.smtp.starttls.enable", ENABLED_STARTTLS);
    }

    public void sendMail(SendMailRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(USERNAME);
        message.setTo(request.getReceivers());
        message.setSubject(request.getSubject());
        message.setText(request.getContent());

        try {
            mailSender.send(message);
        } catch (MailAuthenticationException e) {
            LOGGER.error(e.getMessage());
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }

    }


}
