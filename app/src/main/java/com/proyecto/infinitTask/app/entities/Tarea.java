package com.proyecto.infinitTask.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tarea")
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_tarea")
    private int id;

    @Column(name="nombre")
    private String titulo;

    @Column(name="descripcion")
    private String descripcion;

    @Column(name="activo")
    private boolean activo;

    @Column(name="fecha_creacion")
    private LocalDate fechaCreacion;

    @Column(name="fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name="fecha_actualizacion")
    private LocalDate fechaActualizacion;

    @Column(name="fecha_fin")
    private LocalDate fechaFin;

    @Column(name="estado")
    private String estado;

    @Column(name="dificultad")
    private String dificultad; // baja media alta

    @ManyToMany(mappedBy = "tareas")
    private Set<Usuario> usuarios = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_proyecto")
    private Proyecto proyecto;

}
