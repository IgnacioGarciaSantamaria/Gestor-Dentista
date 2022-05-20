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

    public Historial() {
    }

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((dni == null) ? 0 : dni.hashCode());
        result = prime * result + ((idTratamiento == null) ? 0 : idTratamiento.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Historial other = (Historial) obj;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (dni == null) {
            if (other.dni != null)
                return false;
        } else if (!dni.equals(other.dni))
            return false;
        if (idTratamiento == null) {
            if (other.idTratamiento != null)
                return false;
        } else if (!idTratamiento.equals(other.idTratamiento))
            return false;
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        return true;
    }

    

    
}
