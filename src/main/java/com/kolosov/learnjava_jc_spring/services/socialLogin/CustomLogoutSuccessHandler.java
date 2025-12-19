package com.kolosov.learnjava_jc_spring.services.socialLogin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
@Slf4j
public class CustomLogoutSuccessHandler extends OidcClientInitiatedLogoutSuccessHandler {
    private final OAuth2AuthorizedClientService clientService;


    public CustomLogoutSuccessHandler(ClientRegistrationRepository clientRegistrationRepository, OAuth2AuthorizedClientService clientService) {
        super(clientRegistrationRepository);
        this.clientService = clientService;

        this.setPostLogoutRedirectUri("{baseUrl}");
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info(String.format("User %s logout.", authentication.getName()));
        RestTemplate restTemplate = new RestTemplate();
        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;

        String clientRegistrationId = oauthToken.getAuthorizedClientRegistrationId();
        OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(clientRegistrationId, oauthToken.getName());
        String tokenValue = client.getAccessToken().getTokenValue();

        map.add("token", tokenValue);

        try {
            log.info(String.format("Sending revoke token request for user %s", authentication.getName()));
            restTemplate.postForObject("https://oauth2.googleapis.com/revoke", map, Void.class);
        } catch (Exception e) {
            log.error(String.format("Error revoking OAuth2 access_token by user %s.", authentication.getName()));
        }

        super.onLogoutSuccess(request, response, authentication);
    }
}
