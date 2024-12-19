package com.cosmoIntl.LiquibaseTest1.dto.requestDTO;

import lombok.*;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenRequestDto {
    private String token;
}
