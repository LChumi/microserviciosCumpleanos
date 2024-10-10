package com.cumpleanos.reccomprobantes.util;

public class ProveedorIdGeneratorUtils {

    public static String generarPrefix(String nombre) {
        return "PN-"+nombre.substring(0, 3).toUpperCase().trim();
    }

}
