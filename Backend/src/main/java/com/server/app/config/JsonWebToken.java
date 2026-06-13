package com.server.app.config;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.server.app.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JsonWebToken {

    @Value("${security.jwt.expiration-time}")
    private int tokenTime;

    @Value("${security.jwt.secret-key}")
    private String tokenSecret;

    private SecretKey getTokenKey() {

        try {

            byte[] keyBytes = Decoders.BASE64.decode(tokenSecret);

            System.out.println("BYTES KEY: " + keyBytes.length);

            SecretKey key = Keys.hmacShaKeyFor(keyBytes);

            System.out.println("SECRET KEY GENERADA");

            return key;

        } catch (Exception e) {

            System.out.println("ERROR EN getTokenKey()");
            e.printStackTrace();

            throw e;
        }
    }

    public String createToken(User user) {

        try {

            System.out.println("ENTRO A CREAR TOKEN");

            Map<String, Object> json = new LinkedHashMap<>();
            json.put("id", user.getId());

            System.out.println("ID: " + user.getId());
            System.out.println("SECRET: " + tokenSecret);
            System.out.println("TIME: " + tokenTime);

            SecretKey key = getTokenKey();

            System.out.println("KEY GENERADA");

            String token = Jwts.builder()
                    .claims(json)
                    .signWith(key)
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + tokenTime))
                    .compact();

            System.out.println("TOKEN CREADO");

            return token;

        } catch (Exception e) {

            System.out.println("ERROR EN createToken()");
            e.printStackTrace();

            throw e;
        }
    }

    public Claims extracClaims(String token) {

        try {

            return Jwts.parser()
                    .verifyWith(getTokenKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

        } catch (ExpiredJwtException e) {

            return null;
        }
    }

    public Integer extractIdUser(String token) {

        Claims claims = extracClaims(token);

        if (claims == null) {
            return null;
        }

        return claims.get("id", Integer.class);
    }

    public boolean isTokenExpired(String token) {

        Claims claims = extracClaims(token);

        return claims == null || claims.getExpiration().before(new Date());
    }
}