package com.proyecto.infinitTask.app.controllers;

import com.proyecto.infinitTask.app.entities.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    /*@GetMapping("/nuevo")
    public Usuario obtenerUsuario(){
        return new Usuario(1, "Cristian", "contra", "ADMIN", "email@mail.com", LocalDate.now(),  LocalDate.now(),  LocalDate.now(), true);
    }*/
}
