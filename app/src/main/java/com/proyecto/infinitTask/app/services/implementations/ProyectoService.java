package com.proyecto.infinitTask.app.services.implementations;

import com.proyecto.infinitTask.app.dtos.response.Proyecto.ProyectoDTOResponse;
import com.proyecto.infinitTask.app.entities.Proyecto;
import com.proyecto.infinitTask.app.repositories.ProyectoRolUsuarioRepository;
import com.proyecto.infinitTask.app.repositories.UsuarioRepository;
import com.proyecto.infinitTask.app.services.IProyectoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

   @Override
    public List<ProyectoDTOResponse> obtenerProyectosDeUsuario(int idUsuario) throws Exception {

        if(usuarioRepository.findById(idUsuario) == null){
            throw new Exception("El usuario con id "+ idUsuario +" No existe");
        }

       List<Proyecto> proyectos = this.convertirObjetosEnProyectos(proyectoRolUsuarioRepository.findProyectosByUsuario(idUsuario));

       if(proyectos.isEmpty()){
           throw new Exception("No existe ningun proyecto para el usuario");
       }
        //esta linea convierte el listado de proyectos en dto
        List<ProyectoDTOResponse> dtos = proyectos.stream().map(proyecto -> modelMapper.map(proyecto, ProyectoDTOResponse.class)).collect(Collectors.toList());

       return dtos;
    }

    public List<Proyecto> convertirObjetosEnProyectos( List<Object[]> objects){
       List<Proyecto> proyectos = new ArrayList<>();
        for(Object[] object : objects){

            Proyecto proyecto = new Proyecto();
            proyecto.setId((Integer)object[0]);
            proyecto.setNombre((String)object[1]);
            proyecto.setDescripcion((String)object[2]);
            proyecto.setActivo((Boolean)object[3]);
            proyecto.setFechaInicio((LocalDateTime)object[4]);
            proyecto.setFechaFin((LocalDateTime)object[5]);

            proyectos.add(proyecto);
        }
        return proyectos;
    }
}
