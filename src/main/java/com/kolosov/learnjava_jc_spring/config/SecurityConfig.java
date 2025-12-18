package com.kolosov.learnjava_jc_spring.config;

import com.kolosov.learnjava_jc_spring.models.User;
import com.kolosov.learnjava_jc_spring.repositories.UserRepository;
import com.kolosov.learnjava_jc_spring.security.AuthUser;
import com.kolosov.learnjava_jc_spring.services.socialLogin.SocialAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final SocialAppService socialAppService;
    private final LogoutSuccessHandler logoutSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/", "/login", "/error", "/webjars/**", "/static/**").permitAll() // Разрешаем доступ к этим маршрутам всем
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Только для администраторов
                        .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)) // Обработка ошибок аутентификации
                )
//                .oauth2Login(Customizer.withDefaults());
                .oauth2Login(oauth2Login -> oauth2Login
                        .loginPage("/") // Указываем страницу для входа
                        .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint
                                .userService(socialAppService))
                        .defaultSuccessUrl("/user")
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL для выхода
                        .logoutSuccessHandler(oidcLogoutSuccessHandler()) // Обработчик для логаута
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                );
//                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
//        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return email -> {
            User user = userRepository.findByEmail(email);
            if (user == null) {
                throw new UsernameNotFoundException(String.format("User with email %s not found", email));
            }

            return new AuthUser(user);
        };
    }

    private LogoutSuccessHandler oidcLogoutSuccessHandler() {
        OidcClientInitiatedServerLogoutSuccessHandler oidcLogoutSuccessHandler =
                new OidcClientInitiatedServerLogoutSuccessHandler(this.clientRegistrationRepository);

        new Oidc
        // Sets the location that the End-User's User Agent will be redirected to
        // after the logout has been performed at the Provider
        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}");

        return oidcLogoutSuccessHandler;
    }
}
