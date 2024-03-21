package com.proyecto.infinitTask.app.repositories;


import com.proyecto.infinitTask.app.entities.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {

   Proyecto findById(int id);
   Proyecto findByNombre(String nombre);

   @Query(value = "SELECT p.* FROM proyecto p " +
           "INNER JOIN proyecto_rol_usuario pru ON p.id_proyecto = pru.id_proyecto " +
           "WHERE pru.id_usuario = :usuarioId AND p.activo = true", nativeQuery = true)
   List<Proyecto> findAllProyectosByUsuarioId(@Param("usuarioId") int usuarioId);

   @Query(value = "SELECT p.* FROM proyecto p " +
           "INNER JOIN proyecto_rol_usuario pru ON p.id_proyecto = pru.id_proyecto " +
           "WHERE pru.id_usuario = :id_usuario AND p.activo = true AND p.nombre LIKE %:nombre_proyecto%", nativeQuery = true)
   List<Proyecto> findProyectosByUsuarioAndNombreProyecto(@Param("id_usuario") int idUsuario, @Param("nombre_proyecto") String nombreProyecto);

}


