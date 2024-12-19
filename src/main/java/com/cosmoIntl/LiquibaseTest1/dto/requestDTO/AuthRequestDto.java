package com.cosmoIntl.LiquibaseTest1.dto.requestDTO;

import com.cosmoIntl.LiquibaseTest1.validation.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestDto {
    @NotNull(message="Username cannot be Null.")
    @NotBlank(message = "Username cannot be Blank.")
    private String username;
    @ValidPassword
    private String password;
}
