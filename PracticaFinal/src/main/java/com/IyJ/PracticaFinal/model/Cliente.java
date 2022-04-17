package com.IyJ.PracticaFinal.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
@Table("CLIENTE")
public class Cliente {
    @Id
    private String dni;

    private String nombre;
    private String apellidos;
    private int telefono;
    private String correo;
}
