package com.serranoaaron.app.excepcion;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * Estructura de respuesta para los errores de la API.
 * Se devuelve al cliente cuando ocurre una excepción.
 */
@Data
@AllArgsConstructor
public class RespuestaError {
    
    private LocalDateTime fecha;
    private String mensaje;
    private String detalles;
    private int codigoEstado;
}
