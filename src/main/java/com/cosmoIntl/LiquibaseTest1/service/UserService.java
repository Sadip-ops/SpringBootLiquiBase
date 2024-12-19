package com.cosmoIntl.LiquibaseTest1.service;




import com.cosmoIntl.LiquibaseTest1.dto.requestDTO.RoleRequest;
import com.cosmoIntl.LiquibaseTest1.dto.requestDTO.UserRequest;
import com.cosmoIntl.LiquibaseTest1.dto.responseDTO.UserResponse;
import com.cosmoIntl.LiquibaseTest1.entity.UserRole;

import java.util.List;

public interface UserService {
    UserResponse saveUser(UserRequest userRequest);

    UserResponse getUserById(Long id);

    void deleteUserById(Long id);

    List<UserResponse> getAllUser();

    void deleteRoleById(Long roleId);

    UserRole addRole(RoleRequest roleRequest);

}
