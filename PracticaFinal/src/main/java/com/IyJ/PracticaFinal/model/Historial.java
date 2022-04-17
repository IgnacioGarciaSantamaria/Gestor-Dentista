package com.IyJ.PracticaFinal.model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;




@Table("HISTORIAL")
public class Historial {
    @Id
    private Long id;

    private String dni;
    private String idTratamiento;
    private LocalDate date;
    private LocalTime time;

    public Historial(Long id, String dni, String idTratamiento, LocalDate date, LocalTime time) {
        this.id = id;
        this.dni = dni;
        this.idTratamiento = idTratamiento;
        this.date = date;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(String idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    

    
}
