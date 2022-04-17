package com.IyJ.PracticaFinal.joins;

public class HistorialTratamientosJoin {
    
    private Long id;
    private String dni;
    private String nombre;
    private int precio;
    private String descripcion;

    public HistorialTratamientosJoin(Long id, String dni, String nombre, int precio, String descripcion) {
        this.id = id;
        this.dni = dni;
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
