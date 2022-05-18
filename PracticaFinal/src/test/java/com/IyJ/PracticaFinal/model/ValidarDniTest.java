package com.IyJ.PracticaFinal.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ValidarDniTest {
    
    @Test
    public void validarDniCorrectoTest(){

        ValidarDni dni = new ValidarDni("53857194A");
        boolean resultado = dni.validar();
        assertEquals(true,resultado);

        dni = new ValidarDni("02567041B");
        resultado = dni.validar();
        assertEquals(true,resultado);

        dni = new ValidarDni("02568420X");
        resultado = dni.validar();
        assertEquals(true,resultado);
        System.out.println("hola");
    }

    @Test
    public void validarDniExcepcionesTest(){
        ValidarDni dni = new ValidarDni("00000000T");
        boolean resultado = dni.validar();
        assertEquals(false,resultado);

        dni = new ValidarDni("00000001R");
        resultado = dni.validar();
        assertEquals(false,resultado);

        dni = new ValidarDni("99999999R");
        resultado = dni.validar();
        assertEquals(false,resultado);
    }

    @Test
    public void validarDniPatronMalTest() {

        ValidarDni dni = new ValidarDni("345");
        boolean resultado = dni.validar();
        assertEquals(false,resultado);

        dni = new ValidarDni("4512N");
        resultado = dni.validar();
        assertEquals(false,resultado);

        dni = new ValidarDni("72849506Ñ");
        resultado = dni.validar();
        assertEquals(false,resultado);


        dni = new ValidarDni("728495063");
        resultado = dni.validar();
        assertEquals(false,resultado);
    }
}
