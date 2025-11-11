package com.serranoaaron.app.service;

import com.serranoaaron.app.dto.UsuarioDTO;
import com.serranoaaron.app.excepcion.RecursoNoEncontradoExcepcion;
import com.serranoaaron.app.excepcion.SolicitudInvalidaExcepcion;
import com.serranoaaron.app.model.Usuario;
import com.serranoaaron.app.repository.UsuarioRepository;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para gestionar operaciones de Usuario.
 * Contiene la lógica de negocio y validaciones.
 */
@Service
public class ServiceUsuario {
    
    private final UsuarioRepository usuarioRepository;

    public ServiceUsuario(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Obtener un usuario por ID.
     * Lanza excepción si no se encuentra.
     */
    @Transactional(readOnly = true)
    public Usuario obtenerUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("Usuario", "id", id));
    }

    /**
     * Obtener todos los usuarios.
     */
    @Transactional(readOnly = true)
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    /**
     * Crear un nuevo usuario.
     * Convierte el DTO a entidad y guarda en BD.
     */
    @Transactional
    public Usuario crearUsuario(UsuarioDTO usuarioDTO) {
        // Validar que el email no exista
        if (usuarioRepository.findAll().stream()
                .anyMatch(u -> u.getEmail().equals(usuarioDTO.getEmail()))) {
            throw new SolicitudInvalidaExcepcion("Ya existe un usuario con ese correo");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setRol(usuarioDTO.getRol() != null ? usuarioDTO.getRol() : "USUARIO");

        return usuarioRepository.save(usuario);
    }

    /**
     * Actualizar un usuario existente.
     */
    @Transactional
    public Usuario actualizarUsuario(UsuarioDTO usuarioDTO, Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("Usuario", "id", id));

        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setPassword(usuarioDTO.getPassword());
        
        if (usuarioDTO.getRol() != null) {
            usuario.setRol(usuarioDTO.getRol());
        }

        return usuarioRepository.save(usuario);
    }

    /**
     * Eliminar un usuario por ID.
     */
    @Transactional
    public void eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("Usuario", "id", id));
        
        usuarioRepository.delete(usuario);
    }

    /**
     * Asignar un rol a un usuario (operación de administrador).
     */
    @Transactional
    public Usuario asignarRol(Long id, String rol) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("Usuario", "id", id));

        if (!rol.equals("USUARIO") && !rol.equals("ADMINISTRADOR")) {
            throw new SolicitudInvalidaExcepcion("El rol debe ser USUARIO o ADMINISTRADOR");
        }

        usuario.setRol(rol);
        return usuarioRepository.save(usuario);
    }
}
