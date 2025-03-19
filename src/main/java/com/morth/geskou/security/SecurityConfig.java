package com.morth.geskou.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.morth.geskou.security.jwt.JWTAuthentificationFilter;


@Configuration // indique à Spring que cette classe est une classe de configuration
@EnableWebSecurity // active la sécurité Web
public class SecurityConfig {

    @Bean // ici on configure les acces aux ressources (api)
    public SecurityFilterChain  filterChain(HttpSecurity http) throws Exception{
        http.csrf( csrf -> csrf.disable()) // désactive la protection CSRF
            .authorizeHttpRequests(
                authz -> authz
                        //.requestMatchers(HttpMethod.GET, "/api/products/**").permitAll() // autorise les requêtes GET sur /api/public/**
                        .requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll() 
                        .requestMatchers(HttpMethod.GET, "/api/product/create").hasAnyAuthority("USER", "ADMIN") // autorise les requêtes POST sur /api/user/** pour les utilisateurs ayant les rôles USER ou ADMIN
                        .requestMatchers(HttpMethod.GET, "/api/admin/**").hasAuthority("ADMIN") // autorise les requêtes POST sur /api/admin/** pour les utilisateurs ayant le rôle ADMIN
                        .anyRequest().authenticated() // toutes les autres requêtes nécessitent une authentification
            ); // autorise toutes les requêtes
        // Ajout du filtre JWTAuthentificationFilter avant UsernamePasswordAuthenticationFilter pour vérifier le token JWT et authentifier l'utilisateur
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder  passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWTAuthentificationFilter jwtAuthenticationFilter() {
        return new JWTAuthentificationFilter();
    }

        @Bean // configuration de l' AuthenticationManager
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}

