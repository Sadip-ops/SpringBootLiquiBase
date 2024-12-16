package com.cosmoIntl.LiquibaseTest1.service;

import com.cosmoIntl.LiquibaseTest1.dto.requestDTO.EmailRequestDto;
import com.cosmoIntl.LiquibaseTest1.dto.responseDTO.EmailResponseDTO;
import com.cosmoIntl.LiquibaseTest1.entity.EmailDetail;
import com.cosmoIntl.LiquibaseTest1.entity.EmailTemplate;
import com.cosmoIntl.LiquibaseTest1.mapper.EmailMapper;
import com.cosmoIntl.LiquibaseTest1.repository.EmailTemplateRepository;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.File;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final EmailMapper emailMapper;
    private final Configuration freemarkerConfig;
    private final EmailTemplateRepository emailTemplateRepository;

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

    public EmailResponseDTO sendMailFromTemplate(EmailRequestDto emailRequestDto) {
        EmailDetail emailDetail = emailMapper.toEmailDetail(emailRequestDto);
        EmailResponseDTO emailResponseDTO = new EmailResponseDTO();

        try {
            String emailContent = processTemplate(emailRequestDto.getTemplateData(), "emailTemplate.ftlh");
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false);

            helper.setFrom(sender);
            helper.setTo(emailDetail.getRecipient());
            helper.setSubject(emailDetail.getSubject());
            helper.setText(emailContent, true); // Set email content as HTML

            javaMailSender.send(mimeMessage);
            emailResponseDTO.setMessage("Email sent successfully using FreeMarker template");
        } catch (Exception e) {
            log.error("Error while sending email: {}", e.getMessage());
            emailResponseDTO.setMessage("Failed to send email: " + e.getMessage());
        }

        return emailResponseDTO;
    }

  public  EmailResponseDTO sendMailUsingDbTemplate(EmailRequestDto emailRequestDto){
        EmailResponseDTO emailResponseDTO = new EmailResponseDTO();

        try {
            // Fetch the template content from the database
            String templateContent = getTemplateContentFromDatabase(emailRequestDto.getSubject()); // Using subject as template name for example
            String emailContent = processTemplateDb(emailRequestDto.getTemplateData(), templateContent);

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false);

            helper.setFrom(sender);
            helper.setTo(emailRequestDto.getRecipient());
            helper.setSubject(emailRequestDto.getSubject());
            helper.setText(emailContent, true); // HTML email

            javaMailSender.send(mimeMessage);
            emailResponseDTO.setMessage("Email sent successfully using database template");
        } catch (Exception e) {
            log.error("Error while sending email: {}", e.getMessage());
            emailResponseDTO.setMessage("Failed to send email: " + e.getMessage());
        }
        return emailResponseDTO;
    }

    private String processTemplate(Map<String, Object> model, String templateName) {
        try {
            Template template = freemarkerConfig.getTemplate(templateName);
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        } catch (Exception e) {
            log.error("Error processing FreeMarker template: {}", e.getMessage());
            throw new RuntimeException("Failed to process email template", e);
        }
    }

    private String processTemplateDb(Map<String, Object> model, String templateContent) {
        try {
            Template template = new Template("dynamicTemplate", templateContent, freemarkerConfig);
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        } catch (Exception e) {
            log.error("Error processing FreeMarker template: {}", e.getMessage());
            throw new RuntimeException("Failed to process email template", e);
        }
    }

    private String getTemplateContentFromDatabase(String templateName) {
        return emailTemplateRepository.findByTemplateName(templateName)
                .map(EmailTemplate::getTemplateContent)
                .orElseThrow(() -> new RuntimeException("Template not found: " + templateName));
    }
}
