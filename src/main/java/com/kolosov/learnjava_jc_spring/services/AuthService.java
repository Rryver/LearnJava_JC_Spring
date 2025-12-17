package com.kolosov.learnjava_jc_spring.services;

import com.kolosov.learnjava_jc_spring.config.AppConfig;
import com.kolosov.learnjava_jc_spring.dto.SignInRequest;
import com.kolosov.learnjava_jc_spring.dto.TokenResponse;
import com.kolosov.learnjava_jc_spring.models.FailedLoginAttempt;
import com.kolosov.learnjava_jc_spring.models.User;
import com.kolosov.learnjava_jc_spring.security.AuthUser;
import com.kolosov.learnjava_jc_spring.security.JWTUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final JWTUtils jwtUtils;
    private final AuthenticationManager authManager;
    private final UserService userService;

    public TokenResponse signIn(SignInRequest signInRequest) {
        try {
            log.info(String.format("Sign in attempt. Username: %s", signInRequest.getUsername()));
            Authentication authenticate = authManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
            AuthUser userDetails = (AuthUser) authenticate.getPrincipal();
            User user = userDetails.getUser();
            user.getFailedLoginAttempt().setAttempts(0);
            userService.save(user);

            return jwtUtils.generateToken(userDetails);
        } catch (BadCredentialsException e) {
            log.error(String.format("Sign in error. Bad credentials. Username: %s", signInRequest.getUsername()));
            User user = userService.getByEmail(signInRequest.getUsername());
            if (user != null) {
                FailedLoginAttempt loginAttempt = user.getFailedLoginAttempt();
                loginAttempt.setAttempts(loginAttempt.getAttempts() + 1);
                if (loginAttempt.getAttempts() >= AppConfig.MAX_FAILED_ATTEMPTS) {
                    user.setIsAccountNonLocked(false);
                    log.error(String.format("User %s blocked", signInRequest.getUsername()));
                }

                userService.save(user);
            } else {
                log.error(String.format("Sign in error. Username: %s not found", signInRequest.getUsername()));
            }

            throw e;
        }
    }
}
