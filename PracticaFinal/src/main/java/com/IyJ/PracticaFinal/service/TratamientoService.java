package com.IyJ.PracticaFinal.service;

import com.IyJ.PracticaFinal.model.Tratamiento;

public interface TratamientoService {
    Iterable<Tratamiento> retreiveTratamientos();
    Tratamiento retreiveTratamiento(String id);
    void deleteTratamiento(String id);
    Tratamiento updateTratamiento(String dni,Tratamiento tratamiento);
    Tratamiento createTratamiento(Tratamiento tratamiento);
}
