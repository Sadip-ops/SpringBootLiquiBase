package com.cosmoIntl.LiquibaseTest1.repository;



import com.cosmoIntl.LiquibaseTest1.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByUsername(String username);
}
