package com.valkyrie.entity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class AuthenticationByPass {

    @Bean
    public RequestInterceptor requestInterceptor() {
        
        return restTemplate -> {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                String authenticationHeader = request.getHeader("Authorization");

                if (authenticationHeader != null) {
                    restTemplate.header("Authorization", authenticationHeader);
                }

            }

        };

    }

}
