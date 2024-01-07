package com.proyecto.infinitTask.app.repositories;


import com.proyecto.infinitTask.app.entities.RolUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolUsuarioRepository extends JpaRepository<RolUsuario, Integer>{

    List<RolUsuario> findAll();
}