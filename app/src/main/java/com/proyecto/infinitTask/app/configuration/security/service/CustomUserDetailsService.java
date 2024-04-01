package com.proyecto.infinitTask.app.configuration.security.service;
import com.proyecto.infinitTask.app.entities.Usuario;
import com.proyecto.infinitTask.app.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    // Implementa el método loadUserByUsername para cargar los detalles del usuario
    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        // Implementa la lógica para cargar los detalles del usuario desde tu fuente de datos
        // Puedes cargar los detalles del usuario desde una base de datos, un servicio externo, etc.
        // Aquí solo un ejemplo básico:
        Usuario user = usuarioRepository.findByUsuario(usuario);

        return new org.springframework.security.core.userdetails.User(
                user.getUsuario(),
                user.getPassword(),
                Collections.emptyList()
        );
    }
}
