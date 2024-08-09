package com.app.taxes.service.serviceImpl;

import com.app.taxes.Domain.sec.SecureToken;
import com.app.taxes.Domain.sec.UserEntity;
import com.app.taxes.dao.TokenRepository;
import com.app.taxes.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;
    private final Random random = new SecureRandom();
    private final TokenRepository tokenRepository;

    public MailServiceImpl(JavaMailSender javaMailSender, TokenRepository tokenRepository) {
        this.javaMailSender = javaMailSender;
        this.tokenRepository = tokenRepository;
    }

    @Async
    @Override
    public void sendEmail(UserEntity user) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");


            StringBuilder confirmationBuilder = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                confirmationBuilder.append(random.nextInt(10));
            }

            String confirmation = confirmationBuilder.toString();
            helper.setText(buildEmail(confirmation));
            helper.setTo(user.getEmail());
            helper.setSubject("Taxes Email Confirmation");

            SecureToken token = new SecureToken(confirmation, LocalDateTime.now().plusMinutes(3), user);
            tokenRepository.save(token);

            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new IllegalStateException("failed to send email");
        }
    }

    @Override
    public String buildEmail(String confirmation) {
        return "Your confirmation code is "+ confirmation;
    }


}
