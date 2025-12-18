## Практическое задание - Spring Security - oauth2

---

### 1. Регистрация клиента в провайдере OAuth 2.0
- Вы должны зарегистрировать своё приложение как клиент в провайдере OAuth 2.0: Google, GitHub, или любой другой провайдер.
- Получите client_id и client_secret после регистрации.
- Задача: настроить эти параметры в проекте.

### 2. Интеграция Spring Security и OAuth 2.0

```
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-oauth2-client</artifactId>
</dependency>
```
   
#### Настройте SecurityConfigurerAdapter:

- В application.yml добавьте настройки клиента (например, для Google):
```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: your-client-id
            client-secret: your-client-secret
            scope: profile, email
            redirect-uri: "{baseUrl}/login/oauth2/code/{registrationId}"
```

- Используйте Authorization Code поток для аутентификации.
- Задача: настроить приложение для работы с OAuth 2.0 авторизацией.


### 3. Разделение доступа на роли
- На основе данных, возвращаемых провайдером (например, GitHub или Google), реализуйте функционал назначения ролей пользователю.


- Пример ролей:
  - USER: имеет доступ к основным ресурсам.
  - ADMIN: имеет доступ к административным ресурсам.


- Задача: настроить доступ к определенным эндпоинтам в зависимости от роли пользователя. Пример:
```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
   http
           .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                   .requestMatchers("/", "/login", "/error", "/webjars/**").permitAll() // Разрешаем доступ к этим маршрутам всем
                   .requestMatchers("/h2-console/*").permitAll()
                   .requestMatchers("/admin/**").hasRole("ADMIN") // Только для администраторов
                   .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
           )
           .exceptionHandling(exceptionHandling -> exceptionHandling
                   .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)) // Обработка ошибок аутентификации
           )
           .oauth2Login(oauth2Login -> oauth2Login
                   .loginPage("/") // Указываем страницу для входа
                   .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint
                           .userService(socialAppService))
                   .defaultSuccessUrl("/user")
           )
           .logout(logout -> logout
                   .logoutUrl("/logout") // URL для выхода
                   .logoutSuccessHandler(oidcLogoutSuccessHandler()) // Обработчик для логаута
                   .invalidateHttpSession(true)
                   .deleteCookies("JSESSIONID")
           );
   return http.build();
}
```


### 4. Получение и отображение профиля пользователя
- После успешной аутентификации получите данные профиля пользователя из провайдера OAuth 2.0 (имя, email и т.д.).
- Настройте контроллер, который будет получать эту информацию и передавать её на страницу профиля пользователя.
```java
@GetMapping("/user")  
public String user(@AuthenticationPrincipal OAuth2User principal, Model model) {  
   model.addAttribute("name", principal.getAttribute("name"));  
   model.addAttribute("login", principal.getAttribute("login"));  
   model.addAttribute("id", principal.getAttribute("id"));  
   model.addAttribute("email", principal.getAttribute("email"));  
   return "user";  
}
```

### 5. Отзыв доступа и выход из системы
- Реализуйте механизм отзыва доступа:
- Добавьте функционал выхода из системы и отзыв OAuth-токена.
- Для Google или других провайдеров это можно сделать через вызов logout URL.
- Задача: реализовать безопасный выход из системы и отзыв токена.

### 6. Логирование действий пользователя
- Интегрируйте логирование ключевых действий:
- Успешная аутентификация.
- Выход из системы.
- Отзыв доступа.
- Используйте стандартное логирование Spring или SLF4J для записи этих действий в журнал.
- Задача: добавить логирование этих событий.

### 7. Защита эндпоинтов
- Защитите чувствительные части приложения:
- Запретите доступ к страницам, таким как админ-панель, для неавторизованных пользователей.
- Задача: реализовать правильную защиту, чтобы защищённые эндпоинты были доступны только авторизованным пользователям и определённым ролям.

```java
@Service  
@AllArgsConstructor  
public class SocialAppService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

   private final UserRepository userRepository;
   private static final Logger logger = LoggerFactory.getLogger(SocialAppService.class);

   @Override  
   @Transactional 
   public OAuth2User loadUser(OAuth2UserRequest userRequest) {
       OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
       OAuth2User oAuth2User = delegate.loadUser(userRequest);
       // Логика сохранения юзера, определения роли на основе аутентификации
       return savedUser;
   }
}
```

### 8. Обработка ошибок
- Добавьте обработчики для ошибок:
- Ошибки аутентификации.
- Недостаток прав доступа (403 Forbidden).
- Ошибка истечения токена и другие сценарии.
- Задача: реализовать контроллер для обработки этих ошибок и выводить корректные сообщения пользователю.
   
---

### Дополнительные материалы:

- https://spring.io/guides/tutorials/spring-boot-oauth2

- https://vk.com/video-218833461_456239075