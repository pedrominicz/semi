package io.github.pedrominicz.semi.model;

import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User implements UserDetails {
    private static final long serialVersionUID = -7926043431701724639L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id = null;

    @Column(length = 16, unique = true)
    @NotNull
    private final String username;

    @NotNull
    private final String password;

    @Column(columnDefinition = "boolean default false")
    @NotNull
    private final Boolean admin = false;

    // Hibernate requires the existance of a no-argument constructor.
    public User() {
        username = null;
        password = null;
    }

    public User(@JsonProperty("username") final String username, @JsonProperty("password") final String password) {
        this.username = username;
        this.password = password;
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
    public List<SimpleGrantedAuthority> getAuthorities() {
        final List<SimpleGrantedAuthority> authorities = Collections.emptyList();

        authorities.add(new SimpleGrantedAuthority("user"));

        if (admin) {
            authorities.add(new SimpleGrantedAuthority("admin"));
        }

        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}