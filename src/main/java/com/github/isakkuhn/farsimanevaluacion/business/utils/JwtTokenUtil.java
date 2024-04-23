package com.github.isakkuhn.farsimanevaluacion.business.utils;

import com.github.isakkuhn.farsimanevaluacion.persistence.entities.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenUtil {

    private static final String SECRET = "arsiman-evaluacion";
    private static final long EXPIRATION_TIME = 86400000;

    public String generateToken(UserEntity user){
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .subject(user.getUsername())
                .claim("role", user.getRole())
                .issuedAt(now)
                .expiration(expirationDate)
                .signWith(getSecretKey(SECRET))
                .compact();
    }

    public Claims getClaims(String token){
        return Jwts.parser()
                .verifyWith(getSecretKey(SECRET))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getUsername(String token){
        return Jwts.parser()
                .verifyWith(getSecretKey(SECRET))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser()
                    .verifyWith(getSecretKey(SECRET))
                    .build()
                    .parseEncryptedClaims(token)
                    .getPayload()
                    .getSubject();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    private SecretKey getSecretKey(String secret){
        byte[] secretBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(secretBytes);
    }
}
