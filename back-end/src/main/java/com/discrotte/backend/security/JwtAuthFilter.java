package com.discrotte.backend.security;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class JwtAuthFilter extends OncePerRequestFilter {
	 
    private final UserAuthenticationProvider userAuthenticationProvider;
 
    public JwtAuthFilter(UserAuthenticationProvider userAuthenticationProvider) {
        this.userAuthenticationProvider = userAuthenticationProvider;
    }
 
    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException,java.io.IOException {
        String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
 
        if (header != null) {
            String[] authElements = header.split(" ");
 
            if (authElements.length == 2
                    && "Bearer".equals(authElements[0])) {
                try {
                    SecurityContextHolder.getContext().setAuthentication(
                            userAuthenticationProvider.validateToken(authElements[1]));
                } catch (RuntimeException e) {
                    SecurityContextHolder.clearContext();
                    throw e;
                }
            }
        }
 
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}