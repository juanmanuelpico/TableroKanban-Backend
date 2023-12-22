package com.proyecto.infinitTask.app.controllers;

import com.proyecto.infinitTask.app.dtos.request.UsuarioDTORequest;
import com.proyecto.infinitTask.app.dtos.response.UsuarioDTOResponse;
import com.proyecto.infinitTask.app.services.IUsuarioService;
import com.proyecto.infinitTask.app.util.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;


   //Este controlador recibe un body desde el front y lo convierte a UsuarioDTORequest
   //Luego convierte el dto a entidad para guardarlo en la base de datos
    //Finalmente devuelve un body para que el front puede saber  travez de una msj si el
    //usuario fue ingresado correctamente
    @PostMapping("/registro")
    public ResponseEntity<Object> crearUsuario(@RequestBody UsuarioDTORequest dto){
        try{
           usuarioService.crearUsuario(dto);
           return ResponseEntity.status(HttpStatus.CREATED).body(new Mensaje("Usuario creado exitosamente"));
           //ES EQUIVALENTE
           //return new ResponseEntity<>(new Mensaje("Usuario creado exitosamente"), HttpStatus.CREATED );

        }catch(Exception e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()) , HttpStatus.BAD_REQUEST);
            //ES EQUIVALENTE
           // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje(e.getMessage()));
        }
    }

    @GetMapping("/traerUsuario/{usuario}")
    public ResponseEntity<Object> obtenerUsuario(@PathVariable String usuario) {

        try{
            UsuarioDTOResponse dtoUsuario = usuarioService.traerUsuario(usuario);
            return ResponseEntity.status(HttpStatus.OK).body(dtoUsuario);
        } catch (Exception e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()) , HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/traerUsuarioId/{id}")
    public ResponseEntity<Object> obtenerUsuarioPorId(@PathVariable int id) {

        try{
            UsuarioDTOResponse dtoUsuario = usuarioService.traerUsuarioId(id);
            return ResponseEntity.status(HttpStatus.OK).body(dtoUsuario);
        } catch (Exception e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()) , HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/traerUsuarios")
    public ResponseEntity<Object> obtenerUsuarios(){
        try{
            List<UsuarioDTOResponse> listaDto = usuarioService.obtenerUsuarios();
            return ResponseEntity.status(HttpStatus.OK).body(listaDto);
        } catch (Exception e){
            return new ResponseEntity<>(new Mensaje(e.getMessage()) , HttpStatus.BAD_REQUEST);
        }
    }
}
