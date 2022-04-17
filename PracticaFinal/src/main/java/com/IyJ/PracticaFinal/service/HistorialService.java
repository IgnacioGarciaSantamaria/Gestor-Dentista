package com.IyJ.PracticaFinal.service;

import java.util.List;

import com.IyJ.PracticaFinal.model.Historial;
import com.IyJ.PracticaFinal.joins.HistorialTratamientosJoin;

public interface HistorialService {
    Iterable<Historial> retreiveHistoriales(String fecha);
    Historial retreiveHistorial(String id);
    void deleteHistorial(String id);
    Historial updateHistorial(String id,Historial historial);
    Historial createHistorial(Historial historial);
    
    List<HistorialTratamientosJoin> retreiveHistorialTratamiento();
}
