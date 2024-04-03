package com.proyecto.infinitTask.app.controllers;

import com.proyecto.infinitTask.app.dtos.request.Usuario.UsuarioDTOLogin;
import com.proyecto.infinitTask.app.dtos.response.Usuario.UsuarioDTOResponse;
import com.proyecto.infinitTask.app.security.jwt.TokenUtils;
import com.proyecto.infinitTask.app.services.IUsuarioService;
import com.proyecto.infinitTask.app.util.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:3000")

public class LoginController {
    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping("")
    public ResponseEntity<Object> login(@RequestBody UsuarioDTOLogin dtoLogin){
        try{
            UsuarioDTOResponse response = usuarioService.traerUsuarioLogin(dtoLogin);
            // Generar un nuevo token JWT
            String nuevoToken = TokenUtils.createToken(response.getUsuario());
            response.setToken(nuevoToken);
            // Devolver el nuevo token junto con los atributos del usuario en la respuesta
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}