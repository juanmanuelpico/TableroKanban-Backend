package com.proyecto.infinitTask.app.controllers;

import com.proyecto.infinitTask.app.dtos.request.Proyecto.ProyectoDTORequest;
import com.proyecto.infinitTask.app.dtos.request.ProyectoRolUsuario.ProyectoRolUsuarioDTO;
import com.proyecto.infinitTask.app.dtos.request.Usuario.UsuarioDTORequest;
import com.proyecto.infinitTask.app.dtos.response.Proyecto.ProyectoDTOResponse;
import com.proyecto.infinitTask.app.entities.Proyecto;
import com.proyecto.infinitTask.app.repositories.ProyectoRolUsuarioRepository;
import com.proyecto.infinitTask.app.services.IProyectoRolUsuarioService;
import com.proyecto.infinitTask.app.services.IProyectoService;
import com.proyecto.infinitTask.app.util.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/proyecto")
@CrossOrigin(origins = "http://localhost:3000")
public class ProyectoController {

    @Autowired
    private IProyectoService proyectoService;


    @PostMapping("/crear")
    public ResponseEntity<Object> crearProyecto(@RequestBody ProyectoDTORequest dto){
        try{
            proyectoService.crearProyecto(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Mensaje("Proyecto creado exitosamente"));
            //ES EQUIVALENTE
            //return new ResponseEntity<>(new Mensaje("Usuario creado exitosamente"), HttpStatus.CREATED );

        }catch(Exception e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()) , HttpStatus.BAD_REQUEST);
            //ES EQUIVALENTE
            // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(e.getMessage()));
        }
    }

    @GetMapping("/traerProyectos/{idUsuario}")
    public ResponseEntity<Object> obtenerProyectosDeUsuario(@PathVariable int idUsuario) {
        try {
            List<ProyectoDTOResponse> dtos = proyectoService.obtenerProyectosDeUsuario(idUsuario);
            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(e.getMessage()));
        }
    }

    @GetMapping("/buscarProyectos/{idUsuario}/{nombreProyecto}")
    public ResponseEntity<Object> buscarProyectos(@PathVariable int idUsuario, @PathVariable String nombreProyecto) {
        try {
            List<ProyectoDTOResponse> dtos = proyectoService.buscarProyectosPorNombre(idUsuario, nombreProyecto);
            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(e.getMessage()));
        }
    }

    @GetMapping("/traerProyectosInicioFin/{idUsuario}")
    public ResponseEntity<Object> obtenerProyectosDeUsuarioFechaDesdeFechaHasta(@PathVariable int idUsuario,
                                                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde,
                                                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHasta) {
        try {
            List<ProyectoDTOResponse> dtos = proyectoService.obtenerProyectosDeUsuarioDesdeHasta(idUsuario, fechaDesde, fechaHasta);
            return ResponseEntity.status(HttpStatus.OK).body(dtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(e.getMessage()));
        }
    }

    @GetMapping("/traerProyectoId/{id}")
    public ResponseEntity<Object> obtenerProyectoPorId(@PathVariable int id) {

        try {
            ProyectoDTOResponse dtoProyecto = proyectoService.traerProyectoId(id);

            return ResponseEntity.status(HttpStatus.OK).body(dtoProyecto);
        } catch (Exception e) {

            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/traerProyectoNombre/{nombre}")
    public ResponseEntity<Object> obtenerProyectoPorId(@PathVariable String nombre) {

        try {
            ProyectoDTOResponse dtoProyecto = proyectoService.traerProyectoNombre(nombre);
            return ResponseEntity.status(HttpStatus.OK).body(dtoProyecto);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/bajaProyecto/{id}")
    public ResponseEntity<Object> bajaProyecto(@PathVariable int id) {
        try {
            proyectoService.bajaLogicaProyecto(id);
            return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Proyecto eliminado exitosamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(e.getMessage()));
        }
    }

    @PutMapping("/editarProyecto/{id}/{nombre}/{descripcion}")
    public ResponseEntity<Object> editarProyecto(@PathVariable int id, @PathVariable String nombre, @PathVariable String descripcion) {
        try {
            proyectoService.editarNombreDescripcionProyecto(id, nombre, descripcion);
            return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Proyecto editado exitosamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(e.getMessage()));
        }
    }

    @PostMapping("/agregarUsuarioAProyecto")
    public ResponseEntity<Object> agregarUsuarioAProyectoConRol(@RequestBody ProyectoRolUsuarioDTO dto){
        System.out.println(dto);
        try {
            proyectoService.agregarUsuarioAProyectoConRol(dto);
            return ResponseEntity.status(HttpStatus.OK).body(new Mensaje("Usuario agregado exitosamente"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(e.getMessage()));
        }
    }
}
