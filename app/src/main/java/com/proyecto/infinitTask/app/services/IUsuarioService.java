package com.proyecto.infinitTask.app.services;


import com.proyecto.infinitTask.app.dtos.request.Usuario.UsuarioDTOLogin;
import com.proyecto.infinitTask.app.dtos.request.Usuario.UsuarioDTORequest;
import com.proyecto.infinitTask.app.dtos.response.Usuario.UsuarioDTOResponse;

import java.util.List;

public interface IUsuarioService {

    public boolean crearUsuario(UsuarioDTORequest dto) throws Exception;

    public UsuarioDTOResponse traerUsuarioId(int id)throws Exception;

    public UsuarioDTOResponse traerUsuario(String usuario)throws Exception;

    public List<UsuarioDTOResponse> obtenerUsuarios() throws Exception;

    public UsuarioDTOResponse traerUsuarioLogin(UsuarioDTOLogin dtoLogin)throws Exception;


}
