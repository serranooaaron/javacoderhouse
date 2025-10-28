package com.serranoaaron.app.service;

import com.serranoaaron.app.model.Administrador;
import com.serranoaaron.app.repository.AdminRepository;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceAdmin {

    private final AdminRepository adminRepository;

    public ServiceAdmin(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }


    @Transactional(readOnly = true)
    public Administrador ObtenerAdmin(Long ID){
        return adminRepository.findById(ID).orElse(null); // Buscar por ID la entidad Administrador

    }

    @Transactional(readOnly = true)
    public List<Administrador> ObtenerAdmins(){
        return adminRepository.findAll(); // Traer los todas las entidades Administrador
    }
 
    @Transactional
    public void CrearAdmin(Administrador admin){
        adminRepository.save(admin); // Almacenar entidad Administrador en BD
    }

    @Transactional
    public void ActualizarAdmin(Administrador actualizarAdmin, Long id){
        if (adminRepository.existsById(id)){
            actualizarAdmin.setId(id);
            adminRepository.save(actualizarAdmin);
        }
    }

    @Transactional
    public void EliminarAdmin(Administrador admin){
        adminRepository.delete(admin);
    }


}
