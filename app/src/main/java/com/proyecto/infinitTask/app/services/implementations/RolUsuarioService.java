package com.proyecto.infinitTask.app.services.implementations;

import com.proyecto.infinitTask.app.dtos.response.RolUsuario.RolUsuarioDTOResponse;
import com.proyecto.infinitTask.app.entities.RolUsuario;
import com.proyecto.infinitTask.app.repositories.RolUsuarioRepository;
import com.proyecto.infinitTask.app.services.IRolUsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("rolUsuarioService")
public class RolUsuarioService implements IRolUsuarioService {

    @Autowired
    private RolUsuarioRepository rolUsuarioRepository;

    @Autowired(required = true)
    private ModelMapper modelMapper;

    // Trae la Lista de Roles de los usuarios.
    // Si está vacía throws exception: "La Lista de roles está vacia".
    // For each ru del tipo RolUsuario en listaRolUsuarioEnt, agrega a listaRolUsuarioDto el modelMapper de ru y RolUsuarioDTOResponse

    @Override
    public List<RolUsuarioDTOResponse> obtenerRolesUsuarios() throws Exception{

       List<RolUsuarioDTOResponse> listaRolUsuarioDto = new ArrayList<>();
       List<RolUsuario> listaRolUsuarioEnt = rolUsuarioRepository.findAll();

       if(listaRolUsuarioEnt.isEmpty()) {
           throw new Exception("La lista de roles esta vacía.");
       }

       for(RolUsuario ru: listaRolUsuarioEnt){
           listaRolUsuarioDto.add(modelMapper.map(ru, RolUsuarioDTOResponse.class));
       }

       return listaRolUsuarioDto;
    }
}
