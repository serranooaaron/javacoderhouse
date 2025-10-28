package com.serranoaaron.app.model;



import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

@Data // LOMBOK GENERA LOS GETTER - SETTER - TOSTRING
@Table(name = "admin")
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera que sea auto-incremental.
    private long id;

    @Column(name = "User")
    Usuario usuario = new Usuario();
    @Column(name = "Turno")
    Turno turno = new Turno();

    @Column(name = "name_admin")
    private String nombre;
    @Column(name = "email_admin")
    private String email;
    @Column(name = "dni_admin")
    private int dni;

}
