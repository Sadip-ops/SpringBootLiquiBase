package com.cosmoIntl.LiquibaseTest1.entity;


import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailDetail {
    private String recipient;
    private String subject;
    private String msgBody;
    private String attachment;
}
