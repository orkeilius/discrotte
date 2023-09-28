package com.discrotte.backend.security;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.*;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.discrotte.backend.model.Login;
import com.discrotte.backend.model.User;
import com.discrotte.backend.service.UserService;

import jakarta.annotation.PostConstruct;

@Component
public class UserAuthenticationProvider {
 
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;
 
    private final UserService userService;
 
    public UserAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }
 
    @PostConstruct
    protected void init() {
        // this is to avoid having the raw secret key available in the JVM
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }
 
    public String createToken(String login) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1 hour
 
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withIssuer(login)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(algorithm);
    }
 
    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
 
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
 
        DecodedJWT decoded = verifier.verify(token);
 
        User user = userService.getUser(decoded.getIssuer()).get();
 
        return new UsernamePasswordAuthenticationToken(user.getName(), null, Collections.emptyList());
    }
 
    public Authentication validateCredentials(Login login) {
        User user = userService.getUser(login.getUsername()).get();
        
        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }
 
 
}