package com.app.taxes.Domain.sec;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@Entity
public class UserEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Length(min = 8, message = "The password must have at least {min} digits")
    private String password;

    @Column(unique = true)
    @Email(message = "This field must be an Email")
    private String email;

    private boolean active = false;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<RoleEntity> roles;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<SecureToken> tokens;

    public UserEntity() {
    }

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserEntity(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public UserEntity(String username, String password, Set<RoleEntity> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public UserEntity(Long id, String username, String password, Set<RoleEntity> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public Set<SecureToken> getTokens() {
        return tokens;
    }

    public void setTokens(Set<SecureToken> tokens) {
        this.tokens = tokens;
    }
}
