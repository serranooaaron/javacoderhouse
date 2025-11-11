package com.serranoaaron.app.service;

import com.serranoaaron.app.dto.TurnoDTO;
import com.serranoaaron.app.excepcion.RecursoNoEncontradoExcepcion;
import com.serranoaaron.app.model.Turno;
import com.serranoaaron.app.model.Usuario;
import com.serranoaaron.app.repository.TurnoRepository;
import com.serranoaaron.app.repository.UsuarioRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Servicio para gestionar turnos.
 */
@Service
public class ServiceTurno {
    
    private final TurnoRepository turnoRepository;
    private final UsuarioRepository usuarioRepository;

    public ServiceTurno(TurnoRepository turnoRepository, UsuarioRepository usuarioRepository) {
        this.turnoRepository = turnoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Obtener un turno por ID.
     */
    @Transactional(readOnly = true)
    public Turno obtenerTurno(Long id) {
        return turnoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("Turno", "id", id));
    }

    /**
     * Obtener todos los turnos.
     */
    @Transactional(readOnly = true)
    public List<Turno> obtenerTurnos() {
        return turnoRepository.findAll();
    }

    /**
     * Crear un nuevo turno.
     * Valida que el usuario exista antes de crear el turno.
     */
    @Transactional
    public Turno crearTurno(TurnoDTO turnoDTO) {
        // Buscar el usuario asociado
        Usuario usuario = usuarioRepository.findById(turnoDTO.getUsuarioId())
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("Usuario", "id", turnoDTO.getUsuarioId()));

        Turno turno = new Turno();
        turno.setUsuario(usuario);
        turno.setNombre(turnoDTO.getNombre());
        turno.setDescripcion(turnoDTO.getDescripcion());

        return turnoRepository.save(turno);
    }

    /**
     * Actualizar un turno existente.
     */
    @Transactional
    public Turno actualizarTurno(Long id, TurnoDTO turnoDTO) {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("Turno", "id", id));

        // Actualizar usuario si cambió
        if (turnoDTO.getUsuarioId() != null && !turno.getUsuario().getId().equals(turnoDTO.getUsuarioId())) {
            Usuario usuario = usuarioRepository.findById(turnoDTO.getUsuarioId())
                    .orElseThrow(() -> new RecursoNoEncontradoExcepcion("Usuario", "id", turnoDTO.getUsuarioId()));
            turno.setUsuario(usuario);
        }

        turno.setNombre(turnoDTO.getNombre());
        turno.setDescripcion(turnoDTO.getDescripcion());

        return turnoRepository.save(turno);
    }

    /**
     * Eliminar un turno por ID.
     */
    @Transactional
    public void eliminarTurno(Long id) {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("Turno", "id", id));
        
        turnoRepository.delete(turno);
    }
}
