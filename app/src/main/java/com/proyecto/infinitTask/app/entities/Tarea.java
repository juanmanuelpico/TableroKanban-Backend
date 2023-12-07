package com.proyecto.infinitTask.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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
    private LocalDateTime fechaCreacion;

    @Column(name="fecha_inicio")
    private LocalDateTime fechaInicio;

    @Column(name="fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @Column(name="fecha_fin")
    private LocalDateTime fechaFin;

    @Column(name="estado")
    private String estado;

    @Column(name="dificultad")
    private int dificultad; // dificultad de 1 a 10

    @ManyToMany(mappedBy = "tareas")
    private Set<Usuario> usuarios = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_proyecto")
    private Proyecto proyecto;


}
