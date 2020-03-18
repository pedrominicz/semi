package io.github.pedrominicz.semi.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.pedrominicz.semi.model.User;
import io.github.pedrominicz.semi.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public User save(final User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(final String username) {
        return userRepository.findByUsername(username);
    }

    public Set<User> findByUsernameIn(final Set<String> usernames) {
        final Iterable<User> users = userRepository.findByUsernameIn(usernames);

        return StreamSupport.stream(users.spliterator(), false).collect(Collectors.toSet());
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}