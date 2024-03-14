package com.proyecto.infinitTask.app.repositories;


import com.proyecto.infinitTask.app.entities.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {

   Proyecto findById(int id);
   Proyecto findByNombre(String nombre);

}
