package com.proyecto.infinitTask.app.repositories;

import com.proyecto.infinitTask.app.entities.Proyecto;
import com.proyecto.infinitTask.app.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findById(int id);

    Usuario findByUsuario(String usuario);

    Optional<Usuario> findOneByUsuario(String usuario);

    Usuario findByEmail(String email);

    List<Usuario> findAll();

    //OBTENER A LOS USUARIOS POR Nombre

    @Query(value = "SELECT * FROM usuario u WHERE u.usuario LIKE %:usuario%", nativeQuery = true)
    List<Usuario> findAllByUsuario(@Param("usuario") String usuario);

    @Query(value = "SELECT * FROM usuario u WHERE u.usuario LIKE %:usuario% AND u.id_usuario NOT IN (SELECT pru.id_usuario FROM proyecto_rol_usuario pru WHERE pru.id_proyecto = :proyectoId)", nativeQuery = true)
    List<Usuario> findAllUsuariosNotInProyecto(@Param("usuario") String usuario, @Param("proyectoId") int proyectoId);

    @Query(value = "SELECT * FROM usuario u WHERE BINARY u.usuario = :usuario AND u.password = :password", nativeQuery = true)
    Usuario findByUsuarioAndPasswordCaseSensitive(@Param("usuario") String usuario, @Param("password") String password);

    @Query(value = "SELECT u.* FROM usuario u " +
            "INNER JOIN proyecto_rol_usuario pru ON u.id_usuario = pru.id_usuario " +
            "WHERE pru.id_proyecto = :id_proyecto AND u.activo = true", nativeQuery = true)
    List<Usuario> findUsuariosByProyecto(@Param("id_proyecto") int idProyecto);

    //traer a todos los usuarios de un proyecto, pero que además no estén asignados a esa tarea
    //Obtiene un listado de usuarios, cada usuario del listado debe tener en su nombre de usuario, la terminación por la que se lo está buscando.
    //Luego compara que el proyecto al que pertenece sea el solicitado, además el usuario tiene que ser un usuario activo
    //Por ulitmo verifica que el id del usuario, no se encuentre registrado en la tabla intermedia con el id de la misma tarea sobre la que se solicita.
    @Query(value = "SELECT u.* FROM usuario u " +
            "INNER JOIN proyecto_rol_usuario pru ON u.id_usuario = pru.id_usuario " +
            "WHERE u.usuario LIKE %:usuario% AND pru.id_proyecto = :id_proyecto AND u.activo = true " +
            "AND u.id_usuario NOT IN (SELECT utt.id_usuario FROM usuario_tiene_tarea utt WHERE utt.id_tarea = :id_tarea)", nativeQuery = true)
    List<Usuario> findUsuariosByProyectoNotInTarea(@Param("usuario") String usuario, @Param("id_proyecto") int idProyecto, @Param("id_tarea") int idTarea);
}
