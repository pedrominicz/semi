package io.github.pedrominicz.semi.service;

import java.util.List;
import java.util.Optional;

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

    public Optional<User> findByName(final String name) {
        return userRepository.findByName(name);
    }

    public List<User> findByNameIn(final List<String> names) {
        return userRepository.findByNameIn(names);
    }

    @Override
    public UserDetails loadUserByUsername(final String name) throws UsernameNotFoundException {
        return findByName(name).orElseThrow(() -> new UsernameNotFoundException(name));
    }
}