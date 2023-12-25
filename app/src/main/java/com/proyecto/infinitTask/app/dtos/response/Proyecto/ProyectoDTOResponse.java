package com.proyecto.infinitTask.app.dtos.response.Proyecto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProyectoDTOResponse {
    private int id;

    private String nombre;

    private String descripcion;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;
}
