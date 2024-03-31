package com.proyecto.infinitTask.app.services;

import com.proyecto.infinitTask.app.dtos.request.ProyectoRolUsuario.ProyectoRolUsuarioDTO;
import com.proyecto.infinitTask.app.entities.Proyecto;
import com.proyecto.infinitTask.app.entities.RolUsuario;
import com.proyecto.infinitTask.app.entities.Usuario;

public interface IProyectoRolUsuarioService {

    public boolean crearProyectoRolUsuario(Proyecto proyecto, Usuario usuario, RolUsuario rolUsuario);

    public boolean agregarUsuarioAProyectoConRol(ProyectoRolUsuarioDTO dto) throws Exception;

    public void eliminarProyectoRolUsuario (int idUsuario, int idProyecto) throws Exception;
}
