package com.proyecto.infinitTask.app.services;

import com.proyecto.infinitTask.app.dtos.request.Usuario.UsuarioDTOLogin;
import com.proyecto.infinitTask.app.dtos.request.Usuario.UsuarioDTORequest;
import com.proyecto.infinitTask.app.dtos.response.Usuario.UsuarioDTOResponse;
import com.proyecto.infinitTask.app.entities.Usuario;

import java.util.List;

public interface IUsuarioService {

    public boolean crearUsuario(UsuarioDTORequest dto) throws Exception;

    public UsuarioDTOResponse traerUsuarioId(int id) throws Exception;

    public Usuario obtenerUsuarioEntidadPorId(int id) throws Exception;

    public UsuarioDTOResponse traerUsuario(String usuario)throws Exception;

    public List<UsuarioDTOResponse> obtenerUsuarios() throws Exception;

    public List<UsuarioDTOResponse> obtenerUsuariosPorNombre(String nombre, int idProyecto) throws Exception;

    public UsuarioDTOResponse traerUsuarioLogin(UsuarioDTOLogin dtoLogin)throws Exception;

    public UsuarioDTOResponse editarUsuario(UsuarioDTORequest dto)throws Exception;

    public List<UsuarioDTOResponse> obtenerUsuariosDeProyecto (int idProyecto) throws Exception;
    //Obtiene todos los usuarios por terminación en el nombre de usuario, de un proyecto que no pertenezcan a esa tarea
    public List<UsuarioDTOResponse> obtenerUsuariosPorTermDeProyectoNoTarea (String usuario, int idProyecto, int idTarea) throws Exception;

    public void asignarUsuarioATarea(int idTarea, int idUsuario) throws Exception;

    void desasignarUsuarioATarea(int idTarea, int idUsuario) throws Exception;

    void desasignarTareasDeUsuarioEnProyecto(int idUsuario, int idProyecto) throws Exception;
}
