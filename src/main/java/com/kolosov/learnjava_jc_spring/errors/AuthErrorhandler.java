package com.kolosov.learnjava_jc_spring.errors;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Component
public class AuthErrorhandler implements AuthenticationEntryPoint {

    private final HandlerExceptionResolver exceptionResolver;

    public AuthErrorhandler(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver exceptionResolver) {
        this.exceptionResolver = exceptionResolver;
    }

    @SneakyThrows
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ModelAndView modelAndView = exceptionResolver.resolveException(request, response, null, authException);
        modelAndView.getView().render(modelAndView.getModel(), request, response);
//        response.getWriter().write(modelAndView.toString());
    }
}
