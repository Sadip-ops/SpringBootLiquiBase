package com.cosmoIntl.LiquibaseTest1.repository;



import com.cosmoIntl.LiquibaseTest1.entity.RefreshToken;
import com.cosmoIntl.LiquibaseTest1.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    RefreshToken findByUserInfo(UserInfo byUsername);
}
