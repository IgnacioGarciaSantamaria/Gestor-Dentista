package com.IyJ.PracticaFinal.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarTelefono {
    
    String telefono;


    public ValidarTelefono(String telefono) {
        this.telefono = telefono;
    }

    

    private static final String patterns
    = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
    + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
    + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";


    private static final Pattern REGEXP = Pattern.compile(patterns);

    public boolean validar() {
        Matcher matcher = REGEXP.matcher(telefono);
        return matcher.matches();   
    }

}
