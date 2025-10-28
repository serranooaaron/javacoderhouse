package com.serranoaaron.app.service;

import com.serranoaaron.app.model.Turno;
import com.serranoaaron.app.repository.TurnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTurno {

    TurnoRepository turnoRepository;

    public Turno ObtenerTurno(Long id){
       return turnoRepository.findById(id).orElse(null);
    }
    public List<Turno> ObtenerTurnos(){
        return turnoRepository.findAll();

    }
    public void CrearTurno(Turno turno){
        turnoRepository.save(turno);

    }
    public void ActualizarTurno(Long id, Turno turnoActualizado){
        if(turnoRepository.existsById(id)){
            turnoActualizado.setId(id);
            turnoRepository.save(turnoActualizado);
        }

    }
    public void EliminarTurno(Turno turno){
        turnoRepository.delete(turno);

    }

}
