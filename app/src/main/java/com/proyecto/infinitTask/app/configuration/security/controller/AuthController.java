package com.proyecto.infinitTask.app.configuration.security.controller;

import com.proyecto.infinitTask.app.configuration.security.service.AuthService;
import com.proyecto.infinitTask.app.dtos.request.Usuario.UsuarioDTOLogin;
import com.proyecto.infinitTask.app.dtos.response.Usuario.UsuarioDTOWithToken;
import com.proyecto.infinitTask.app.services.IUsuarioService;
import com.proyecto.infinitTask.app.util.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private final AuthService authService;

    @Autowired
    private IUsuarioService usuarioService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /*@PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        // Implementar lógica de registro aquí
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
    }*/

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UsuarioDTOLogin dtoLogin) {
        try {
            // Autenticar usuario
            UsuarioDTOWithToken usuarioResponse = usuarioService.traerUsuarioLogin(dtoLogin);
            if (usuarioResponse == null) {
                return new ResponseEntity<>(new Mensaje("Credenciales inválidas"), HttpStatus.UNAUTHORIZED);
            }

            // Generar token JWT
            String token = authService.generateJwtToken(dtoLogin.getUsuario());

            // Devolver UsuarioDTO junto con el token JWT
            usuarioResponse.setToken(token);
            return ResponseEntity.ok(usuarioResponse);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /*@PostMapping("/logout")
    public ResponseEntity<String> logoutUser() {
        // Implementar lógica de cierre de sesión aquí (en el lado del cliente)
        return ResponseEntity.ok("User logged out successfully.");
   }*/
}