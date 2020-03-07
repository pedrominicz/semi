package io.github.pedrominicz.semi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import io.github.pedrominicz.semi.model.User;
import io.github.pedrominicz.semi.service.UserService;

@Component
public class SecurityUtil {
    private static UserService userService;

    @Autowired
    public void setUserService(final UserService userService) {
        SecurityUtil.userService = userService;
    }

    private SecurityUtil() {
        // Empty.
    }

    public static User getUser() {
        // TODO: verify which exception this function throws when the user is
        // not authenticated.
        final String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}