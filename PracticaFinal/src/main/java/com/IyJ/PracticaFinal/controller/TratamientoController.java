package com.IyJ.PracticaFinal.controller;

import com.IyJ.PracticaFinal.model.Tratamiento;
import com.IyJ.PracticaFinal.repository.TratamientoRepository;
import com.IyJ.PracticaFinal.service.TratamientoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TratamientoController {
    @Autowired
    private TratamientoService tratamientoService;

    @GetMapping("/tratamientos")
    public ResponseEntity<Iterable<Tratamiento>> retreiveTratamientos(){
        Iterable<Tratamiento> response = tratamientoService.retreiveTratamientos();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/tratamientos/{id}/")
    public ResponseEntity<Tratamiento> retreiveTratamiento(@PathVariable String id){
        Tratamiento response = tratamientoService.retreiveTratamiento(id);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/tratamientos")
    public ResponseEntity<Tratamiento> createTratamiento(@RequestBody Tratamiento tratamiento) {
        tratamiento.setId(null);
        Tratamiento newTratamiento = tratamientoService.createTratamiento(tratamiento);
        return ResponseEntity.ok().body(newTratamiento);
    }

    @DeleteMapping("/tratamientos/{id}/")
    public ResponseEntity<Tratamiento> deleteTratamiento(@PathVariable String id){
        tratamientoService.deleteTratamiento(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/tratamientos/{id}/")
    public ResponseEntity<Tratamiento> updateTratamiento(@PathVariable String id, @RequestBody Tratamiento tratamiento){
        Tratamiento newTratamiento = tratamientoService.updateTratamiento(id, tratamiento);
        if (newTratamiento == null){
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok().body(newTratamiento);
        }
    }
}
