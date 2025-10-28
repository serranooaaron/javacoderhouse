package com.serranoaaron.app.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@Table(name = "turno")
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "nombre_turno")
    private String nombre;
    @Column(name = "descripcion_turno")
    private String descripcion;

    @Column(name = "fecha_turno", updatable = false)
    @CreationTimestamp
    private java.time.LocalDateTime fecha;

}
