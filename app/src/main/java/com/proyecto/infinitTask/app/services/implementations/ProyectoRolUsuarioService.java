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

    public void eliminarProyectoRolUsuario(int idUsuario, int idProyecto) throws Exception{
        ProyectoRolUsuario pru = proyectoRolUsuarioRepository.findProyectoRolUsuarioByIdUsuarioAndIdProyecto(idUsuario, idProyecto);
        if(pru == null) {
            throw new Exception("Registro pru inexistente");
        }
        proyectoRolUsuarioRepository.delete(pru);
    }
}
