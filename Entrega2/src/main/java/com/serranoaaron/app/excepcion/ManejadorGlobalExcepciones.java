package com.serranoaaron.app.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Manejador global de excepciones para toda la aplicación.
 * Captura las excepciones y devuelve respuestas HTTP apropiadas al cliente.
 */
@RestControllerAdvice
public class ManejadorGlobalExcepciones {

    /**
     * Maneja las excepciones cuando no se encuentra un recurso (404 NOT FOUND)
     */
    @ExceptionHandler(RecursoNoEncontradoExcepcion.class)
    public ResponseEntity<RespuestaError> manejarRecursoNoEncontrado(
            RecursoNoEncontradoExcepcion excepcion,
            WebRequest solicitud) {
        
        RespuestaError respuesta = new RespuestaError(
                LocalDateTime.now(),
                excepcion.getMessage(),
                solicitud.getDescription(false),
                HttpStatus.NOT_FOUND.value()
        );
        
        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja las excepciones de solicitudes inválidas (400 BAD REQUEST)
     */
    @ExceptionHandler(SolicitudInvalidaExcepcion.class)
    public ResponseEntity<RespuestaError> manejarSolicitudInvalida(
            SolicitudInvalidaExcepcion excepcion,
            WebRequest solicitud) {
        
        RespuestaError respuesta = new RespuestaError(
                LocalDateTime.now(),
                excepcion.getMessage(),
                solicitud.getDescription(false),
                HttpStatus.BAD_REQUEST.value()
        );
        
        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja errores de validación de Bean Validation (@Valid)
     * Devuelve un mapa con todos los campos que fallaron la validación
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarErroresValidacion(
            MethodArgumentNotValidException excepcion) {
        
        Map<String, String> errores = new HashMap<>();
        
        excepcion.getBindingResult().getAllErrors().forEach(error -> {
            String nombreCampo = ((FieldError) error).getField();
            String mensajeError = error.getDefaultMessage();
            errores.put(nombreCampo, mensajeError);
        });
        
        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja cualquier otra excepción no controlada (500 INTERNAL SERVER ERROR)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespuestaError> manejarExcepcionGlobal(
            Exception excepcion,
            WebRequest solicitud) {
        
        RespuestaError respuesta = new RespuestaError(
                LocalDateTime.now(),
                "Error interno del servidor",
                excepcion.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        
        return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
