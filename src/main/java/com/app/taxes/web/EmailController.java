package com.app.taxes.web;

import com.app.taxes.Domain.sec.SecureToken;
import com.app.taxes.Domain.sec.UserEntity;
import com.app.taxes.dao.TokenRepository;
import com.app.taxes.dao.UserRepository;
import com.app.taxes.service.MailService;
import com.app.taxes.service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class EmailController {

    private final MailService mailService;
    private final UserRepository userRepository;
    private final UserService userService;
    private final TokenRepository tokenRepository;

    public EmailController(MailService mailService, UserRepository userRepository, UserService userService, TokenRepository tokenRepository) {
        this.mailService = mailService;
        this.userRepository = userRepository;
        this.userService = userService;
        this.tokenRepository = tokenRepository;
    }

    @PostMapping("/verifyEmail")
    public String sendMail(@RequestParam(name = "code") String code) {
        UserEntity user = userService.getConnectedUser();
        Optional<SecureToken> secureToken = tokenRepository.findTopByTokenAndAndUser(code, user);
        if(secureToken.isPresent() && secureToken.get().getExpiredAt().isAfter(LocalDateTime.now())){
            userService.activateAccount(user);
//            return "redirect:/";
            return "redirect:/logout";
        }
        return "redirect:/mail";
    }

    @GetMapping("/verifyEmail")
    public String verifyMail(@RequestParam(name = "token") String token, @RequestParam(name = "email") String email) {
        UserEntity user = userRepository.findUserEntityByEmail(email);
        Optional<SecureToken> secureToken = tokenRepository.findTopByTokenAndAndUser(token, user);
        if(secureToken.isPresent() && secureToken.get().getExpiredAt().isAfter(LocalDateTime.now())){
            userService.activateAccount(user);
//      TODO : to add verification by link
            return "redirect:/";
        }
        return "redirect:/mail";
    }

    @GetMapping("/mail")
    public String mailTemplate() {
        return "test_mail";
    }

    @GetMapping("/sendMail")
    public String sendMail() {
        UserEntity user = userService.getConnectedUser();
        mailService.sendEmail(user);
        return "redirect:/mail";
    }

}
