package com.proyecto.infinitTask.app.services;

import com.proyecto.infinitTask.app.dtos.request.Tarea.TareaDTORequest;

public interface ITareaService {

    public boolean crearTarea(TareaDTORequest dto) throws Exception;

}
