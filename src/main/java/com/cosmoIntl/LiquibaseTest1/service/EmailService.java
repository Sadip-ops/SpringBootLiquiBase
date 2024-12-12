package com.cosmoIntl.LiquibaseTest1.service;

import com.cosmoIntl.LiquibaseTest1.entity.EmailDetail;

public interface EmailService {


    String sendSimpleMail(EmailDetail emailDetail);
    String sendMailWithAttachment(EmailDetail emailDetail);
}
