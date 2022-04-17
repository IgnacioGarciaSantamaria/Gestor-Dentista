package com.IyJ.PracticaFinal.joins;

public class ClienteHistorialJoin {
    private Long clienteId;
    private Long historialId;
    private String nombre;
    private String apellidos;
    private int telefono;
    private String correo;
    private Long idTratamiento;

    public ClienteHistorialJoin(Long clienteId, Long historialId, String nombre, String apellidos, int telefono, String correo, Long idTratamiento) {
        this.clienteId = clienteId;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo = correo;
        this.historialId = historialId;
        this.idTratamiento = idTratamiento;
    }

    public Long getClienteId() {
        return clienteId;
    }
    public void setClienteId(Long historialId) {
        this.historialId = historialId;
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
    public int getTelefono() {
        return telefono;
    }
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Long getHistorialId() {
        return historialId;
    }

    public void setHistorialId(Long historialId) {
        this.historialId = historialId;
    }

    public Long getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(Long idTratamiento) {
        this.idTratamiento = idTratamiento;
    }
}
