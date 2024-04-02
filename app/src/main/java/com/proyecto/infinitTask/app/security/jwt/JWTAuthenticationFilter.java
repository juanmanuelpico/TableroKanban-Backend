package com.proyecto.infinitTask.app.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.infinitTask.app.security.service.UserDetailImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import java.io.IOException;
import java.util.Collections;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                               HttpServletResponse response) {
        AuthCredentials authCredentials = new AuthCredentials();

        try{
            authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
        } catch (IOException e) {
        }

        UsernamePasswordAuthenticationToken usernamePat = new UsernamePasswordAuthenticationToken(
                authCredentials.getUsuario(),
                authCredentials.getPassword(),
                Collections.emptyList()
        );

        return getAuthenticationManager().authenticate(usernamePat);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                             HttpServletResponse response,
                                             FilterChain chain,
                                             Authentication authResult) throws IOException, ServletException{
       UserDetailImpl userDetails = (UserDetailImpl) authResult.getPrincipal();
       String token = TokenUtils.createToken(userDetails.getUsername());

       response.addHeader("Authorization", "Bearer " + token);
       response.getWriter().flush();
        // Una vez que la autenticación ha sido exitosa, establece el contexto de seguridad
        SecurityContextHolder.getContext().setAuthentication(authResult);

       super.successfulAuthentication(request, response, chain, authResult);
    }
}
