package com.app.taxes.service.serviceImpl;

import com.app.taxes.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;

    public MailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    @Override
    public void sendEmail(String email) {
        try {

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setText(buildEmail());
            helper.setTo(email);
            helper.setSubject("Test Send Email");

            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new IllegalStateException("failed to send email");
        }
    }

    @Override
    public String buildEmail() {
        return "Test Send Email Automatically";
    }
}
