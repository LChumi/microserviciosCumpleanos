package com.cumpleanos.models.utils;

public final class stringsUtils {

    private stringsUtils() {
        throw new UnsupportedOperationException("Clase de utilidades, no instanciable");
    }

    public static String normalizedItemsPrefix(String item) {
        return item.replaceFirst("^(EP|IC)", "");
    }
}
