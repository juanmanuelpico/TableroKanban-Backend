package com.proyecto.infinitTask.app.repositories;

import com.proyecto.infinitTask.app.entities.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Integer> {

    Tarea findByTitulo(String titulo);

    @Query(value = "SELECT * FROM tarea t  WHERE t.id_proyecto = :id", nativeQuery = true)
    List<Tarea>findByIdProyecto(@Param("id") int id);

}
