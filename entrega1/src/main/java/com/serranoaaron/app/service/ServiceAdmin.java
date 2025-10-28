package com.serranoaaron.app.service;

import com.serranoaaron.app.model.Administrador;
import com.serranoaaron.app.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceAdmin {
    @Autowired
    private AdminRepository adminRepository;

    public Administrador ObtenerAdmin(Long ID){
        return adminRepository.findById(ID).orElse(null); // Buscar por ID la entidad Administrador

    }

    public List<Administrador> ObtenerAdmins(){
        return adminRepository.findAll(); // Traer los todas las entidades Administrador
    }
    public void CrearAdmin(Administrador admin){
        adminRepository.save(admin); // Almacenar entidad Administrador en BD
    }
    public void ActualizarAdmin(Administrador actualizarAdmin, Long id){
        if (adminRepository.existsById(id)){
            actualizarAdmin.setId(id);
            adminRepository.save(actualizarAdmin);
        }
    }
    private void EliminarAdmin(Administrador admin){
        adminRepository.delete(admin);
    }


}
