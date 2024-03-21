package com.proyecto.infinitTask.app.services.implementations;

import com.proyecto.infinitTask.app.dtos.request.ProyectoRolUsuario.ProyectoRolUsuarioDTO;
import com.proyecto.infinitTask.app.entities.Proyecto;
import com.proyecto.infinitTask.app.entities.ProyectoRolUsuario;
import com.proyecto.infinitTask.app.entities.RolUsuario;
import com.proyecto.infinitTask.app.entities.Usuario;
import com.proyecto.infinitTask.app.repositories.ProyectoRolUsuarioRepository;
import com.proyecto.infinitTask.app.services.IProyectoRolUsuarioService;
import com.proyecto.infinitTask.app.services.IProyectoService;
import com.proyecto.infinitTask.app.services.IRolUsuarioService;
import com.proyecto.infinitTask.app.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("proyectoRolUsuarioService")
public class ProyectoRolUsuarioService implements IProyectoRolUsuarioService {

    @Autowired
    private ProyectoRolUsuarioRepository proyectoRolUsuarioRepository;
    @Autowired
    private IProyectoService proyectoService;
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private IRolUsuarioService rolUsuarioService;

    @Override
    public boolean crearProyectoRolUsuario(Proyecto proyecto, Usuario usuario, RolUsuario rolUsuario) {

        ProyectoRolUsuario pru = new ProyectoRolUsuario();

        pru.setUsuario(usuario);
        pru.setProyecto(proyecto);
        pru.setRolUsuario(rolUsuario);

        proyectoRolUsuarioRepository.save(pru);

        return true;
    }

    //Se crea un nuevo registro en la tabla
    public boolean agregarUsuarioAProyectoConRol(ProyectoRolUsuarioDTO dto) throws Exception{

        if(proyectoRolUsuarioRepository.findByUsuarioAndProyecto(dto.getIdUsuario(), dto.getIdProyecto()) !=null){
            throw new Exception("ERROR: Ya existe el usuario en el proyecto");
        }

        Usuario usuario = usuarioService.obtenerUsuarioEntidadPorId(dto.getIdUsuario());
        Proyecto proyecto = proyectoService.obtenerProyectoEntidadPorId(dto.getIdProyecto());
        RolUsuario rolUsuario = rolUsuarioService.obtenerRolEntidadPorId(dto.getIdRolUsuario());

        ProyectoRolUsuario proyectoRolUsuario = new ProyectoRolUsuario();

        proyectoRolUsuario.setProyecto(proyecto);
        proyectoRolUsuario.setRolUsuario(rolUsuario);
        proyectoRolUsuario.setUsuario(usuario);

        proyectoRolUsuarioRepository.save(proyectoRolUsuario);

        return true;
    }
}
