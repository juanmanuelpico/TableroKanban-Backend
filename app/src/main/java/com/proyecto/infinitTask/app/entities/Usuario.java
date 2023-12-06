package com.proyecto.infinitTask.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    @Column(name="rol")
    private String rol;
    @Column(name="email")
    private String email;
    @Column(name="fechaAlta")
    private LocalDate fechaAlta;
    @Column(name="fechaBaja")
    private LocalDate fechaBaja;
    @Column(name="fechaActualizacion")
    private LocalDate fechaActualizacion;
    @Column(name="activo")
    private boolean activo;

}
