package com.app.taxes.service;

import com.app.taxes.Domain.sec.UserEntity;

public interface MailService {

    void sendEmail(UserEntity user);

    String buildEmail(String confirmation);
}
