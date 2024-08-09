package com.app.taxes.security;

import com.app.taxes.Domain.sec.UserEntity;
import com.app.taxes.dao.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findUserEntityByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new User(user.getUsername(), user.getPassword(), getAuthorities(user));

    }

    public Collection<GrantedAuthority> getAuthorities(UserEntity user) {
        if (!user.getRoles().isEmpty()) {
            return user.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_"+role.getRole()))
                    .collect(Collectors.toList());
        }
        Collection<GrantedAuthority> collection = new ArrayList<>();
//        collection.add(new SimpleGrantedAuthority("ROLE_USER"));
        return collection;

    }
}
