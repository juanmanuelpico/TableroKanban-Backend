package com.proyecto.infinitTask.app.services;


import com.proyecto.infinitTask.app.dtos.request.UsuarioDTORequest;

public interface IUsuarioService {

    public boolean crearUsuario(UsuarioDTORequest dto) throws Exception;
}
