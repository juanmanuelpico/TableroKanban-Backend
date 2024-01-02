package com.proyecto.infinitTask.app.repositories;

import com.proyecto.infinitTask.app.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findById(int id);

    Usuario findByUsuario(String usuario);

    Usuario findByEmail(String email);

    List<Usuario> findAll();

    @Query(value = "SELECT * FROM usuario u WHERE BINARY u.usuario = :usuario AND u.password = :password", nativeQuery = true)
    Usuario findByUsuarioAndPasswordCaseSensitive(@Param("usuario") String usuario, @Param("password") String password);

}
