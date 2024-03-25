package com.proyecto.infinitTask.app.services;

import com.proyecto.infinitTask.app.dtos.request.Tarea.TareaDTORequest;
import com.proyecto.infinitTask.app.dtos.request.Tarea.TareaEstadoDTORequest;
import com.proyecto.infinitTask.app.dtos.response.Tarea.TareaDTOResponse;
import com.proyecto.infinitTask.app.entities.Tarea;

import java.util.List;

public interface ITareaService {

    public boolean crearTarea(TareaDTORequest dto) throws Exception;

    public TareaDTOResponse traerTareaPorId(int idTarea) throws Exception;

    public Tarea traerTareaEntidadPorId(int idTarea) throws Exception;

    public List<TareaDTOResponse>traerTareas(int idProyecto) throws Exception;

    public List<TareaDTOResponse>buscarTareasIdNombre(int idProyecto, String nombre)throws Exception;

    public void bajaLogicaTarea(int id) throws Exception;

    public void editarTarea (TareaDTORequest dto) throws Exception;

    public void cambiarEstado (TareaEstadoDTORequest dto) throws Exception;
}
