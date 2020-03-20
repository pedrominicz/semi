package io.github.pedrominicz.semi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import io.github.pedrominicz.semi.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(final String username);

    List<User> findByUsernameIn(final List<String> usernames);

}