package com.IyJ.PracticaFinal.service.Implementation;

import java.util.Iterator;
import java.util.List;

import java.time.LocalDate;

import com.IyJ.PracticaFinal.model.Historial;
import com.IyJ.PracticaFinal.repository.HistorialRepository;
import com.IyJ.PracticaFinal.service.HistorialService;
import com.IyJ.PracticaFinal.joins.HistorialTratamientosJoin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;

@Service
public class HistorialServiceImplementation implements HistorialService{
    @Autowired
    private HistorialRepository historialRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Historial createHistorial(Historial historial) {
        Historial newHistorial = null;
        String dni = historial.getDni();
        Iterable<Historial> lista = historialRepository.retreiveHistorial(dni);
        Iterator<Historial> it = lista.iterator();
        int sum = 0;
        while (it.hasNext()) {
            it.next();
            sum++;
        }
        if(sum == 0){
            newHistorial = historialRepository.save(historial); 
            return newHistorial;
        }else{
            return newHistorial;
        }
    }

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
    public Historial retreiveHistorial(String dni){
        Historial response = null;
        if(historialRepository.retreiveHistorial(dni) != null){
            Iterable<Historial> historiales = historialRepository.retreiveHistorial(dni);
            for(Historial historial : historiales){
                response = historial;
            }
            return response;
        }
        return response;
    }

    @Override
    public void deleteHistorial(String dni){
        Long entrada = null;
        Iterable<Long> ids = historialRepository.retreiveId(dni); 
        for(Long id : ids){
            entrada = id;
        }
        historialRepository.deleteById(entrada);
    }

    @Override
    public Historial updateHistorial(String dni,Historial historial){
        Long entrada = null;
        Iterable<Long> ids = historialRepository.retreiveId(dni); 
        for(Long id : ids){
            entrada = id;
        }
        if(entrada != null){
            historialRepository.deleteById(entrada);
            return historialRepository.save(historial);
        } else {
            return null;
        }
    }

    @Override
    public List<HistorialTratamientosJoin> retreiveHistorialTratamiento() {
        String query="SELECT HISTORIAL.ID, HISTORIAL.DNI, TRATAMIENTO.NOMBRE, TRATAMIENTO.PRECIO, TRATAMIENTO.DESCRIPCION "
				+ "FROM HISTORIAL "
				+ "LEFT JOIN TRATAMIENTO ON HISTORIAL.ID_TRATAMIENTO=TRATAMIENTO.ID;";
		
		
		List<HistorialTratamientosJoin> historialTratamientos = jdbcTemplate.query(
                query,
                (rs, rowNum) -> {
                        return new HistorialTratamientosJoin(
                                rs.getLong("HISTORIAL.ID"),
                                rs.getString("HISTORIAL.DNI"),
                                rs.getString("TRATAMIENTO.NOMBRE"),
                                rs.getInt("TRATAMIENTO.PRECIO"),
                                rs.getString("TRATAMIENTO.DESCRIPCION"));
                }
        );
		
		
		return historialTratamientos;
    }
}
