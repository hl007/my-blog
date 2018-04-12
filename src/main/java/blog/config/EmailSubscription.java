package main.java.blog.config;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


@Component
public class EmailSubscription {

    public void sendEmail(InternetAddress[] internetAddressTo,String subject,String content){

        Properties props=new Properties();
        props.setProperty("mail.transport.protocol","smtps");
        props.setProperty("mail.smtps.auth","true");
        props.setProperty("mail.smtps.host","smtp.163.com");
        props.setProperty("mail.smtps.user","18896558387@163.com");


        Authenticator authenticator=new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("18896558387@163.com","199396hl");
            }
        };

        Session mailSession= Session.getDefaultInstance(props,authenticator);
        MimeMessage message=new MimeMessage(mailSession);
        JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
        mailSender.setSession(mailSession);
        try {
            MimeMessageHelper helper=new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("18896558387@163.com");
            helper.setTo(internetAddressTo);
            helper.setSubject(subject);
            helper.setText("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "<meta charset=\"utf-8\">\n" +
                    "<title>Blog Subscription</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<h1>"+subject+"</h1>\n" +content+
                    "</body>\n" +
                    "</html>",true);
            mailSender.send(message);
        }
        catch (MessagingException e){
            e.printStackTrace();;
        }
    }
}
