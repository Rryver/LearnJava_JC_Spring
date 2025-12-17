package com.kolosov.learnjava_jc_spring.services;

import com.kolosov.learnjava_jc_spring.dto.TokenRequest;
import com.kolosov.learnjava_jc_spring.dto.TokenResponse;
import com.kolosov.learnjava_jc_spring.security.AuthUser;
import com.kolosov.learnjava_jc_spring.security.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JWTUtils jwtUtils;

    private final AuthenticationManager authManager;

    public TokenResponse generateToken() {
        return jwtUtils.generateToken(AuthUser.getOrThrow());
    }
}
