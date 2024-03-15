package com.proyecto.infinitTask.app.controllers;

import com.proyecto.infinitTask.app.dtos.request.Tarea.TareaDTORequest;
import com.proyecto.infinitTask.app.dtos.response.Tarea.TareaDTOResponse;
import com.proyecto.infinitTask.app.services.ITareaService;
import com.proyecto.infinitTask.app.util.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarea")
@CrossOrigin(origins = "http://localhost:3000") // Permitir solicitudes desde localhost:3000
public class TareaController {

    @Autowired private ITareaService tareaService;

    @PostMapping("/crearTarea")
    public ResponseEntity<Object> crearTarea(@RequestBody TareaDTORequest dto) {

        try {
            tareaService.crearTarea(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Mensaje("Tarea creada exitosamente"));
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()) , HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/traerTareas/{idProyecto}")
    public ResponseEntity<Object> obtenerTareas(@PathVariable int idProyecto){

        try{
            List<TareaDTOResponse>tareasDto=tareaService.traerTareas(idProyecto);
            return ResponseEntity.status(HttpStatus.OK).body(tareasDto);
        }catch (Exception e){

            return new ResponseEntity<>(new Mensaje(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/buscarTareasIdNombre/{idProyecto}/{nombreTarea}")
    public ResponseEntity<Object> obtenerTareas(@PathVariable int idProyecto, @PathVariable String nombreTarea){

        try{
            List<TareaDTOResponse>tareasDto=tareaService.buscarTareasIdNombre(idProyecto, nombreTarea);
            return ResponseEntity.status(HttpStatus.OK).body(tareasDto);
        }catch (Exception e){

            return new ResponseEntity<>(new Mensaje(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

}
