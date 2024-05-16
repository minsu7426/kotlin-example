package com.example.backend.global.config.security

import org.springframework.cglib.core.Customizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.DefaultSecurityFilterChain

@Configuration
class SecurityConfig {

    private val allowedUrls = arrayOf("/", "/api/common/sign-up", "/api/common/sign-in")

    @Bean
    fun filterChain(http: HttpSecurity): DefaultSecurityFilterChain {
        return http
            .csrf(CsrfConfigurer<HttpSecurity>::disable)
            .httpBasic { it.disable() }
            .authorizeHttpRequests { request ->
                request.requestMatchers(*allowedUrls).permitAll()
                    .requestMatchers("/member/**").permitAll()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
            }
            .sessionManagement{ it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .build()!!
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
//    fun passwordEncoder(): PasswordEncoder {
//        return BCryptPasswordEncoder()
//    }

}