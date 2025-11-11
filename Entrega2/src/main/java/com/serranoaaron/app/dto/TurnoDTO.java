package com.serranoaaron.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO para Turno con validaciones.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurnoDTO {
    
    private Long id;
    
    @NotNull(message = "El ID del usuario es obligatorio")
    private Long usuarioId;
    
    @NotBlank(message = "El nombre del turno es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;
    
    @Size(max = 500, message = "La descripción no puede tener más de 500 caracteres")
    private String descripcion;
    
    private LocalDateTime fecha;
}
