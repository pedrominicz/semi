package io.github.pedrominicz.semi.service;

import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.security.core.AuthenticationException;

import io.github.pedrominicz.semi.model.User;

public interface UserService {

    User.WithToken login(final User user) throws JsonProcessingException, AuthenticationException;

    User.WithToken register(final User user) throws JsonProcessingException, AuthenticationException;

    Optional<User> findByName(final String name);

}
