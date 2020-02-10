package com.eternalinfo.tern.module.frame.alarm.utils;

import com.eternalinfo.tern.kit.AESKit;
import com.sun.mail.util.MailSSLSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.*;

public class MailUtil {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //@Autowired
    //private JavaMailSender mailSender;//JavaMailSender邮件发送类

    @Autowired
    private JavaMailSenderImpl mailSenderImpl;

    private String from;
    private String password;
    private String host;
    private String port;
    private String isAuth;
    private String isEnableStarttls;
    private String isRequireStarttls;


    public void simpleMailSend(String mailReceivers,String subject,String content) {
        String decodePwd = AESKit.decrypt(password);
        mailSenderImpl.setPassword(decodePwd);

        SimpleMailMessage message = new SimpleMailMessage();
        //发件人
        message.setFrom(from);
        //收件人
        message.setTo(mailReceivers);
        //抬头
        message.setSubject(subject);
        //内容
        message.setText(content);

        try {
            mailSenderImpl.send(message);
            logger.info("简单邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常！", e);
        }

    }

    public void sendMail(String mailReceivers,String subject,String content) {
        String decodePwd = AESKit.decrypt(password);

        Properties props = new Properties();
        props.put("mail.smtp.host",host);
        props.put("mail.smtp.port", port);

        props.put("mail.debug", "true");
        //props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", isAuth);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");

        try{

            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            props.put("mail.smtp.ssl.enable", "false");
            props.put("mail.smtp.ssl.socketFactory", sf);

            //设置Session对象，同时配置验证方法
            Session session = Session.getInstance(props,new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, decodePwd);
                }
            });

            //创建Message对象，并设置相关参数
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(mailReceivers));
            //设置发送信息主题.信息正文
            message.setSubject(subject);
            message.setText(content);

            //mailSenderImpl.send(message);

            //发送信息
            Transport.send(message);
            logger.info("邮件已发送，收件箱：" + mailReceivers);

        }catch(MessagingException e){
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

    }

    public void batchSendMail(List<String> receivers, String subject, String content) {

        String decodePwd = AESKit.decrypt(password);

        Properties props = new Properties();
        props.put("mail.smtp.host",host);
        props.put("mail.smtp.port", port);

        props.put("mail.debug", "true");
        //props.put("mail.transport.protocol", "smtp");

        //普通邮件 IsAuth为true, isEnableStarttls和isRequireStarttls为false，才能正常发送邮件
        props.put("mail.smtp.auth", isAuth);
        props.put("mail.smtp.starttls.enable", isEnableStarttls);
        props.put("mail.smtp.starttls.required", isRequireStarttls);

        try {
            List<Address> receiversList = new ArrayList<>();
            for(String receiver : receivers) {
                receiversList.add(new InternetAddress(receiver));
            }
            Address[] address = receiversList.toArray(new Address[receiversList.size()]);
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            props.put("mail.smtp.ssl.enable", "false");
            props.put("mail.smtp.ssl.socketFactory", sf);

            //设置Session对象，同时配置验证方法
            Session session = Session.getInstance(props,new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, decodePwd);
                }
            });

            //创建Message对象，并设置相关参数
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, address);
            //设置发送信息主题.信息正文
            message.setSubject(subject);
            message.setText(content);

            //mailSenderImpl.send(message);

            //发送信息
            Transport.send(message);
            logger.info("邮件已发送，收件箱：" + Arrays.toString(address));

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

}
