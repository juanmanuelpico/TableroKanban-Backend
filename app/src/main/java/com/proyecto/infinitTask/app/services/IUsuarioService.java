package com.proyecto.infinitTask.app.services;


import com.proyecto.infinitTask.app.dtos.request.UsuarioDTORequest;
import com.proyecto.infinitTask.app.dtos.response.UsuarioDTOResponse;

public interface IUsuarioService {

    public boolean crearUsuario(UsuarioDTORequest dto) throws Exception;
    public UsuarioDTOResponse traerUsuarioId(int id)throws Exception;
    public UsuarioDTOResponse traerUsuario(String usuario)throws Exception;
}
