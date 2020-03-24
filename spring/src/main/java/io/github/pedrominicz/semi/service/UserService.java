package io.github.pedrominicz.semi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import io.github.pedrominicz.semi.model.User;

public interface UserService extends UserDetailsService {

    User save(final User user);

    Optional<User> findByName(final String name);

    List<User> findByNameIn(final List<String> names);

}
