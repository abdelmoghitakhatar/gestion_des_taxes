package com.app.taxes.service.serviceImpl;

import com.app.taxes.Domain.sec.RoleEntity;
import com.app.taxes.Domain.sec.UserEntity;
import com.app.taxes.dao.RoleRepository;
import com.app.taxes.dao.UserRepository;
import com.app.taxes.security.Constants;
import com.app.taxes.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, UserDetailsService userDetailsService, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void authenticateUser(HttpServletRequest request, UserEntity user) {
//        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
//        authToken.setDetails(new WebAuthenticationDetails(request));
//
//        Authentication authentication = authenticationManager.authenticate(authToken);
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public UserEntity addUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        return user;
    }

    @Override
    public UserEntity getConnectedUser(){
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        String username = principal.getName();
        return userRepository.findUserEntityByUsername(username);
    }

    @Override
    public void activateAccount(UserEntity user){
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(roleRepository.findByRole(Constants.ROLE_USER));
        user.setRoles(roles);
        user.setActive(true);
        userRepository.save(user);
    }
}
