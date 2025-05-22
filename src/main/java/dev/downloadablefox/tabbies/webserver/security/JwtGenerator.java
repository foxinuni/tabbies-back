package dev.downloadablefox.tabbies.webserver.security;

import java.security.Key;
import java.sql.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtGenerator {
    public static final Long EXPIRATION_TIME = 86400000L; // 1 day in milliseconds
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date(System.currentTimeMillis());
        Date expirationDate = new Date(currentDate.getTime() + EXPIRATION_TIME); // 1 day expiration
    
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(key)
                .compact();
        
        return token;
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
