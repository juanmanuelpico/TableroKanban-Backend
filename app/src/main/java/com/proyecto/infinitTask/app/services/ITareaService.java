package com.proyecto.infinitTask.app.services;

import com.proyecto.infinitTask.app.dtos.request.Tarea.TareaDTORequest;
import com.proyecto.infinitTask.app.dtos.response.Tarea.TareaDTOResponse;

import java.util.List;

public interface ITareaService {

    public boolean crearTarea(TareaDTORequest dto) throws Exception;

    public List<TareaDTOResponse>traerTareas(int idProyecto) throws Exception;

    public List<TareaDTOResponse>buscarTareasIdNombre(int idProyecto, String nombre)throws Exception;
}
