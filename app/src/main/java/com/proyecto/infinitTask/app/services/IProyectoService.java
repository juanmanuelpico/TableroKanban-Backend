package com.proyecto.infinitTask.app.services;

import com.proyecto.infinitTask.app.dtos.response.Proyecto.ProyectoDTOResponse;

import java.util.List;

public interface IProyectoService {

    public List<ProyectoDTOResponse> obtenerProyectosDeUsuario(int idUsuario) throws Exception;
}
