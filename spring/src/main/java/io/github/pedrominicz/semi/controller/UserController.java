package io.github.pedrominicz.semi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.pedrominicz.semi.model.User;
import io.github.pedrominicz.semi.security.JwtUtil;
import io.github.pedrominicz.semi.service.UserService;

@CrossOrigin
@RequestMapping("api/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(path = "login")
    @PreAuthorize("permitAll()")
    public String login(@RequestBody final User user) throws JsonProcessingException, AuthenticationException {
        final Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(),
                user.getPassword());

        return JwtUtil.generateToken((User) authenticationManager.authenticate(authentication).getPrincipal());
    }

    @PostMapping(path = "register")
    @PreAuthorize("permitAll()")
    public String register(@RequestBody final User user) throws JsonProcessingException, AuthenticationException {
        return login(userService.save(user));
    }

}