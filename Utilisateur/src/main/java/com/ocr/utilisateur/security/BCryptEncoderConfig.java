package com.ocr.utilisateur.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BCryptEncoderConfig {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public BCryptEncoderConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean(name = "passwordEncoder")
    public static PasswordEncoder passwordencoder(){
        return new BCryptPasswordEncoder();
    }
}