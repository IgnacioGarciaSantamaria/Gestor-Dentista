package com.IyJ.PracticaFinal.joins;

import java.sql.Date;
import java.sql.Time;
//import java.time.LocalDate;
//import java.time.LocalTime;

public class ClienteHistorialJoin {

    private Long id;
    private String nombre;
    private String apellidos;
    private String dni;
    private Date date;
    private Time time;
    private Long idTratamiento;

    public ClienteHistorialJoin(Long id, String nombre, String apellidos, String dni, Date date, Time time, Long idTratamiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.setDate(date);
        this.setTime(time);
        this.idTratamiento = idTratamiento;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(Long idTratamiento) {
        this.idTratamiento = idTratamiento;
    }
}
