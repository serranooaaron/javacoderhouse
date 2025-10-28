package com.serranoaaron.app.controller;

import com.serranoaaron.app.model.Turno;
import com.serranoaaron.app.service.ServiceTurno;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turnos")
public class TurnoController {

    private final ServiceTurno serviceTurno;

    public TurnoController(ServiceTurno serviceTurno) {
        this.serviceTurno = serviceTurno;
    }

    @GetMapping
    public ResponseEntity<List<Turno>> ObtenerTurnos(){
        return new ResponseEntity<>(serviceTurno.ObtenerTurnos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> ObtenerTurno(@PathVariable Long id){
        Turno t = serviceTurno.ObtenerTurno(id);
        if(t == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> CrearTurno(@RequestBody Turno turno){
        serviceTurno.CrearTurno(turno);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> ActualizarTurno(@RequestBody Turno turno, @PathVariable Long id){
        serviceTurno.ActualizarTurno(id, turno);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> EliminarTurno(@RequestBody Turno turno){
        serviceTurno.EliminarTurno(turno);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
