package com.app.taxes.service;

public interface MailService {

    void sendEmail(String email);

    String buildEmail();
}
