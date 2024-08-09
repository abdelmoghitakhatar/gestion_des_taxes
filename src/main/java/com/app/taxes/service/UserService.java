package com.app.taxes.service;

import com.app.taxes.Domain.sec.UserEntity;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {

    void authenticateUser(HttpServletRequest request, UserEntity user);

    UserEntity addUser(UserEntity user);

    UserEntity getConnectedUser();

    void activateAccount(UserEntity user);

    }
