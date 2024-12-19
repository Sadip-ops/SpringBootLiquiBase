package com.cosmoIntl.LiquibaseTest1.dto.responseDTO;


import com.cosmoIntl.LiquibaseTest1.entity.UserRole;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private Long id;
    private String username;
    private Set<UserRole> roles;


}
