package com.cosmoIntl.LiquibaseTest1.service;

import com.cosmoIntl.LiquibaseTest1.entity.EmailDetail;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@RequiredArgsConstructor
@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public String sendSimpleMail(EmailDetail emailDetail) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        try {
            mailMessage.setFrom(sender);
            mailMessage.setTo(emailDetail.getRecipient());
            mailMessage.setSubject(emailDetail.getSubject());
            mailMessage.setText(emailDetail.getMsgBody());

            javaMailSender.send(mailMessage);
            return "Message sent successfully";
        }catch (Exception e){
            log.info("Error sending email: {}", e.getMessage());
            return e.getMessage();
        }
    }

    public String sendMailWithAttachment(EmailDetail emailDetail) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
        mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setTo(emailDetail.getRecipient());
        mimeMessageHelper.setSubject(emailDetail.getSubject());
        mimeMessageHelper.setText(emailDetail.getMsgBody());

        FileSystemResource file = new FileSystemResource(new File(emailDetail.getAttachment()));

        mimeMessageHelper.addAttachment(file.getFilename(), file);
        javaMailSender.send(mimeMessage);
        return "Message sent successfully with attachment";

    }catch(Exception e) {
        return e.getMessage();}
    }
}
