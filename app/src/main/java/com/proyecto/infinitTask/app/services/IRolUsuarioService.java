package com.proyecto.infinitTask.app.services;

import com.proyecto.infinitTask.app.dtos.response.RolUsuario.RolUsuarioDTOResponse;
import com.proyecto.infinitTask.app.entities.RolUsuario;

import java.util.List;

public interface IRolUsuarioService {

    public List<RolUsuarioDTOResponse> obtenerRolesUsuarios() throws Exception;

    public RolUsuario obtenerRolEntidadPorId (int id) throws Exception;

}
