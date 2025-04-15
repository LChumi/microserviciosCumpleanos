package com.cumpleanos.common.utils;

public class CedulatUtils {

    public static Short tipoCedula(String cedula) {
        //Verificar si el string solo tiene numeros
        if (cedula.matches("\\d+")) {
            if (cedula.length() == 10) {
                return 1;
            }else if (cedula.length() == 13) {
                return 2;
            }
        }else {
            return 3;
        }
        return 3;
    }

    public static String validarCaracteres(String data, int caracteres) {
        if (data.length() > caracteres) {
            return data.substring(0, caracteres);
        }
        return data;
    }
}
