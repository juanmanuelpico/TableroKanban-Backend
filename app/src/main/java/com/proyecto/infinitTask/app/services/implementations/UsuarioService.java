package com.proyecto.infinitTask.app.services.implementations;

import com.proyecto.infinitTask.app.dtos.request.UsuarioDTORequest;
import com.proyecto.infinitTask.app.entities.Usuario;
import com.proyecto.infinitTask.app.repositories.UsuarioRepository;
import com.proyecto.infinitTask.app.services.IUsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service("usuarioService")
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired(required = true)
    private ModelMapper modelMapper;
    @Override
    public boolean crearUsuario(UsuarioDTORequest dto) throws Exception{

        if(usuarioRepository.findByUsuario(dto.getUsuario()) != null) {
            throw new Exception("El nombre del usuario ya existe");
        }

        if(usuarioRepository.findByEmail(dto.getEmail())!= null) {
            throw new Exception("El mail ya se encuentra registrado");
        }

        Usuario usuario = modelMapper.map(dto, Usuario.class);

        usuario.setFechaAlta(LocalDate.now());
        usuario.setActivo(true);
        usuarioRepository.save(usuario);

        return true;
    }
}
