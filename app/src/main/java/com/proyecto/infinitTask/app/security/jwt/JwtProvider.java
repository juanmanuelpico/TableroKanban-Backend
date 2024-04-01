package com.proyecto.infinitTask.app.security.jwt;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.unla.Beneficiarios.security.entities.UserSecurity;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {

    protected static final Logger logger = LogManager.getLogger(JwtProvider.class);
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authentication) {
        UserSecurity userSecurity = (UserSecurity) authentication.getPrincipal();
        return Jwts.builder().setSubject(userSecurity.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getUserNameFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Metodo: validateToken - Ocurrio un error al validar el token : token mal formado");
        } catch (UnsupportedJwtException e) {
            logger.error("Metodo: validateToken - Ocurrio un error al validar el token : token no soportado");
        } catch (ExpiredJwtException e) {
            logger.error("Metodo: validateToken - Ocurrio un error al validar el token : token expirado");
        } catch (IllegalArgumentException e) {
            logger.error("Metodo: validateToken - Ocurrio un error al validar el token : token vacio");
        } catch (SignatureException e) {
            logger.error("Metodo: validateToken - Ocurrio un error al validar el token : fail en la firma");
        }
        return false;
    }
}