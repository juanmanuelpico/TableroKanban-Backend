package com.proyecto.infinitTask.app.controllers;

import com.proyecto.infinitTask.app.dtos.request.UsuarioDTORequest;
import com.proyecto.infinitTask.app.dtos.response.UsuarioDTOResponse;
import com.proyecto.infinitTask.app.entities.Usuario;
import com.proyecto.infinitTask.app.services.IUsuarioService;
import com.proyecto.infinitTask.app.util.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<Object> crearUsuario(@RequestBody UsuarioDTORequest dto){
        try{
           usuarioService.crearUsuario(dto);
           return ResponseEntity.status(HttpStatus.CREATED).body(new Mensaje("Usuario creado exitosamente"));
           //ES QUIVALENTE
           //return new ResponseEntity<>(new Mensaje("Usuario creado exitosamente"), HttpStatus.CREATED );

        }catch(Exception e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()) , HttpStatus.BAD_REQUEST);
            //ES QUIVALENTE
           // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(e.getMessage()));
        }
    }

    @GetMapping("/{usuario}")
    public ResponseEntity<Object> obtenerUsuario(@PathVariable String usuario) {

        try{
            UsuarioDTOResponse dtoUsuario = usuarioService.traerUsuario(usuario);
            return ResponseEntity.status(HttpStatus.OK).body(dtoUsuario);
        } catch (Exception e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()) , HttpStatus.BAD_REQUEST);
        }

    }
}
