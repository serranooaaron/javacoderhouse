package com.serranoaaron.app.controller;

import com.serranoaaron.app.dto.TurnoDTO;
import com.serranoaaron.app.model.Turno;
import com.serranoaaron.app.service.ServiceTurno;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar turnos.
 * Endpoints: /api/turnos
 */
@RestController
@RequestMapping("/api/turnos")
public class TurnoController {

    private final ServiceTurno serviceTurno;

    public TurnoController(ServiceTurno serviceTurno) {
        this.serviceTurno = serviceTurno;
    }

    /**
     * GET /api/turnos
     * Obtener todos los turnos.
     */
    @GetMapping
    public ResponseEntity<List<Turno>> obtenerTurnos() {
        List<Turno> turnos = serviceTurno.obtenerTurnos();
        return ResponseEntity.ok(turnos);
    }

    /**
     * GET /api/turnos/{id}
     * Obtener un turno por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Turno> obtenerTurno(@PathVariable Long id) {
        Turno turno = serviceTurno.obtenerTurno(id);
        return ResponseEntity.ok(turno);
    }

    /**
     * POST /api/turnos
     * Crear un nuevo turno.
     * Requiere el ID del usuario en el body.
     */
    @PostMapping
    public ResponseEntity<Turno> crearTurno(@Valid @RequestBody TurnoDTO turnoDTO) {
        Turno nuevoTurno = serviceTurno.crearTurno(turnoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTurno);
    }

    /**
     * PUT /api/turnos/{id}
     * Actualizar un turno existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Turno> actualizarTurno(
            @Valid @RequestBody TurnoDTO turnoDTO,
            @PathVariable Long id) {
        Turno turnoActualizado = serviceTurno.actualizarTurno(id, turnoDTO);
        return ResponseEntity.ok(turnoActualizado);
    }

    /**
     * DELETE /api/turnos/{id}
     * Eliminar un turno por ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTurno(@PathVariable Long id) {
        serviceTurno.eliminarTurno(id);
        return ResponseEntity.noContent().build();
    }
}
