package io.github.pedrominicz.semi.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;

import io.github.pedrominicz.semi.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

    private static final String secret = "secret";

    private static ObjectMapper objectMapper;

    @Autowired
    private JwtUtil(final ObjectMapper objectMapper) {
        JwtUtil.objectMapper = objectMapper;
        JwtUtil.objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static String generateToken(final User user) throws JsonProcessingException {
        final String userJson = objectMapper.writeValueAsString(user);

        return Jwts.builder().claim("user", userJson).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public static User parseToken(final String token) throws JsonProcessingException {
        final String content = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("user",
                String.class);

        return objectMapper.readValue(content, User.class);
    }

}