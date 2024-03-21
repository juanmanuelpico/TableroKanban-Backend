package com.proyecto.infinitTask.app.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private int id;

    @Column(name="usuario")
    private String usuario;

    @Column(name="password")
    private String password;

    @Column(name="nombre")
    private String nombre;

    @Column(name="apellido")
    private String apellido;

    @Column(name="email")
    private String email;

    @Column(name="fecha_alta")
    private LocalDate fechaAlta;

    @Column(name="fecha_baja")
    private LocalDate fechaBaja;

    @Column(name="fecha_actualizacion")
    private LocalDate fechaActualizacion;

    @Column(name="activo")
    private boolean activo;

    @ManyToMany
    @JoinTable(name = "usuario_tiene_tarea", joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_tarea"))
    private Set<Tarea> tareas = new HashSet<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Set<ProyectoRolUsuario> proyectoRolUsuarios = new HashSet<>();
}
