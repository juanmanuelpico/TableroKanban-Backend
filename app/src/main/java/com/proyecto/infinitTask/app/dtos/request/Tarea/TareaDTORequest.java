package com.proyecto.infinitTask.app.dtos.request.Tarea;

import com.proyecto.infinitTask.app.entities.Proyecto;
import com.proyecto.infinitTask.app.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class TareaDTORequest {

    private String titulo;

    private String descripcion;

    private boolean activo;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaActualizacion;

    private LocalDateTime fechaFin;

    private String estado;

    private  int dificultad;

    private Set<Usuario> usuarios;

    private Proyecto proyecto;

}
