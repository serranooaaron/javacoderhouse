package com.serranoaaron.app.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entidad Administrador.
 * Representa información adicional de administradores del sistema.
 * El administrador es quien gestiona usuarios y asigna roles.
 */
@Data
@Entity
@Table(name = "administradores")
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @Column(name = "dni", nullable = false, unique = true)
    private int dni;
    
    // Podrías agregar una relación OneToMany si quieres rastrear qué usuarios gestionó
    // @OneToMany(mappedBy = "administrador")
    // private List<Usuario> usuariosGestionados;
}
