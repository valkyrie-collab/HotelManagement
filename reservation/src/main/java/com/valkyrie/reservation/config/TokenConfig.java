package com.valkyrie.reservation.config;

import java.security.Key;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenConfig {
    @Value("${jwts.security}")
    public String securityKey;

    private Key generateKey() {return Keys.hmacShaKeyFor(Decoders.BASE64.decode(securityKey));}

    private <I> I claims(String token, Function<Claims, I> claimBearer) {
        return claimBearer.apply(Jwts.parserBuilder().setSigningKey(generateKey()).build()
            .parseClaimsJws(token).getBody());
    }

    public String getUsername(String token) {return claims(token, Claims::getSubject);}

    public boolean isAdmin(String token) {
        List<?> roles = claims(token, claim -> claim.get("roles", List.class));
        List<String> role = roles == null? null : roles.stream().map(Object::toString).toList();
        return (role == null || !role.getFirst().equals("ROLE_ADMIN"))? false : true;
    }
    
}
