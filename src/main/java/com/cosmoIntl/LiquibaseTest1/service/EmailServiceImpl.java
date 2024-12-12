package com.cosmoIntl.LiquibaseTest1.service;

import com.cosmoIntl.LiquibaseTest1.dto.requestDTO.EmailRequestDto;
import com.cosmoIntl.LiquibaseTest1.dto.responseDTO.EmailResponseDTO;
import com.cosmoIntl.LiquibaseTest1.entity.EmailDetail;
import com.cosmoIntl.LiquibaseTest1.mapper.EmailMapper;
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
    private final EmailMapper emailMapper;

    @Value("${spring.mail.username}")
    private String sender;

    public EmailResponseDTO sendSimpleMail(EmailRequestDto emailRequestDto) {

        EmailDetail emailDetail = emailMapper.toEmailDetail(emailRequestDto);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        EmailResponseDTO emailResponseDTO = new EmailResponseDTO();
        try {
            mailMessage.setFrom(sender);
            mailMessage.setTo(emailDetail.getRecipient());
            mailMessage.setSubject(emailDetail.getSubject());
            mailMessage.setText(emailDetail.getMsgBody());

            javaMailSender.send(mailMessage);
            emailResponseDTO.setMessage("Message sent successfully");
            return emailResponseDTO;
        } catch (Exception e) {
            log.info("Error sending email: {}", e.getMessage());
            emailResponseDTO.setMessage(e.getMessage());
            return emailResponseDTO;
        }
    }

    public EmailResponseDTO sendMailWithAttachment(EmailRequestDto emailRequestDto) {

        EmailDetail emailDetail = emailMapper.toEmailDetail(emailRequestDto);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        EmailResponseDTO emailResponseDTO = new EmailResponseDTO();

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(emailDetail.getRecipient());
            mimeMessageHelper.setSubject(emailDetail.getSubject());
            mimeMessageHelper.setText(emailDetail.getMsgBody());

            FileSystemResource file = new FileSystemResource(new File(emailDetail.getAttachment()));

            mimeMessageHelper.addAttachment(file.getFilename(), file);
            javaMailSender.send(mimeMessage);
            emailResponseDTO.setMessage("Message sent successfully with attachment");
            return emailResponseDTO;

        } catch (Exception e) {
            emailResponseDTO.setMessage(e.getMessage());
            return emailResponseDTO;
        }
    }
}
