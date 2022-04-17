package com.IyJ.PracticaFinal.controller;

import com.IyJ.PracticaFinal.model.Historial;
import com.IyJ.PracticaFinal.service.HistorialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HistorialController {
    @Autowired
    private HistorialService historialService;

    @GetMapping("/historiales")
    public ResponseEntity<Iterable<Historial>> retreiveHistoriales(@RequestParam(required=false) String fecha){
        Iterable<Historial> response = historialService.retreiveHistoriales(fecha);
        return ResponseEntity.ok().body(response);
    }
    
    @GetMapping("/historiales/{id}/")
    public ResponseEntity<Historial> retreiveHistorial(@PathVariable String id){
        Historial response = historialService.retreiveHistorial(id);
        return ResponseEntity.ok().body(response);
    }
    // /api/v1/historiales/?fecha=2008-10-31

    @DeleteMapping("/historiales/{id}/")
    public ResponseEntity<Historial> deleteHistorial(@PathVariable String id){
        historialService.deleteHistorial(id);
        return ResponseEntity.noContent().build();
    }

    // http://localhost:8080/api/v1/historiales/2/
    @PutMapping("/historiales/{id}/")
    public ResponseEntity<Historial> updateHistorial(@PathVariable String id, @RequestBody Historial historial){
        Historial newHistorial = historialService.updateHistorial(id, historial);
        if (newHistorial == null){
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok().body(newHistorial);
        }
    }

    
}
