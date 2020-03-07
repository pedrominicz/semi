package io.github.pedrominicz.semi.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.pedrominicz.semi.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
    private static final String secret = "secret";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String generateToken(final User user) throws JsonProcessingException {
        final String userJson = objectMapper.writeValueAsString(user);

        return Jwts.builder().claim("user", userJson).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public static User parseToken(final String token) throws JsonMappingException, JsonProcessingException {
        final String content = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().get("user",
                String.class);

        return objectMapper.readValue(content, User.class);
    }
}