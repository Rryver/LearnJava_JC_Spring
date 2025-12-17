package com.kolosov.learnjava_jc_spring.security;

import com.kolosov.learnjava_jc_spring.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

//@Component
public class BasicAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final HandlerExceptionResolver exceptionResolver;
    private final UserService userService;

    public BasicAuthenticationEntryPoint(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver exceptionResolver,
                                         UserService userService) {
        this.exceptionResolver = exceptionResolver;
        this.userService = userService;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Authentication authentication = authException.getAuthenticationRequest();
        if (!authentication.isAuthenticated()) {
            String principal = (String) authentication.getPrincipal();
        }
        exceptionResolver.resolveException(request, response, null, authException);
    }
}
