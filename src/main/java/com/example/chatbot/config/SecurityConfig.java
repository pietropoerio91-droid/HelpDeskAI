package com.example.chatbot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf
                // In produzione abilita il token CSRF; qui ignoriamo la nostra API per semplicità
                .ignoringRequestMatchers("/api/chat")
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/chat", "/api/chat", "/css/**", "/js/**").permitAll()
                .anyRequest().permitAll()
            )
            .httpBasic(Customizer.withDefaults())
            .formLogin(form -> form.disable());

        return http.build();
    }
}
