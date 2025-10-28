package com.valkyrie.entity.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenConfig {

    @Value("${jwts.security}")
    private String securityKey;
    
}
