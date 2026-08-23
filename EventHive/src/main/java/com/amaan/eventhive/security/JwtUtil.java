package com.amaan.eventhive.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private final String secret = "EventHiveSecretKeyForJwtAuthentication123";

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {

        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);

            return true;

        } catch (Exception e) {
            return false;
        }
    }



}