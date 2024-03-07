package com.proyecto.infinitTask.app.services;

import com.proyecto.infinitTask.app.dtos.request.Proyecto.ProyectoDTORequest;
import com.proyecto.infinitTask.app.dtos.response.Proyecto.ProyectoDTOResponse;

import java.time.LocalDate;
import java.util.List;

public interface IProyectoService {

    public boolean crearProyecto (ProyectoDTORequest dtoProyecto);
    public List<ProyectoDTOResponse> obtenerProyectosDeUsuario(int idUsuario) throws Exception;
    public List<ProyectoDTOResponse> obtenerProyectosDeUsuarioDesdeHasta(int idUsuario, LocalDate fechaInicio, LocalDate fechaFin) throws Exception;

    public ProyectoDTOResponse traerProyectoId(int id) throws Exception;

    ProyectoDTOResponse traerProyectoNombre(String nombre) throws Exception;
    public void bajaLogicaProyecto(int id) throws  Exception;
}
