package com.proyecto.infinitTask.app.repositories;


import com.proyecto.infinitTask.app.entities.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {

   Proyecto findById(int id);
   Proyecto findByNombre(String nombre);


}
