package com.proyecto.infinitTask.app.dtos.request.Proyecto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ProyectoDTORequest {

    private int idUsuario;
    private String nombre;
    private String descripcion;

}
