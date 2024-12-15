package com.cosmoIntl.LiquibaseTest1.service;

import com.cosmoIntl.LiquibaseTest1.dto.requestDTO.EmailRequestDto;
import com.cosmoIntl.LiquibaseTest1.dto.responseDTO.EmailResponseDTO;
import com.cosmoIntl.LiquibaseTest1.entity.EmailDetail;

public interface EmailService {


    EmailResponseDTO sendSimpleMail(EmailRequestDto emailRequestDto);
    EmailResponseDTO sendMailWithAttachment(EmailRequestDto emailRequestDto);
    EmailResponseDTO sendMailFromTemplate(EmailRequestDto emailRequestDto);
}
