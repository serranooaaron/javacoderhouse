package com.serranoaaron.app.controller;

import com.serranoaaron.app.dto.UsuarioDTO;
import com.serranoaaron.app.model.Usuario;
import com.serranoaaron.app.service.ServiceUsuario;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controlador REST para gestionar usuarios.
 * Endpoints: /api/usuarios
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final ServiceUsuario serviceUsuario;

    public UsuarioController(ServiceUsuario serviceUsuario) {
        this.serviceUsuario = serviceUsuario;
    }

    /**
     * GET /api/usuarios
     * Obtener todos los usuarios.
     */
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
        List<Usuario> usuarios = serviceUsuario.obtenerUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    /**
     * GET /api/usuarios/{id}
     * Obtener un usuario por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long id) {
        Usuario usuario = serviceUsuario.obtenerUsuario(id);
        return ResponseEntity.ok(usuario);
    }

    /**
     * POST /api/usuarios
     * Crear un nuevo usuario.
     * @Valid valida automáticamente el DTO según las anotaciones.
     */
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        Usuario nuevoUsuario = serviceUsuario.crearUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    /**
     * PUT /api/usuarios/{id}
     * Actualizar un usuario existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(
            @Valid @RequestBody UsuarioDTO usuarioDTO,
            @PathVariable Long id) {
        Usuario usuarioActualizado = serviceUsuario.actualizarUsuario(usuarioDTO, id);
        return ResponseEntity.ok(usuarioActualizado);
    }

    /**
     * DELETE /api/usuarios/{id}
     * Eliminar un usuario por ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        serviceUsuario.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * PATCH /api/usuarios/{id}/rol
     * Asignar un rol a un usuario (operación de administrador).
     * Ejemplo body: { "rol": "ADMINISTRADOR" }
     */
    @PatchMapping("/{id}/rol")
    public ResponseEntity<Usuario> asignarRol(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        String rol = body.get("rol");
        Usuario usuario = serviceUsuario.asignarRol(id, rol);
        return ResponseEntity.ok(usuario);
    }
}
