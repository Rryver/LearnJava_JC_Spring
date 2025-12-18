package com.kolosov.learnjava_jc_spring.services.socialLogin.dataExtractors;

import com.kolosov.learnjava_jc_spring.services.socialLogin.OAuth2UserData;
import org.springframework.stereotype.Component;

@Component("google")
public class GoogleOAuth2UserDataExtractor implements OAuth2UserDataExtractor {
    @Override
    public String getFirstName(OAuth2UserData oAuth2UserData) {
        return oAuth2UserData.getData("given_name");
    }

    @Override
    public String getLastName(OAuth2UserData oAuth2UserData) {
        return oAuth2UserData.getData("family_name");
    }

    @Override
    public String getEmail(OAuth2UserData oAuth2UserData) {
        return oAuth2UserData.getData("email");
    }
}
