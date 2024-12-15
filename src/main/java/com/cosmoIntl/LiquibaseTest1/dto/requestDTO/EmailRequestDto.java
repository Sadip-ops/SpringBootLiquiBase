package com.cosmoIntl.LiquibaseTest1.dto.requestDTO;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequestDto {

    private String recipient;
    private String subject;
    private String msgBody;
    private String attachment;
}
