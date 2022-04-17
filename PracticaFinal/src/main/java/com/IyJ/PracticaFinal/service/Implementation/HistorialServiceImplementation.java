package com.IyJ.PracticaFinal.service.Implementation;

import java.time.LocalDate;

import com.IyJ.PracticaFinal.model.Historial;
import com.IyJ.PracticaFinal.repository.HistorialRepository;
import com.IyJ.PracticaFinal.service.HistorialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistorialServiceImplementation implements HistorialService{
    @Autowired
    private HistorialRepository historialRepository;

    @Override
    public Iterable<Historial> retreiveHistoriales(String fecha) {
        if( fecha == null){
            return historialRepository.findAll();
        } else {
            LocalDate fechaFormat = LocalDate.parse(fecha);
            return historialRepository.retreiveHistorialesByFecha(fechaFormat);
        }
    }
    
    @Override 
    public Historial retreiveHistorial(String id){
        Historial response = null;
        Long idLong = Long.parseLong(id);
        if(historialRepository.existsById(idLong)){
            Iterable<Historial> historiales = historialRepository.retreiveHistorial(idLong);
            for(Historial historial : historiales){
                response = historial;
            }
            return response;
        }
        return response;
    }

    @Override
    public void deleteHistorial(String id){
        Long idLong = Long.parseLong(id);
        historialRepository.deleteById(idLong);
    }

    @Override
    public Historial updateHistorial(String id,Historial historial){
        Long idLong = Long.parseLong(id);
        if(historialRepository.existsById(idLong)){
            return historialRepository.save(historial);
        } else {
            return null;
        }
    }
}
