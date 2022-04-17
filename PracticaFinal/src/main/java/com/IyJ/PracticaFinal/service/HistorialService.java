package com.IyJ.PracticaFinal.service;

import com.IyJ.PracticaFinal.model.Historial;

public interface HistorialService {
    Iterable<Historial> retreiveHistoriales(String fecha);
    Historial retreiveHistorial(String id);
    void deleteHistorial(String id);
    Historial updateHistorial(String id,Historial historial);
}
