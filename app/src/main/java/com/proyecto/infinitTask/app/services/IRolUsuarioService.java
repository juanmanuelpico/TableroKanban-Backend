package com.proyecto.infinitTask.app.services;

import com.proyecto.infinitTask.app.dtos.response.RolUsuario.RolUsuarioDTOResponse;

import java.util.List;

public interface IRolUsuarioService {

    public List<RolUsuarioDTOResponse> obtenerRolesUsuarios() throws Exception;

}
