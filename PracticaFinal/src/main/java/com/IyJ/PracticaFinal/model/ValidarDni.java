package com.IyJ.PracticaFinal.model;

import java.util.Arrays;
import java.util.regex.Pattern;

public class ValidarDni {
    String dniValue;

    public ValidarDni(String dniValue){
        this.dniValue=dniValue;
    }


    public boolean validar() {
      return Arrays.binarySearch(new String[]{"00000000T", "00000001R", "99999999R"}, dniValue) < 0 // (1)
              && Pattern.compile("[0-9]{8}[A-Z]").matcher(dniValue).matches() // (2)
              && dniValue.charAt(8) == "TRWAGMYFPDXBNJZSQVHLCKE".charAt(Integer.parseInt(dniValue.substring(0, 8)) % 23); // (3)
    }
}
