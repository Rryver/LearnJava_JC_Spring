package com.kolosov.learnjava_jc_spring.services.socialLogin;

import com.kolosov.learnjava_jc_spring.models.User;
import com.kolosov.learnjava_jc_spring.security.AuthUser;
import lombok.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public class CustomOAuth2User extends AuthUser implements OAuth2User {
    private final OAuth2User oauth2User;

    public CustomOAuth2User(@NonNull OAuth2User oauth2User, @NonNull User user) {
        super(user);
        this.oauth2User = oauth2User;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oauth2User.getAttributes();
    }

    @Override
    public String getName() {
        return super.getUsername();
    }

    public static CustomOAuth2User get() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }

        return auth.getPrincipal() instanceof CustomOAuth2User authUser ? authUser : null;
    }
}
