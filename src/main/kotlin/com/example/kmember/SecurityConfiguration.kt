package com.example.kmember

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.encrypt.Encryptors
import org.springframework.security.crypto.encrypt.TextEncryptor
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(val jwtProvider: JwtProvider) {

    private val permitUrl = arrayOf(
        "/sign-up", "/login", "/swagger-ui/**", "/v2/api-docs/**",
        "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**"
    )

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun textEncryptor(): TextEncryptor {
        return Encryptors.text("password", "5c0744940b5c369b")
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {

        return http
            .headers { it -> it.frameOptions { it.disable() } }
            .csrf { it.disable() }
            .httpBasic { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests()
            .requestMatchers(*permitUrl).permitAll()
            .anyRequest().permitAll()
            .and()
            .addFilterBefore(
                JwtAuthenticationFilter(jwtProvider),
                UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }
}