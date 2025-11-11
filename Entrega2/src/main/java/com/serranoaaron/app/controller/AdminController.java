package com.serranoaaron.app.controller;

import com.serranoaaron.app.dto.AdministradorDTO;
import com.serranoaaron.app.model.Administrador;
import com.serranoaaron.app.service.ServiceAdmin;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar administradores.
 * Endpoints: /api/administradores
 */
@RestController
@RequestMapping("/api/administradores")
public class AdminController {

    private final ServiceAdmin serviceAdmin;

    public AdminController(ServiceAdmin serviceAdmin) {
        this.serviceAdmin = serviceAdmin;
    }

    /**
     * GET /api/administradores
     * Obtener todos los administradores.
     */
    @GetMapping
    public ResponseEntity<List<Administrador>> obtenerAdmins() {
        List<Administrador> admins = serviceAdmin.obtenerAdmins();
        return ResponseEntity.ok(admins);
    }

    /**
     * GET /api/administradores/{id}
     * Obtener un administrador por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Administrador> obtenerAdmin(@PathVariable Long id) {
        Administrador admin = serviceAdmin.obtenerAdmin(id);
        return ResponseEntity.ok(admin);
    }

    /**
     * POST /api/administradores
     * Crear un nuevo administrador.
     */
    @PostMapping
    public ResponseEntity<Administrador> crearAdmin(@Valid @RequestBody AdministradorDTO adminDTO) {
        Administrador nuevoAdmin = serviceAdmin.crearAdmin(adminDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAdmin);
    }

    /**
     * PUT /api/administradores/{id}
     * Actualizar un administrador existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Administrador> actualizarAdmin(
            @Valid @RequestBody AdministradorDTO adminDTO,
            @PathVariable Long id) {
        Administrador adminActualizado = serviceAdmin.actualizarAdmin(adminDTO, id);
        return ResponseEntity.ok(adminActualizado);
    }

    /**
     * DELETE /api/administradores/{id}
     * Eliminar un administrador por ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAdmin(@PathVariable Long id) {
        serviceAdmin.eliminarAdmin(id);
        return ResponseEntity.noContent().build();
    }
}
