package com.cosmoIntl.LiquibaseTest1.repository;


import com.cosmoIntl.LiquibaseTest1.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByName(String name);
}
