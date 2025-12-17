package com.kolosov.learnjava_jc_spring.security;

import com.kolosov.learnjava_jc_spring.models.Role;
import com.kolosov.learnjava_jc_spring.models.User;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Set;

@Setter
public class AuthUser extends org.springframework.security.core.userdetails.User {

    @NonNull
    private User user;

    public AuthUser(@NonNull User user) {
        super(user.getEmail(), user.getPassword(), Set.of(user.getRole()));
        this.user = user;
    }

    public static AuthUser get() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }

        return auth.getPrincipal() instanceof AuthUser authUser ? authUser : null;
    }

    public static AuthUser getOrThrow() throws NullPointerException {
        return Objects.requireNonNull(get(), "No authorized user found");
    }

    public User getUser() {
        return Objects.requireNonNull(user, "No authorized user found");
    }

    public boolean hasRole(Role role) {
        return user.hasRole(role);
    }

    @Override
    public String toString() {
        return "AuthUser:" + user.getId() + '[' + user.getEmail() + ']';
    }
}
