package com.IyJ.PracticaFinal.model;


import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;


public class ValidarTelefonoTest {
    @Test
    public void validarTelefonoValidoTest() {
        //Teléfonos válidos
        ValidarTelefono telefono = new ValidarTelefono("628389354");
        boolean resultado = telefono.validar();
        assertEquals(true,resultado);


        telefono = new ValidarTelefono("+34 666666666");
        resultado = telefono.validar();
        assertEquals(true,resultado);


        telefono = new ValidarTelefono("626855391");
        resultado = telefono.validar();
        assertEquals(true,resultado);
    }

    @Test
    public void validarTelefonoNoValidoTest() {
        ValidarTelefono telefono = new ValidarTelefono("66666");
        Boolean resultado = telefono.validar();
        assertEquals(false,resultado);

        telefono = new ValidarTelefono("66666666B");
        resultado = telefono.validar();
        assertEquals(false,resultado);

        telefono = new ValidarTelefono("66666-");
        resultado = telefono.validar();
        assertEquals(false,resultado);

        telefono = new ValidarTelefono("+3A 666666666");
        resultado = telefono.validar();
        assertEquals(false,resultado);
    }
}
