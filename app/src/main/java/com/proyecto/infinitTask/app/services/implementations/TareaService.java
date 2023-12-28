package com.proyecto.infinitTask.app.services.implementations;

import com.proyecto.infinitTask.app.dtos.request.Tarea.TareaDTORequest;
import com.proyecto.infinitTask.app.entities.Tarea;
import com.proyecto.infinitTask.app.repositories.TareaRepository;
import com.proyecto.infinitTask.app.services.ITareaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service("tareaService")
public class TareaService implements ITareaService {

    @Autowired private TareaRepository tareaRepository;

    @Autowired(required = true)
    private ModelMapper modelMapper;

    @Override
    public boolean crearTarea(TareaDTORequest dto) throws Exception {

        Tarea tarea = modelMapper.map(dto, Tarea.class);
        tarea.setFechaCreacion(LocalDate.now());
        tarea.setActivo(true);
        tareaRepository.save(tarea);

        return true;
    }
}
