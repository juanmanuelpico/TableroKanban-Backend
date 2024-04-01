package com.proyecto.infinitTask.app.dtos.response.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioDTOWithToken {

    private int id;

    private String usuario;

    private String nombre;

    private String apellido;

    private String email;

    private String token;
}
