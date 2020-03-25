package io.github.pedrominicz.semi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.pedrominicz.semi.model.User;
import io.github.pedrominicz.semi.service.UserService;

@CrossOrigin
@RequestMapping("api/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public User.WithToken login(@RequestBody final User user) throws JsonProcessingException, AuthenticationException {
        return userService.login(user);
    }

    @PostMapping("register")
    public User.WithToken register(@RequestBody final User user)
            throws JsonProcessingException, AuthenticationException {
        return userService.register(user);
    }

}