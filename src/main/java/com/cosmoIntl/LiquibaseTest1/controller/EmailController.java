package com.cosmoIntl.LiquibaseTest1.controller;

import com.cosmoIntl.LiquibaseTest1.dto.requestDTO.EmailRequestDto;
import com.cosmoIntl.LiquibaseTest1.dto.responseDTO.EmailResponseDTO;
import com.cosmoIntl.LiquibaseTest1.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/mail")
public class EmailController {

    @Autowired
    private  EmailService emailService;

    @PostMapping("/sendSimpleMail")
    public ResponseEntity<EmailResponseDTO> sendSimpleMail(@RequestBody EmailRequestDto emailRequestDto) {
    EmailResponseDTO response=emailService.sendSimpleMail(emailRequestDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/sendMailWithAttachment")
    public ResponseEntity<EmailResponseDTO> sendMailWithAttachment(@RequestBody EmailRequestDto emailRequestDto) {

        EmailResponseDTO response=emailService.sendMailWithAttachment(emailRequestDto);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/sendMailFromTemplate")
    public ResponseEntity<EmailResponseDTO> sendMailFromTemplate(@RequestBody EmailRequestDto emailRequestDto) {

        EmailResponseDTO response=emailService.sendMailFromTemplate(emailRequestDto);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/sendMailFromDbTemplate")
    public ResponseEntity<EmailResponseDTO> sendMailFromDbTemplate(@RequestBody EmailRequestDto emailRequestDto) {
        EmailResponseDTO response = emailService.sendMailUsingDbTemplate(emailRequestDto);
        return ResponseEntity.ok(response);
    }

}
