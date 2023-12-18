package com.proyecto.infinitTask.app.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioDTOResponse {

        private int id;

        private String usuario;

        private String nombre;

        private String apellido;

        private String email;

        private LocalDate fechaAlta;

}
