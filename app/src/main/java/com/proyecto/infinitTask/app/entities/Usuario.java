package com.proyecto.infinitTask.app.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {
    private int id;
    private String usuario;
    private String password;
    private String rol;
    private String email;
    private LocalDate fechaAlta;
    private LocalDate fechaBaja;
    private LocalDate fechaActualizacion;
    private boolean activo;

}
