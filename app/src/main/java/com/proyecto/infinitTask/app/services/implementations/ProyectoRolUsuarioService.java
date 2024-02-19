package com.proyecto.infinitTask.app.services.implementations;

import com.proyecto.infinitTask.app.entities.Proyecto;
import com.proyecto.infinitTask.app.entities.ProyectoRolUsuario;
import com.proyecto.infinitTask.app.entities.RolUsuario;
import com.proyecto.infinitTask.app.entities.Usuario;
import com.proyecto.infinitTask.app.repositories.ProyectoRolUsuarioRepository;
import com.proyecto.infinitTask.app.services.IProyectoRolUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("proyectoRolUsuarioService")
public class ProyectoRolUsuarioService implements IProyectoRolUsuarioService {

    @Autowired
    private ProyectoRolUsuarioRepository proyectoRolUsuarioRepository;

    @Override
    public boolean crearProyectoRolUsuario(Proyecto proyecto, Usuario usuario, RolUsuario rolUsuario) {

        ProyectoRolUsuario pru = new ProyectoRolUsuario();

        pru.setUsuario(usuario);
        pru.setProyecto(proyecto);
        pru.setRolUsuario(rolUsuario);

        proyectoRolUsuarioRepository.save(pru);

        return true;
    }
}
