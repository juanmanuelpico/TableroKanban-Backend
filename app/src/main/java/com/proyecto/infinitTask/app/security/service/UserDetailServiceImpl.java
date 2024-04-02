package com.proyecto.infinitTask.app.security.service;

import com.proyecto.infinitTask.app.entities.Usuario;
import com.proyecto.infinitTask.app.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername (String user) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository
                .findOneByUsuario(user)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario "+user+" no existe."));

        return new UserDetailImpl(usuario);
    }
}
