package com.discrotte.backend.security;

import java.io.IOException;

import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.discrotte.backend.model.Login;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class UsernamePasswordAuthFilter extends OncePerRequestFilter {
	 
    private static final ObjectMapper MAPPER = new ObjectMapper();
 
    private final UserAuthenticationProvider userAuthenticationProvider;
 
    public UsernamePasswordAuthFilter(UserAuthenticationProvider userAuthenticationProvider) {
        this.userAuthenticationProvider = userAuthenticationProvider;
    }
 
    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {
 
        if ("/signIn".equals(httpServletRequest.getServletPath())
                && HttpMethod.POST.matches(httpServletRequest.getMethod())) {
        	
            Login login = MAPPER.readValue(httpServletRequest.getInputStream(), Login.class);
 
            try {
                SecurityContextHolder.getContext().setAuthentication(
                        userAuthenticationProvider.validateCredentials(login));
            } catch (RuntimeException e) {
                SecurityContextHolder.clearContext();
                throw e;
            }
        }

        httpServletResponse.setHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, Authorization, access-control-allow-origin,mode");
        if ("OPTIONS".equals(httpServletRequest.getMethod())) {
        	httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        } else { 
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
 
        //filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
