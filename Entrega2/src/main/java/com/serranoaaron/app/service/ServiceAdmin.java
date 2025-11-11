package com.serranoaaron.app.service;

import com.serranoaaron.app.dto.AdministradorDTO;
import com.serranoaaron.app.excepcion.RecursoNoEncontradoExcepcion;
import com.serranoaaron.app.excepcion.SolicitudInvalidaExcepcion;
import com.serranoaaron.app.model.Administrador;
import com.serranoaaron.app.repository.AdminRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Servicio para gestionar administradores.
 */
@Service
public class ServiceAdmin {

    private final AdminRepository adminRepository;

    public ServiceAdmin(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    /**
     * Obtener un administrador por ID.
     */
    @Transactional(readOnly = true)
    public Administrador obtenerAdmin(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("Administrador", "id", id));
    }

    /**
     * Obtener todos los administradores.
     */
    @Transactional(readOnly = true)
    public List<Administrador> obtenerAdmins() {
        return adminRepository.findAll();
    }

    /**
     * Crear un nuevo administrador.
     */
    @Transactional
    public Administrador crearAdmin(AdministradorDTO adminDTO) {
        // Validar que el email no exista
        if (adminRepository.findAll().stream()
                .anyMatch(a -> a.getEmail().equals(adminDTO.getEmail()))) {
            throw new SolicitudInvalidaExcepcion("Ya existe un administrador con ese correo");
        }

        // Validar que el DNI no exista
        if (adminRepository.findAll().stream()
                .anyMatch(a -> a.getDni() == adminDTO.getDni())) {
            throw new SolicitudInvalidaExcepcion("Ya existe un administrador con ese DNI");
        }

        Administrador admin = new Administrador();
        admin.setNombre(adminDTO.getNombre());
        admin.setEmail(adminDTO.getEmail());
        admin.setDni(adminDTO.getDni());

        return adminRepository.save(admin);
    }

    /**
     * Actualizar un administrador existente.
     */
    @Transactional
    public Administrador actualizarAdmin(AdministradorDTO adminDTO, Long id) {
        Administrador admin = adminRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("Administrador", "id", id));

        admin.setNombre(adminDTO.getNombre());
        admin.setEmail(adminDTO.getEmail());
        admin.setDni(adminDTO.getDni());

        return adminRepository.save(admin);
    }

    /**
     * Eliminar un administrador por ID.
     */
    @Transactional
    public void eliminarAdmin(Long id) {
        Administrador admin = adminRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("Administrador", "id", id));
        
        adminRepository.delete(admin);
    }
}
