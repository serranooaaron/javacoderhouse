package com.serranoaaron.app.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entidad Usuario.
 * Representa tanto usuarios normales como administradores mediante el campo 'rol'.
 */
@Data
@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String rol = "USUARIO"; // Por defecto es USUARIO, puede ser ADMINISTRADOR
}
