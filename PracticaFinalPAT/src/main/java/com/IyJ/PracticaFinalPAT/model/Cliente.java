package com.IyJ.PracticaFinalPAT.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("CLIENTES")
public class Cliente {
    @Id
    private String dni;

    private String nombre;
    private String apellidos;
    private String correo;
    private int telefono;
}