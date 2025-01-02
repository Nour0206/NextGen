package com.example.tptekup.JWT;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(com.example.tptekup.Entities.User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name())) // Assuming role.getName() returns a string
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(), // Assuming user has getId() method
                user.getUsername(), // Assuming user has getUsername() method
                user.getEmail(), // Assuming user has getEmail() method
                user.getPassword(), // Assuming user has getPassword() method
                authorities
        );
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Modify based on your application's logic
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Modify based on your application's logic
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Modify based on your application's logic
    }

    @Override
    public boolean isEnabled() {
        return true; // Modify based on your application's logic
    }
}
