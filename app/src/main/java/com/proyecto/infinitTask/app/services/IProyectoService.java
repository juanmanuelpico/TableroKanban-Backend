package com.proyecto.infinitTask.app.services;

import com.proyecto.infinitTask.app.dtos.request.Proyecto.ProyectoDTORequest;
import com.proyecto.infinitTask.app.dtos.request.ProyectoRolUsuario.ProyectoRolUsuarioDTO;
import com.proyecto.infinitTask.app.dtos.response.Proyecto.ProyectoDTOResponse;
import com.proyecto.infinitTask.app.entities.Proyecto;

import java.time.LocalDate;
import java.util.List;

public interface IProyectoService {

    public boolean crearProyecto (ProyectoDTORequest dtoProyecto);

    public Proyecto obtenerProyectoEntidadPorId(int id) throws Exception;
    public List<ProyectoDTOResponse> obtenerProyectosDeUsuario(int idUsuario) throws Exception;
    public List<ProyectoDTOResponse> obtenerProyectosDeUsuarioDesdeHasta(int idUsuario, LocalDate fechaInicio, LocalDate fechaFin) throws Exception;
    public List<ProyectoDTOResponse> buscarProyectosPorNombre(int idUsuario, String nombreProyecto) throws Exception;
    public ProyectoDTOResponse traerProyectoId(int id) throws Exception;

    ProyectoDTOResponse traerProyectoNombre(String nombre) throws Exception;
    public void bajaLogicaProyecto(int id) throws  Exception;

    public void editarNombreDescripcionProyecto(int id, String nombre, String descripcion) throws Exception;

    public boolean agregarUsuarioAProyectoConRol(ProyectoRolUsuarioDTO dto) throws Exception;

    void eliminarUsuarioDeProyectoYdeTareas(int idUsuario, int idProyecto) throws Exception;
}
