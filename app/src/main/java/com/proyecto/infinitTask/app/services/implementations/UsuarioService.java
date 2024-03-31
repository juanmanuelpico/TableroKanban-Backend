package com.proyecto.infinitTask.app.services.implementations;

import com.proyecto.infinitTask.app.dtos.request.Usuario.UsuarioDTOLogin;
import com.proyecto.infinitTask.app.dtos.request.Usuario.UsuarioDTORequest;
import com.proyecto.infinitTask.app.dtos.response.Usuario.UsuarioDTOResponse;
import com.proyecto.infinitTask.app.entities.Tarea;
import com.proyecto.infinitTask.app.entities.Usuario;
import com.proyecto.infinitTask.app.repositories.UsuarioRepository;
import com.proyecto.infinitTask.app.services.IProyectoService;
import com.proyecto.infinitTask.app.services.ITareaService;
import com.proyecto.infinitTask.app.services.IUsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("usuarioService")
public class UsuarioService implements IUsuarioService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired(required = true)
    private ModelMapper modelMapper;

    @Autowired
    private IProyectoService proyectoService;

    @Autowired
    @Lazy
    private ITareaService tareaService;

    @Autowired
    @Lazy
    private IUsuarioService usuarioService;

    @Override
    public boolean crearUsuario(UsuarioDTORequest dto) throws Exception {

            if (usuarioRepository.findByUsuario(dto.getUsuario()) != null) {
                throw new Exception("El nombre del usuario ya existe");
            }
            if (usuarioRepository.findByEmail(dto.getEmail()) != null) {
                throw new Exception("El mail ya se encuentra registrado");
            }

            Usuario usuario = modelMapper.map(dto, Usuario.class);
            usuario.setFechaAlta(LocalDate.now());
            usuario.setActivo(true);

            // Hash de la contraseña
            usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
            usuarioRepository.save(usuario);
            return true;
        }

        @Override
        public UsuarioDTOResponse traerUsuarioId ( int id)throws Exception {
            Usuario usuarioExistente = usuarioRepository.findById(id);

            if (usuarioExistente == null) {
                throw new Exception("No se encontro el usuario con el id: " + usuarioExistente.getId());
            }
            return modelMapper.map(usuarioExistente, UsuarioDTOResponse.class);
        }

    @Override
    public Usuario obtenerUsuarioEntidadPorId(int id) throws Exception {
        Usuario usuario = usuarioRepository.findById(id);

        if(usuario == null){
            throw new Exception("El usuario con id: "+id+", no existe en la bd");
        }

        return usuario;
    }

    @Override
        public UsuarioDTOResponse traerUsuario (String usuario)throws Exception {
            Usuario existente = usuarioRepository.findByUsuario(usuario);

            if (existente == null) {
                throw new Exception("No se encuentra el usuario " + existente.getUsuario());
            }

            return modelMapper.map(existente, UsuarioDTOResponse.class);

        }

        @Override
        public List<UsuarioDTOResponse> obtenerUsuarios () throws Exception {

            List<UsuarioDTOResponse> listaUsuarioDto = new ArrayList<>();
            List<Usuario> listaUsuarioEnt = usuarioRepository.findAll();

            if (listaUsuarioEnt.isEmpty()) {
                throw new Exception("La lista de usuarios esta vacía.");
            }

            for (Usuario u : listaUsuarioEnt) {
                listaUsuarioDto.add(modelMapper.map(u, UsuarioDTOResponse.class));
            }

            return listaUsuarioDto;
        }


    //se recibe el id del proyecto para que la query pueda obtener todos los usuarios que no pertenezcan a ese proyecto
    @Override
    public List<UsuarioDTOResponse> obtenerUsuariosPorNombre(String nombreUsuario, int idProyecto) throws Exception {
        List<Usuario> listaUsuarios = usuarioRepository.findAllUsuariosNotInProyecto(nombreUsuario, idProyecto);
        List<UsuarioDTOResponse> listaUsuarioDto = new ArrayList<>();

        if(!listaUsuarios.isEmpty()) {
            for (Usuario usuario : listaUsuarios) {
                    listaUsuarioDto.add(modelMapper.map(usuario, UsuarioDTOResponse.class));
            }
        }
        return listaUsuarioDto;
    }

    @Override
        public UsuarioDTOResponse traerUsuarioLogin (UsuarioDTOLogin dtoLogin) throws Exception {
            Usuario usuarioEntidad = usuarioRepository.findByUsuario(dtoLogin.getUsuario());
            if (usuarioEntidad == null) {
                throw new Exception("Usuario y/o contraseña incorrecto");
            }
            // Utilizar BCryptPasswordEncoder para comparar la contraseña en texto plano
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (!passwordEncoder.matches(dtoLogin.getPassword(), usuarioEntidad.getPassword())) {
                throw new Exception("Usuario y/o contraseña incorrecto");
            }
            return modelMapper.map(usuarioEntidad, UsuarioDTOResponse.class);
        }

    @Override
    public UsuarioDTOResponse editarUsuario(UsuarioDTORequest dto) throws Exception {
        Usuario usuario = usuarioRepository.findById(dto.getId());
        if(usuario == null){
            throw new Exception("El usuario: "+ dto.getId() +" no se encontro para editar");
        }
        usuario.setApellido(dto.getApellido());
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.setFechaActualizacion(LocalDate.now());
        usuario.setUsuario(dto.getUsuario());

        usuarioRepository.save(usuario);
        //se retorna el usuario actualizado
        return this.traerUsuarioId(dto.getId());
    }

    @Override
    public List<UsuarioDTOResponse> obtenerUsuariosDeProyecto(int idProyecto) throws Exception {
        if(proyectoService.obtenerProyectoEntidadPorId(idProyecto) == null){
            throw new Exception("El proyecto con id: "+idProyecto+", no existe");
        }
       return usuarioRepository.findUsuariosByProyecto(idProyecto).stream().map(usuario -> modelMapper.map(usuario, UsuarioDTOResponse.class)).collect(Collectors.toList());
    }

    @Override
    public List<UsuarioDTOResponse> obtenerUsuariosPorTermDeProyectoNoTarea(String usuarioNombre, int idProyecto, int idTarea) throws Exception {
        //Se traen las entidades proyecto y tarea ya que las excepciones correspondientes se encuentran en su respectivo service
        //por lo tanto, en caso de haber algun error al traer la entidades, se corta la ejecucion del metodo
        List<UsuarioDTOResponse> dtos = new ArrayList<>();
        if(proyectoService.obtenerProyectoEntidadPorId(idProyecto) != null && tareaService.traerTareaEntidadPorId(idTarea) != null){
            dtos = usuarioRepository.findUsuariosByProyectoNotInTarea(usuarioNombre, idProyecto, idTarea).stream().map(usuario -> modelMapper.map(usuario, UsuarioDTOResponse.class)).collect(Collectors.toList());
        }
        return dtos;
    }

    @Override
    public void asignarUsuarioATarea(int idTarea, int idUsuario) throws Exception {
        Usuario usuario = obtenerUsuarioEntidadPorId(idUsuario);
        Tarea tarea = tareaService.traerTareaEntidadPorId(idTarea);
        usuario.getTareas().add(tarea);
        usuarioRepository.save(usuario);
    }

    @Override
    public void desasignarUsuarioATarea(int idTarea, int idUsuario) throws Exception {
        Usuario usuario = obtenerUsuarioEntidadPorId(idUsuario);
        Tarea tarea = tareaService.traerTareaEntidadPorId(idTarea);
        usuario.getTareas().remove(tarea);
        usuarioRepository.save(usuario);
    }

    @Override
    public void desasignarTareasDeUsuarioEnProyecto(int idUsuario, int idProyecto) throws Exception {
       List<Tarea> tareas = tareaService.obtenerTareasDeUsuarioEnProyecto(idUsuario,idProyecto);
       if(!tareas.isEmpty()) {
           Usuario usuario = usuarioService.obtenerUsuarioEntidadPorId(idUsuario);
           for (Tarea t : tareas) {

               System.out.println(usuario.getTareas().remove(t));

           }
           usuarioRepository.save(usuario);
       }

    }
}
