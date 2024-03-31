package com.proyecto.infinitTask.app.services.implementations;

import com.proyecto.infinitTask.app.dtos.request.Tarea.TareaDTORequest;
import com.proyecto.infinitTask.app.dtos.request.Tarea.TareaEstadoDTORequest;
import com.proyecto.infinitTask.app.dtos.response.Tarea.TareaDTOResponse;
import com.proyecto.infinitTask.app.dtos.response.Usuario.UsuarioDTOResponse;
import com.proyecto.infinitTask.app.entities.Proyecto;
import com.proyecto.infinitTask.app.entities.Tarea;
import com.proyecto.infinitTask.app.entities.Usuario;
import com.proyecto.infinitTask.app.repositories.ProyectoRepository;
import com.proyecto.infinitTask.app.repositories.TareaRepository;
import com.proyecto.infinitTask.app.services.ITareaService;
import com.proyecto.infinitTask.app.services.IUsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("tareaService")
public class TareaService implements ITareaService {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired(required = true)
    private ModelMapper modelMapper;

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private IUsuarioService usuarioService;

    @Override
    public boolean crearTarea(TareaDTORequest dto) throws Exception { //FALTA VALIDAR
        Proyecto proyecto = proyectoRepository.findById(dto.getIdProyecto());
        Tarea tarea = new Tarea();

        tarea.setTitulo(dto.getTitulo());
        tarea.setActivo(true);
        tarea.setDescripcion(dto.getDescripcion());
        tarea.setDificultad("Baja");
        tarea.setEstado("PARA HACER");
        tarea.setFechaCreacion(LocalDate.now());
        tarea.setFechaInicio(LocalDate.now());
        tarea.setProyecto(proyecto);
        
        tareaRepository.save(tarea);

        return true;
    }

    @Override
    public TareaDTOResponse traerTareaPorId(int idTarea) throws Exception {
        Tarea tarea = tareaRepository.findById(idTarea);
        if(tarea == null) {
            throw  new Exception("No se encontro la tarea con id: "+idTarea+".");
        }
        TareaDTOResponse tareaDTO = modelMapper.map(tarea, TareaDTOResponse.class);
        tareaDTO.setUsuarios(tarea.getUsuarios().stream().map(usuario -> modelMapper.map(usuario, UsuarioDTOResponse.class)).collect(Collectors.toList()));
        return tareaDTO;
    }

    @Override
    public Tarea traerTareaEntidadPorId(int idTarea) throws Exception {
       Tarea tarea = tareaRepository.findById(idTarea);
       if(tarea == null){
           throw new Exception("No existe la tarea con id: "+idTarea);
       }
       return tarea;
    }

    public List<TareaDTOResponse> traerTareas(int idProyecto) throws Exception {

        List<TareaDTOResponse> tareasDto = new ArrayList<>();
        List<Tarea> tareas = tareaRepository.findByIdProyectoAndActive(idProyecto);

        for (Tarea t : tareas) {
            TareaDTOResponse dto = modelMapper.map(t, TareaDTOResponse.class);
            dto.setCantUsuarios(tareaRepository.countUsuariosByTareaId(t.getId()));
            tareasDto.add(dto);
        }
        return tareasDto;

    }

    @Override
    public List<TareaDTOResponse> buscarTareasIdNombre(int idProyecto, String nombre) throws Exception {
        List<TareaDTOResponse> tareasDto = new ArrayList<>();
        List<Tarea> tareas = tareaRepository.findByIdProyectoNombreTarea(idProyecto, nombre);

        for (Tarea t : tareas) {
            tareasDto.add(modelMapper.map(t, TareaDTOResponse.class));
        }
        return tareasDto;
    }

    @Override
    public void bajaLogicaTarea(int id) throws Exception {
        Tarea tarea = (Tarea) tareaRepository.findById(id);
        if(tarea == null){
            throw  new Exception("No se encontro la tarea con id: "+id+".");
        }
        tarea.setActivo(!tarea.isActivo());
        tarea.setFechaFin(LocalDate.now());
        tareaRepository.save(tarea);
    }

    @Override
    public void editarTarea(TareaDTORequest dto) throws Exception {
        Tarea tarea = tareaRepository.findById(dto.getIdTarea());
        if(tarea == null){
            throw new Exception("No se encontro la tarea con id: "+dto.getIdTarea()+".");
        }
        tarea.setTitulo(dto.getTitulo());
        tarea.setDescripcion(dto.getDescripcion());
        tarea.setFechaActualizacion(LocalDate.now());
        tarea.setFechaFin((dto.getFechaFin()));
        tarea.setEstado(dto.getEstado());
        tarea.setDificultad(dto.getDificultad());

        tareaRepository.save(tarea);
    }

    @Override
    public void cambiarEstado(TareaEstadoDTORequest dto) throws Exception {
        Tarea tarea = tareaRepository.findById(dto.getIdTarea());
        tarea.setEstado(dto.getEstado());
        tareaRepository.save(tarea);
    }

    @Override
    public List<Tarea> obtenerTareasDeUsuarioEnProyecto(int idUsuario, int idProyecto) throws Exception {
        return tareaRepository.findTareasByIdProyectoAndIdUsuario(idUsuario, idProyecto);
    }


}
