package com.serranoaaron.app.model;



import jakarta.persistence.*;
import lombok.Data;


@Data // LOMBOK GENERA LOS GETTER - SETTER - TOSTRING
@Entity
@Table(name = "admin")
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera que sea auto-incremental.
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "turno_id")
    private Turno turno;

    @Column(name = "name_admin")
    private String nombre;
    @Column(name = "email_admin")
    private String email;
    @Column(name = "dni_admin")
    private int dni;

}
