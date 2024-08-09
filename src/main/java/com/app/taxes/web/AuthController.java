package com.app.taxes.web;

import com.app.taxes.Domain.sec.UserEntity;
import com.app.taxes.dao.UserRepository;
import com.app.taxes.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserRepository userRepository;
    private final UserService userService;

    public AuthController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/sign-in")
    public String signInPage(Model model){
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "signin";
    }

    @PostMapping("/sign-in")
    public String signIn(@Valid @ModelAttribute(name = "user") UserEntity user, BindingResult bindingResult, HttpServletRequest request){
        if (bindingResult.hasErrors()){
            return "signin";
        }
        boolean existingUsername = userRepository.existsByUsername(user.getUsername());
        boolean existingEmail = userRepository.existsByEmail(user.getEmail());
        if (existingUsername) {
            bindingResult.addError(new FieldError("user", "username", "Username already exists"));
        }
        if (existingEmail) {
            bindingResult.addError(new FieldError("user", "email", "Email already exists"));
        }

        if (bindingResult.hasErrors()){
            return "signin";
        }

        userService.addUser(user);
        userService.authenticateUser(request, user);

        return "redirect:/mail";
    }
}
