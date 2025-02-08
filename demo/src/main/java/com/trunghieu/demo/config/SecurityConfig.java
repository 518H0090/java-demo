package com.trunghieu.demo.config;

import com.trunghieu.demo.converter.JwtAuthConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//
//        String defaultUrl = "/api/trunghieu";
//
//        http.authorizeHttpRequests((requests) ->
//                requests
//                        .requestMatchers("/login","/error").permitAll()
//                        .anyRequest().authenticated()
//        );
//
//        http.formLogin(withDefaults());
//        http.httpBasic(withDefaults());
//        return http.build();
//    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withUsername("trunghieu").password("{noop}trunghieu").roles("USER").build();
//        UserDetails admin = User.withUsername("admin").password("{bcrypt}$2a$12$yExJM4VCc8TVmXFe83opy.nLYxH2gkDcO3C31kWsK/6Ikbu/QfHNO").roles("admin").build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }

    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) ->
                requests
                        .requestMatchers("/login","/error").permitAll()
                        .anyRequest().authenticated()
        );

        http
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthConverter);

        return http.build();
    }
}
