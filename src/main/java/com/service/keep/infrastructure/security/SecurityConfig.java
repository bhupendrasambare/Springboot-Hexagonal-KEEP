/**
 * author @bhupendrasambare
 * Date   :26/12/25
 * Time   :11:01 pm
 * Project:Keep
 **/
package com.service.keep.infrastructure.security;

import com.service.keep.domain.port.outbound.JwtTokenPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity httpSecurity,
            JwtTokenPort jwtTokenPort,
            UserDetailsService userDetailsService
    ){
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(
                jwtTokenPort, userDetailsService
        );

        httpSecurity.
                csrf(csrf -> csrf.disable())
                .cors(cors ->{})
                .sessionManagement( sm ->
                        sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .exceptionHandling(ex ->
                        ex.authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/auth/**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ){
        return configuration.getAuthenticationManager();
    }

}
