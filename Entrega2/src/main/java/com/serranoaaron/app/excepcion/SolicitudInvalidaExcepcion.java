package com.serranoaaron.app.excepcion;

/**
 * Excepción lanzada cuando la solicitud del cliente contiene datos inválidos.
 * Por ejemplo: email mal formateado, campos obligatorios vacíos, etc.
 */
public class SolicitudInvalidaExcepcion extends RuntimeException {
    
    public SolicitudInvalidaExcepcion(String mensaje) {
        super(mensaje);
    }
}
