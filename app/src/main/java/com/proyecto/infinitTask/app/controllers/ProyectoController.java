package com.proyecto.infinitTask.app.controllers;

import com.proyecto.infinitTask.app.dtos.response.Proyecto.ProyectoDTOResponse;
import com.proyecto.infinitTask.app.entities.Proyecto;
import com.proyecto.infinitTask.app.repositories.ProyectoRolUsuarioRepository;
import com.proyecto.infinitTask.app.services.IProyectoService;
import com.proyecto.infinitTask.app.util.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/proyecto")
public class ProyectoController {

    @Autowired
    private IProyectoService proyectoService;

    @GetMapping("/traerProyectos/{idUsuario}")
    public ResponseEntity<Object> obtenerProyectosDeUsuario(@PathVariable int idUsuario){
        try{

            List<ProyectoDTOResponse> dtos = proyectoService.obtenerProyectosDeUsuario(idUsuario);

            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        }catch(Exception e){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(e.getMessage()));
        }
    }


}
