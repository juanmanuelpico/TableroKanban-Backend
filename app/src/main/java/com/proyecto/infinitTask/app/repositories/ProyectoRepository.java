package com.proyecto.infinitTask.app.repositories;


import com.proyecto.infinitTask.app.entities.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {

   Proyecto findById(int id);
}

