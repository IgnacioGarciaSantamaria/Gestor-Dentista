package com.IyJ.PracticaFinal.repository;

import java.time.LocalDate;

import com.IyJ.PracticaFinal.model.Historial;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface HistorialRepository extends CrudRepository<Historial,Long>{
    @Query("SELECT * FROM HISTORIAL WHERE HISTORIAL.DATE= :fecha")
    public Iterable<Historial> retreiveHistorialesByFecha(LocalDate fecha);

    @Query("SELECT * FROM HISTORIAL WHERE HISTORIAL.ID= :id")
    public Iterable<Historial> retreiveHistorial(Long id);
}
