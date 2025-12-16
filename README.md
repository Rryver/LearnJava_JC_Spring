## Практическое задание - реализация Spring Security - простая конфигурация

---

1. Добавьте Spring Security в существующее Spring Boot приложение.


2. Добавьте зависимость spring-boot-starter-security в pom.xml.


3. Запустите приложение и проверьте, что все страницы теперь защищены и требуют логина.
Настройте простую конфигурацию Spring Security.


4. Создайте конфигурационный класс, в котором разрешите доступ к одному публичному URL, например, /home, и защитите все остальные URL.

    Пример кода
   ```java
    package com.example.securitytry.configuration;
    
    import org.springframework.context.annotation.Bean;  
    import org.springframework.context.annotation.Configuration;  
    import org.springframework.security.config.Customizer;  
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;  
    import org.springframework.security.core.userdetails.User;  
    import org.springframework.security.core.userdetails.UserDetailsService;  
    import org.springframework.security.provisioning.InMemoryUserDetailsManager;  
    import org.springframework.security.web.SecurityFilterChain;
    
    @Configuration  
    public class SecurityConfig {
    
        @Bean  
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {  
            http ->  
                // Ваш код тут
            return http.build();  
        }  
      
        @Bean  
        public UserDetailsService userDetailsService() {  
            var userDetailsManager = new InMemoryUserDetailsManager();  
                  // Ваш код тут
            return userDetailsManager;  
        }  
    }
    ```