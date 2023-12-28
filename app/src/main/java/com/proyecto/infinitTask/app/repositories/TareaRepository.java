package com.proyecto.infinitTask.app.repositories;

import com.proyecto.infinitTask.app.entities.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaRepository extends JpaRepository<Tarea, Integer> {

    Tarea findByTitulo(String titulo);

}
