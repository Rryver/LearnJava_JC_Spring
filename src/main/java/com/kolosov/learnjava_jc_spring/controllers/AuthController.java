package com.kolosov.learnjava_jc_spring.controllers;

import com.kolosov.learnjava_jc_spring.dto.TokenResponse;
import com.kolosov.learnjava_jc_spring.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/sign-in")
    public TokenResponse signIn() {
        return authService.generateToken();
    }

    @PostMapping("/token/test")
    public String test() {
        return "qwe";
    }
}
