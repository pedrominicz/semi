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

    /**
     * Returns the authenticated user.
     *
     * @return the authenticated user
     */
    public static User getAuthenticatedUser() {
        final String username = getAuthenticatedUserUsername();

        return userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    /**
     * Returns the user name of the authenticated user.
     *
     * @return the user name of the authenticated user
     */
    public static String getAuthenticatedUserUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}