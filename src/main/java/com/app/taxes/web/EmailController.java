package com.app.taxes.web;

import com.app.taxes.service.MailService;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmailController {

    private final MailService mailService;

    public EmailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/sendEmail")
    public String sendMail(@RequestParam(name = "email") String email) throws MessagingException {
        mailService.sendEmail(email);
        return "test_mail";
    }

    @GetMapping("/mail")
    public String mailTemplate() {
        return "test_mail";
    }

}
