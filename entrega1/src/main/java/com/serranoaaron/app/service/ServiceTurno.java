package com.serranoaaron.app.service;

import com.serranoaaron.app.model.Turno;
import com.serranoaaron.app.repository.TurnoRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceTurno {
    private final TurnoRepository turnoRepository;

    public ServiceTurno(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

     @Transactional(readOnly = true)
     public Turno ObtenerTurno(Long id){
         return turnoRepository.findById(id).orElse(null);
     }
    @Transactional(readOnly = true)
    public List<Turno> ObtenerTurnos(){
        return turnoRepository.findAll();

    }
   
    @Transactional
    public void CrearTurno(Turno turno){
        turnoRepository.save(turno);

    }
    @Transactional
    public void ActualizarTurno(Long id, Turno turnoActualizado){
        if(turnoRepository.existsById(id)){
            turnoActualizado.setId(id);
            turnoRepository.save(turnoActualizado);
        }

    }
    @Transactional
    public void EliminarTurno(Turno turno){
        turnoRepository.delete(turno);

    }

}
