package com.kolosov.learnjava_jc_spring.controllers;

import com.kolosov.learnjava_jc_spring.dto.SignInRequest;
import com.kolosov.learnjava_jc_spring.dto.TokenResponse;
import com.kolosov.learnjava_jc_spring.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public TokenResponse signIn(@RequestBody SignInRequest signInRequest) {
        return authService.signIn(signInRequest);
    }

    @GetMapping("/token/test")
    public String test() {
        return "qwe";
    }
}
