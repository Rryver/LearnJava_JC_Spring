package com.kolosov.learnjava_jc_spring.services.socialLogin;

import com.kolosov.learnjava_jc_spring.models.Role;
import com.kolosov.learnjava_jc_spring.models.User;
import com.kolosov.learnjava_jc_spring.repositories.UserRepository;
import com.kolosov.learnjava_jc_spring.services.socialLogin.dataExtractors.GoogleOAuth2UserDataExtractor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SocialAppService extends DefaultOAuth2UserService {
    public static final String SOCIAL_PASSWORD = "[social_pwd]";

    private final UserRepository userRepository;
    private final GoogleOAuth2UserDataExtractor extractor;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        OAuth2UserData oAuth2UserData = new OAuth2UserData(oAuth2User, userRequest);
        String email = extractor.getEmail(oAuth2UserData);
        if (email == null) {
            throw new OAuth2AuthenticationException(new OAuth2Error(OAuth2ErrorCodes.INVALID_TOKEN), "Google account does not contain email or first name");
        }

        String name = extractor.getFirstName(oAuth2UserData);

        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = userRepository.save(new User(email, SOCIAL_PASSWORD, name, Role.USER));
        }

        return new CustomOAuth2User(oAuth2User, user);
    }
}
