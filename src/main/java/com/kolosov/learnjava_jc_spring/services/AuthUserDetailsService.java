package com.kolosov.learnjava_jc_spring.services;

import com.kolosov.learnjava_jc_spring.models.User;
import com.kolosov.learnjava_jc_spring.repositories.UserRepository;
import com.kolosov.learnjava_jc_spring.security.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User with username %s not found", username));
        }

        return new AuthUser(user);
    }
}
