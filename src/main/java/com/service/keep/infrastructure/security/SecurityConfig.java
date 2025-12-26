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
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity httpSecurity,
            JwtTokenPort jwtTokenPort,
            AuthenticationUserDetailsService userDetailsService
    ){
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter()
    }

}
