package com.cosmoIntl.LiquibaseTest1.controller;

import com.cosmoIntl.LiquibaseTest1.entity.EmailDetail;
import com.cosmoIntl.LiquibaseTest1.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class EmailController {

    @Autowired
    private  EmailService emailService;

    @PostMapping("/sendSimpleMail")
    public ResponseEntity<String> sendSimpleMail(@RequestBody EmailDetail emailDetail) {
    String response=emailService.sendSimpleMail(emailDetail);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/sendMailWithAttachment")
    public ResponseEntity<String> sendMailWithAttachment(@RequestBody EmailDetail emailDetail) {

        String response=emailService.sendMailWithAttachment(emailDetail);
        return ResponseEntity.ok(response);

    }
}
