package com.IyJ.PracticaFinal.repository;

import com.IyJ.PracticaFinal.model.Tratamiento;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface TratamientoRepository extends CrudRepository<Tratamiento,String>{
    @Query("SELECT * FROM TRATAMIENTO WHERE TRATAMIENTO.ID= :id")
    public Iterable<Tratamiento> retreiveTratamiento(String id);
}
