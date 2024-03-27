package com.proyecto.infinitTask.app.dtos.request.Tarea;

import com.proyecto.infinitTask.app.entities.Proyecto;
import com.proyecto.infinitTask.app.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class TareaDTORequest {

    private int idProyecto;

    private int idTarea;

    private String titulo;

    private String descripcion;

    private boolean activo;

    private LocalDate fechaCreacion;

    private LocalDate fechaInicio;

    private LocalDate fechaActualizacion;

    private LocalDate fechaFin;

    private String estado;

    private String dificultad;

    private Set<Usuario> usuarios;

}
