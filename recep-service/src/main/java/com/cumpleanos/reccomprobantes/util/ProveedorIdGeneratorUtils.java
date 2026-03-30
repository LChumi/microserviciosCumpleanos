package com.cumpleanos.reccomprobantes.util;

public final class ProveedorIdGeneratorUtils {

    private ProveedorIdGeneratorUtils() {
        throw new UnsupportedOperationException("Clase de utilidades, no instanciable");
    }

    public static String generarPrefix(String nombre) {
        return "PN-"+nombre.substring(0, 3).toUpperCase().trim();
    }

}
