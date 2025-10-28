package com.serranoaaron.app.controller;

import com.serranoaaron.app.model.Usuario;
import com.serranoaaron.app.service.ServiceUsuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final ServiceUsuario serviceUsuario;

    public UsuarioController(ServiceUsuario serviceUsuario) {
        this.serviceUsuario = serviceUsuario;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> ObtenerUsuarios(){
        return new ResponseEntity<>(serviceUsuario.ObtenerUsuarios(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> ObtenerUsuario(@PathVariable Long id){
        Usuario u = serviceUsuario.ObtenerUsuario(id);
        if(u == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> CrearUsuario(@RequestBody Usuario usuario){
        serviceUsuario.CrearUsuario(usuario);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> ActualizarUsuario(@RequestBody Usuario usuario, @PathVariable Long id){
        serviceUsuario.ActualizarUsuario(usuario, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> EliminarUsuario(@RequestBody Usuario usuario){
        serviceUsuario.EliminarUsuario(usuario);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
