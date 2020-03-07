package io.github.pedrominicz.semi.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User implements UserDetails {
    private static final long serialVersionUID = -2716502827930129870L;

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

    @ManyToMany
    private final Set<Post> posts = Collections.emptySet();

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

    @JsonIgnore
    @Override
    public List<SimpleGrantedAuthority> getAuthorities() {
        final List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>(2);

        authorities.add(new SimpleGrantedAuthority("user"));

        if (admin) {
            authorities.add(new SimpleGrantedAuthority("admin"));
        }

        return authorities;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}