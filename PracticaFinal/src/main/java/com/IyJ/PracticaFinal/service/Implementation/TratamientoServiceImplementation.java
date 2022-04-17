package com.IyJ.PracticaFinal.service.Implementation;

import com.IyJ.PracticaFinal.model.Tratamiento;
import com.IyJ.PracticaFinal.repository.TratamientoRepository;
import com.IyJ.PracticaFinal.service.TratamientoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TratamientoServiceImplementation implements TratamientoService{
    @Autowired
    private TratamientoRepository tratamientoRepository;

    @Override
    public Tratamiento createTratamiento(Tratamiento tratamiento) {
        return tratamientoRepository.save(tratamiento);
    }

    @Override
    public Iterable<Tratamiento> retreiveTratamientos(){
        return tratamientoRepository.findAll();
    }

    @Override
    public Tratamiento retreiveTratamiento(String id){
        Tratamiento response = null;
        if(tratamientoRepository.existsById(id)){
            Iterable<Tratamiento> tratamientos = tratamientoRepository.retreiveTratamiento(id);
            for(Tratamiento tratamiento : tratamientos){
                response = tratamiento;
            }
            return response;
        }
        return response;
    }
    
    @Override
    public void deleteTratamiento(String id){
        tratamientoRepository.deleteById(id);
    }

    @Override
    public Tratamiento updateTratamiento(String id,Tratamiento tratamiento){
        if(tratamientoRepository.existsById(id)){
            return tratamientoRepository.save(tratamiento);
        } else {
            return null;
        }
    }
}
