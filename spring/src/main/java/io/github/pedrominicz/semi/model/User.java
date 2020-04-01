package io.github.pedrominicz.semi.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

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
    private final String name;

    @NotNull
    private final String password;

    @Column(columnDefinition = "boolean default false")
    @NotNull
    private final Boolean admin = false;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "author", orphanRemoval = true)
    private final List<Post> posts = Collections.emptyList();

    // Hibernate requires the existance of a no-argument constructor.
    public User() {
        name = null;
        password = null;
    }

    public User(@JsonProperty("name") final String name, @JsonProperty("password") final String password) {
        this.name = name;
        this.password = password;
    }

    @JsonView(View.class)
    public String getName() {
        return name;
    }

    @Override
    public String getUsername() {
        return getName();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @JsonView(Token.View.class)
    public Boolean getAdmin() {
        return admin;
    }

    @Override
    public List<SimpleGrantedAuthority> getAuthorities() {
        final List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>(2);

        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        if (admin) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
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

    public static class Token {

        private final String name;

        private final String token;

        private final Boolean admin;

        public Token(final User user, final String token) {
            this.name = user.getName();
            this.token = token;
            this.admin = user.getAdmin();
        }

        @JsonView(View.class)
        public String getName() {
            return name;
        }

        @JsonView(View.class)
        public String getToken() {
            return token;
        }

        @JsonView(View.class)
        public Boolean getAdmin() {
            return admin;
        }

        public interface View extends User.View {

            // Empty.

        }

    }

    public interface View {

        // Empty.

    }

}