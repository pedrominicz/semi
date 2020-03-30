package io.github.pedrominicz.semi.service;

import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.pedrominicz.semi.model.User;
import io.github.pedrominicz.semi.repository.UserRepository;
import io.github.pedrominicz.semi.security.JwtUtil;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PreAuthorize("permitAll()")
    public User.Token login(final User user) throws JsonProcessingException, AuthenticationException {
        final Authentication authentication = new UsernamePasswordAuthenticationToken(user.getName(),
                user.getPassword());

        final User authenticatedUser = (User) authenticationManager.authenticate(authentication).getPrincipal();

        final String token = JwtUtil.generateToken(authenticatedUser);

        return new User.Token(authenticatedUser, token);
    }

    @PreAuthorize("permitAll()")
    public User.Token register(final User user)
            throws JsonProcessingException, AuthenticationException {
        return login(save(user));
    }

    private User save(final User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByName(final String name) {
        return userRepository.findByName(name);
    }

    public UserDetails loadUserByUsername(final String name) throws UsernameNotFoundException {
        return findByName(name).orElseThrow(() -> new UsernameNotFoundException(name));
    }

}
