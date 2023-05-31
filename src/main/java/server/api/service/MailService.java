package server.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import server.api.config.MailConfig;
import server.api.model.SendMailRequest;
import server.api.util.VerifyCodeGenerateUtil;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Service
public class MailService {
    @Autowired
    private MailConfig mailConfig;
    @Autowired
    TemplateEngine templateEngine;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    private JavaMailSenderImpl mailSender;

    private final Logger LOGGER = LoggerFactory.getLogger(MailService.class);

    @PostConstruct
    private void init() {
        mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailConfig.getHost());
        mailSender.setPort(mailConfig.getPort());
        mailSender.setUsername(mailConfig.getUsername());
        mailSender.setPassword(mailConfig.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", mailConfig.getProtocol());
        props.put("mail.smtp.auth", mailConfig.isAuthEnabled());
        props.put("mail.smtp.starttls.enable", mailConfig.isStarttlsEnabled());
    }

    //獲取驗證碼
    public void sendVerifyCode(SendMailRequest request, HttpSession session){
           try{
               MimeMessage message = mailSender.createMimeMessage();
               MimeMessageHelper messageHelper = new MimeMessageHelper(message,true,"UTF-8");
               messageHelper.setFrom(mailConfig.getUsername());
               messageHelper.setTo(request.getReceivers());
               messageHelper.setSubject(request.getSubject());
               Context context = new Context();
               context.setVariable("content",request.getContent());
               context.setVariable("name",request.getName());
               //隨機產生6位數驗證碼
               String verifyCode = VerifyCodeGenerateUtil.generateVerifyCode();
               context.setVariable("verifyCode",verifyCode);
               //將驗證碼存入redis
               stringRedisTemplate.opsForValue().set("verifyCode",verifyCode,1, TimeUnit.MINUTES);
               String emailContent = templateEngine.process("verifyCode",context);
               messageHelper.setText(emailContent,true);
               try {
                   mailSender.send(message);
               } catch (MailAuthenticationException e) {
                   LOGGER.error(e.getMessage());
               } catch (Exception e) {
                   LOGGER.warn(e.getMessage());
               }
           }catch (Exception e){
               LOGGER.error(e.getMessage());
           }
    }
    public void sendMail(SendMailRequest request) {
        //SimpleMailMessage
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(mailConfig.getUsername());
//        message.setTo(request.getReceivers());
//        message.setSubject(request.getSubject());
//        message.setText(request.getContent());

        //MimeMessage
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message,true,"UTF-8");
            messageHelper.setFrom(mailConfig.getUsername());
            messageHelper.setTo(request.getReceivers());
            messageHelper.setSubject(request.getSubject());
            Context context = new Context();
            context.setVariable("content",request.getContent());
            context.setVariable("name",request.getName());
            context.setVariable("email", Arrays.toString(request.getReceivers()));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String registrationDate = dateFormat.format(new Date());
            context.setVariable("registrationDate",registrationDate);
            String emailContent = templateEngine.process("signUpSuccess",context);
            messageHelper.setText(emailContent,true);
            try {
                mailSender.send(message);
            } catch (MailAuthenticationException e) {
                LOGGER.error(e.getMessage());
            } catch (Exception e) {
                LOGGER.warn(e.getMessage());
            }
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }




    }


}
