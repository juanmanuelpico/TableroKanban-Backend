package com.proyecto.infinitTask.app.repositories;

import com.proyecto.infinitTask.app.entities.ProyectoRolUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ProyectoRolUsuarioRepository extends JpaRepository<ProyectoRolUsuario, Integer> {



    @Query(value = "SELECT p.id_proyecto, p.nombre, p.descripcion, p.activo, p.fecha_inicio, p.fecha_fin " +
            "FROM proyecto_rol_usuario pru " +
            "INNER JOIN proyecto p ON pru.id_proyecto = p.id_proyecto " +
            "WHERE pru.id_usuario = :idUsuario " +
            "AND p.fecha_inicio >= :fechaInicio " +
            "AND p.fecha_fin <= :fechaFin", nativeQuery = true)
    List<Object[]> findByUsuarioAndFechaInicioFechaFin(
            @Param("idUsuario") int idUsuario,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin);

    @Query(value = "SELECT * FROM proyecto_rol_usuario pru " +
            "WHERE pru.id_usuario = :id_usuario " +
            "AND pru.id_proyecto = :id_proyecto", nativeQuery = true)
    ProyectoRolUsuario findProyectoRolUsuarioByIdUsuarioAndIdProyecto (@Param("id_usuario") int idUsuario, @Param("id_proyecto") int idProyecto);
}
