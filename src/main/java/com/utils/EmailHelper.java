package com.utils;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import java.security.Security;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @ClassName : EmailHelper
 * @Description :发送邮件
 * @Author : fmx
 * @Date: 2021-04-23 15:04
 */
public class EmailHelper {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("mail");
    private static final String sendFrom = bundle.getString("email.from");
    private static final String username = bundle.getString("username");
    private static final String password = bundle.getString("password");
    private static final String host = bundle.getString("email.host");
    private static final String port = bundle.getString("email.port");

    public static void sendEmail(String someone, String subject, String content){
        //Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        Properties properties = new Properties();
        properties.setProperty("mail.host", host);
        properties.setProperty("mail.smtp.auth", "true");

        //服务器发送不成功，读取端口，并且设置传输方式为SSL
        //服务器禁用了25的端口，所以改为465端口
        properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.port",port);
        properties.setProperty("mail.smtp.socketFactory.port",port);
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        };

        Session session = Session.getDefaultInstance(properties,authenticator);
        session.setDebug(true);
        Message message = new MimeMessage(session);
        try{
            message.setFrom(new InternetAddress(sendFrom));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(someone));
            try{
                message.setSubject(subject);
                message.setContent(content,"text/html;charset=UTF-8");
                Transport.send(message);
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String content = "Hello,This is a test email!!!!";
        EmailHelper.sendEmail("977045110@qq.com","测试",content);
    }
}
