package com.proyecto.infinitTask.app.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.*;

public class TokenUtils {

    private final static String ACCESS_TOKEN_SECRET = "z6eGH3a9i,$QJ[SV/(tkG[dT%&;+nj+SC!nD";
    private final static  Long ACCESS_TOKEN_VALIDITY_SECONDS = 86_400L; //Un dia de validez

    public static String createToken(String usuario) {
        long expiratioTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1000;
        Date expiratioDate = new Date(System.currentTimeMillis() + expiratioTime);

        Map<String, Object> extra = new HashMap<>();

        return Jwts.builder()
                .setSubject(usuario)
                .setExpiration(expiratioDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try{
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String usuario = claims.getSubject();

            return new UsernamePasswordAuthenticationToken(usuario, null, Collections.emptyList());
        } catch(JwtException e) {
            return null;
        }
    }
}
