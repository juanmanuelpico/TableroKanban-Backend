package com.proyecto.infinitTask.app.services.implementations;

import com.proyecto.infinitTask.app.dtos.request.Proyecto.ProyectoDTORequest;
import com.proyecto.infinitTask.app.dtos.request.ProyectoRolUsuario.ProyectoRolUsuarioDTO;
import com.proyecto.infinitTask.app.dtos.response.Proyecto.ProyectoDTOResponse;
import com.proyecto.infinitTask.app.entities.Proyecto;
import com.proyecto.infinitTask.app.repositories.ProyectoRepository;
import com.proyecto.infinitTask.app.repositories.ProyectoRolUsuarioRepository;
import com.proyecto.infinitTask.app.repositories.RolUsuarioRepository;
import com.proyecto.infinitTask.app.repositories.UsuarioRepository;
import com.proyecto.infinitTask.app.services.IProyectoRolUsuarioService;
import com.proyecto.infinitTask.app.services.IProyectoService;
import com.proyecto.infinitTask.app.services.IUsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("proyectoService")
public class ProyectoService implements IProyectoService {

    @Autowired(required = true)
    private ModelMapper modelMapper;

    //Se pone el  repository de la tabla intermedia, ya que aca se encuentra la query que trae todos los
    //Proyectos de un Usuario
    @Autowired
    private ProyectoRolUsuarioRepository proyectoRolUsuarioRepository;

    //Se coloca usuario repository ya que lo necesitamos para validar si el id del usuario existe
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    @Lazy
    private IProyectoRolUsuarioService proyectoRolUsuarioService;

    @Autowired
    private RolUsuarioRepository rolUsuarioRepository;

    @Autowired
    @Lazy
    private IUsuarioService usuarioService;

    @Override
    public boolean crearProyecto(ProyectoDTORequest dtoProyecto) {

        Proyecto proyecto = new Proyecto();

        proyecto.setNombre(dtoProyecto.getNombre());
        proyecto.setDescripcion(dtoProyecto.getDescripcion());

        proyecto.setFechaInicio(LocalDate.now());
        proyecto.setActivo(true);
        //se guarda el proyecto en la bd
        proyectoRepository.save(proyecto);

        //se llama al metodo que crea la relacion entre el usuario rol y proyecto.
        proyectoRolUsuarioService.crearProyectoRolUsuario(proyectoRepository.findByNombre(dtoProyecto.getNombre()), usuarioRepository.findById(dtoProyecto.getIdUsuario()), rolUsuarioRepository.findById(1));

        return true;
    }

    @Override
    public Proyecto obtenerProyectoEntidadPorId(int id) throws Exception {
        Proyecto proyecto = proyectoRepository.findById(id);
        if(proyecto == null){
            throw new Exception("El proyecto con id: " +id+ ", no existe");
        }
        return proyecto;
    }

    @Override
    public List<ProyectoDTOResponse> obtenerProyectosDeUsuario(int idUsuario) throws Exception {
        if (usuarioRepository.findById(idUsuario) == null) {
            throw new Exception("El usuario con id " + idUsuario + " No existe");
        }
        List<Proyecto> proyectos = proyectoRepository.findAllProyectosByUsuarioId(idUsuario);
        List<ProyectoDTOResponse> dtos = proyectos.stream().map(proyecto -> modelMapper.map(proyecto, ProyectoDTOResponse.class)).collect(Collectors.toList());

        return dtos;
    }

    @Override
    public List<ProyectoDTOResponse> buscarProyectosPorNombre(int idUsuario, String nombreProyecto) throws Exception {
        if (usuarioRepository.findById(idUsuario) == null) {
            throw new Exception("El usuario con id " + idUsuario + " No existe");
        }
        List<Proyecto> proyectos = proyectoRepository.findProyectosByUsuarioAndNombreProyecto(idUsuario, nombreProyecto);

        List<ProyectoDTOResponse> dtos = proyectos.stream().map(proyecto -> modelMapper.map(proyecto, ProyectoDTOResponse.class)).collect(Collectors.toList());

        return dtos;
    }

    @Override
    public List<ProyectoDTOResponse> obtenerProyectosDeUsuarioDesdeHasta(int idUsuario, LocalDate fechaInicio, LocalDate fechaFin) throws Exception {

        List<Proyecto> proyectosEnt = this.convertirObjetosEnProyectos(proyectoRolUsuarioRepository.findByUsuarioAndFechaInicioFechaFin(idUsuario, fechaInicio, fechaFin));
        if (proyectosEnt.isEmpty()) {
            throw new Exception("No hay proyectos en las fechas indicadas");
        }

        List<ProyectoDTOResponse> proyectosDTO = new ArrayList<>();
        for (Proyecto p : proyectosEnt) {
            proyectosDTO.add(modelMapper.map(p, ProyectoDTOResponse.class));

        }
        return proyectosDTO;
    }

    public List<Proyecto> convertirObjetosEnProyectos(List<Object[]> objects) {
        List<Proyecto> proyectos = new ArrayList<>();
        for (Object[] object : objects) {

            Proyecto proyecto = new Proyecto();
            proyecto.setId((Integer) object[0]);
            proyecto.setNombre((String) object[1]);
            proyecto.setDescripcion((String) object[2]);
            proyecto.setActivo((Boolean) object[3]);

            // Corrige la conversión de java.sql.Date a java.time.LocalDate
            if (object[4] != null) {
                proyecto.setFechaInicio(((java.sql.Date) object[4]).toLocalDate());
            }
            if (object[5] != null) {
                proyecto.setFechaFin(((java.sql.Date) object[5]).toLocalDate());
            }
            proyectos.add(proyecto);
        }
        return proyectos;

    }

    public ProyectoDTOResponse traerProyectoId(int id) throws Exception {

        Proyecto proyecto= proyectoRepository.findById(id);

        if(proyecto == null)throw new Exception("ERROR: No se encontro el proyecto con id:" + id);

        return modelMapper.map(proyecto, ProyectoDTOResponse.class);

    }

    @Override
    public ProyectoDTOResponse traerProyectoNombre(String nombre) throws Exception {

        Proyecto proyecto = proyectoRepository.findByNombre(nombre);

        if(proyecto == null) {
            throw new Exception("No se encontro el proyecto '" + nombre + "'.");
        }

        return modelMapper.map(proyecto, ProyectoDTOResponse.class);
    }

    @Override
    public void bajaLogicaProyecto(int id) throws Exception {
        Proyecto proyecto = proyectoRepository.findById(id);
        if(proyecto == null){
            throw  new Exception("No se encontro el proyecto con id: "+id+".");
        }
        proyecto.setActivo(!proyecto.isActivo());
        proyecto.setFechaFin(LocalDate.now());
        proyectoRepository.save(proyecto);
    }

    @Override
    public void editarNombreDescripcionProyecto(int id, String nombre, String descripcion) throws Exception {
        Proyecto proyecto = proyectoRepository.findById(id);
        if(proyecto == null){
            throw  new Exception("No se encontro el proyecto con id: "+id+".");
        }
        proyecto.setNombre(nombre);
        proyecto.setDescripcion(descripcion);
        proyectoRepository.save(proyecto);
    }

    @Override
    public boolean agregarUsuarioAProyectoConRol(ProyectoRolUsuarioDTO dto) throws Exception {
        return proyectoRolUsuarioService.agregarUsuarioAProyectoConRol(dto);
    }

    @Override
    public void eliminarUsuarioDeProyectoYdeTareas(int idUsuario, int idProyecto) throws Exception{
        //proyectoRolUsuarioService.eliminarProyectoRolUsuario(idUsuario, idProyecto);
        usuarioService.desasignarTareasDeUsuarioEnProyecto(idUsuario, idProyecto);
    }

}
