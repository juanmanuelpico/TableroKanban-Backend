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
@Table(name="rol_usuario")
public class RolUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_rol")
    private int id;

    @Column(name="rol")
    private String rol;

    @Column(name="fecha_alta")
    private LocalDate fechaAlta;
    @Column(name="fecha_baja")
    private LocalDate fechaBaja;
    @Column(name="fecha_actualizacion")
    private LocalDate fechaActualizacion;

    @Column(name="activo")
    private boolean activo;

    //CAMBIARLO A ONE TO MANY
    @ManyToMany(mappedBy = "roles")
    private Set<Usuario> usuarios = new HashSet<>();


}
