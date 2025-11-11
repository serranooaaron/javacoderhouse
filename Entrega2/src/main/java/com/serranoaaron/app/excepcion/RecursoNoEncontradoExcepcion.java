package com.serranoaaron.app.excepcion;

/**
 * Excepción lanzada cuando no se encuentra un recurso solicitado en la base de datos.
 * Por ejemplo: cuando se busca un usuario por ID y no existe.
 */
public class RecursoNoEncontradoExcepcion extends RuntimeException {
    
    public RecursoNoEncontradoExcepcion(String mensaje) {
        super(mensaje);
    }
    
    public RecursoNoEncontradoExcepcion(String nombreRecurso, String nombreCampo, Object valorCampo) {
        super(String.format("%s no encontrado con %s: '%s'", nombreRecurso, nombreCampo, valorCampo));
    }
}
