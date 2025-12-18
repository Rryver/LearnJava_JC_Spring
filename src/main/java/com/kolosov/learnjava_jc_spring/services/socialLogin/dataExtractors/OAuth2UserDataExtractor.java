package com.kolosov.learnjava_jc_spring.services.socialLogin.dataExtractors;

import com.kolosov.learnjava_jc_spring.services.socialLogin.OAuth2UserData;

public interface OAuth2UserDataExtractor {
    String getFirstName(OAuth2UserData oAuth2UserData);

    String getLastName(OAuth2UserData oAuth2UserData);

    String getEmail(OAuth2UserData oAuth2UserData);
}
