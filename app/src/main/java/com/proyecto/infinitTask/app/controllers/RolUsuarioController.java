package com.proyecto.infinitTask.app.controllers;

import com.proyecto.infinitTask.app.dtos.response.RolUsuario.RolUsuarioDTOResponse;
import com.proyecto.infinitTask.app.services.IRolUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.proyecto.infinitTask.app.util.Mensaje;

import java.util.List;

@RestController
@RequestMapping("/rolUsuario")
public class RolUsuarioController {

    @Autowired
    private IRolUsuarioService RolUsuarioService;

    @GetMapping("/obtenerRolesUsuarios")
    public ResponseEntity<Object> obtenerRolesUsuarios(){

        try{
            List<RolUsuarioDTOResponse> listaDto = RolUsuarioService.obtenerRolesUsuarios();
            return ResponseEntity.status(HttpStatus.OK).body(listaDto);
        } catch (Exception e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()) , HttpStatus.BAD_REQUEST);
        }
    }

}