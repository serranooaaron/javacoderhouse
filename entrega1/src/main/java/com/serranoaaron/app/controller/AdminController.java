package com.serranoaaron.app.controller;

import com.serranoaaron.app.model.Administrador;
import com.serranoaaron.app.service.ServiceAdmin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final ServiceAdmin serviceAdmin;

    public AdminController(ServiceAdmin serviceAdmin) {
        this.serviceAdmin = serviceAdmin;
    }

    @GetMapping
    public ResponseEntity<List<Administrador>> ObtenerAdmins(){
        return new ResponseEntity<>(serviceAdmin.ObtenerAdmins(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrador> ObtenerAdmin(@PathVariable Long id){
        Administrador a = serviceAdmin.ObtenerAdmin(id);
        if(a == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> CrearAdmin(@RequestBody Administrador admin){
        serviceAdmin.CrearAdmin(admin);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> ActualizarAdmin(@RequestBody Administrador admin, @PathVariable Long id){
        serviceAdmin.ActualizarAdmin(admin, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> EliminarAdmin(@RequestBody Administrador admin){
        serviceAdmin.EliminarAdmin(admin);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
