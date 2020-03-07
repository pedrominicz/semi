package io.github.pedrominicz.semi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.pedrominicz.semi.model.User;
import io.github.pedrominicz.semi.security.JwtUtil;
import io.github.pedrominicz.semi.service.UserService;

@RequestMapping("api/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(path = "login")
    public String login(@RequestBody final User user) throws JsonProcessingException, AuthenticationException {
        final Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(),
                user.getPassword());

        return JwtUtil.generateToken((User) authenticationManager.authenticate(authentication).getPrincipal());
    }

    @PostMapping(path = "register")
    public String register(@RequestBody final User user) throws JsonProcessingException, AuthenticationException {
        return login(userService.save(new User(user.getUsername(), passwordEncoder.encode(user.getPassword()))));
    }
}