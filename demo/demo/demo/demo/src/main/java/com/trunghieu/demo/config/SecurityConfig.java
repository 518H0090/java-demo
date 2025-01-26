package com.trunghieu.demo.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        String defaultUrl = "/api/trunghieu";

        http.authorizeHttpRequests((requests) ->
                requests.requestMatchers("/login","/error").permitAll()
                        .requestMatchers(defaultUrl+"/course/**",
                                defaultUrl+"/student_course/**",
                                defaultUrl+"/instructor/**",
                                defaultUrl+"/student/**",
                                defaultUrl+"/department/**").authenticated()
        );

        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("trunghieu").password("{noop}trunghieu").roles("USER").build();
        UserDetails admin = User.withUsername("admin").password("{bcrypt}$2a$12$yExJM4VCc8TVmXFe83opy.nLYxH2gkDcO3C31kWsK/6Ikbu/QfHNO").roles("admin").build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
