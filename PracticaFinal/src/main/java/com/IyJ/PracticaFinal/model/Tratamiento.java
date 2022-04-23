package com.IyJ.PracticaFinal.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;




@Table("TRATAMIENTO")
public class Tratamiento {
    @Id
    private Long id;

    private String nombre;
    private int precio;
    private String descripcion;

    public Tratamiento(Long id, String nombre, int precio, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }   
}