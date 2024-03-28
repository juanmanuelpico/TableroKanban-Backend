package com.proyecto.infinitTask.app.repositories;

import com.proyecto.infinitTask.app.entities.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Integer> {

    Tarea findByTitulo(String titulo);

    Tarea findById(int id);

    @Query(value = "SELECT * FROM tarea t  WHERE t.id_proyecto = :id", nativeQuery = true)
    List<Tarea> findByIdProyecto(@Param("id") int id);

    @Query(value = "SELECT * FROM tarea t  WHERE t.id_proyecto = :id AND t.activo = true", nativeQuery = true)
    List<Tarea> findByIdProyectoAndActive(@Param("id") int id);

    @Query(value = "SELECT * FROM tarea t  WHERE t.id_proyecto = :id AND t.nombre LIKE %:nombre_tarea% AND t.activo = true", nativeQuery = true)
    List<Tarea> findByIdProyectoNombreTarea(@Param("id") int id, @Param("nombre_tarea") String nombreTarea);

    @Query(value = "SELECT COUNT(ut.id_usuario) FROM usuario_tiene_tarea ut WHERE ut.id_tarea = :id_tarea", nativeQuery = true)
    int countUsuariosByTareaId(@Param("id_tarea") int idTarea);
}

