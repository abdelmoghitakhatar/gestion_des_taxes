package com.app.taxes.dao;

import com.app.taxes.Domain.sec.SecureToken;
import com.app.taxes.Domain.sec.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<SecureToken, Long> {

    Optional<SecureToken> findTopByTokenAndAndUser(String Token, UserEntity user);
}
