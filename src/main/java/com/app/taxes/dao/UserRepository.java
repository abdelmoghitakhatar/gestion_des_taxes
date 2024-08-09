package com.app.taxes.dao;

import com.app.taxes.Domain.sec.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findUserEntityByUsername(String username);

    UserEntity findUserEntityByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String username);
}
