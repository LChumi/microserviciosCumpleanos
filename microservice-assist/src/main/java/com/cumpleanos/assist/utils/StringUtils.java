package com.cumpleanos.assist.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Normalizer;
import java.util.Locale;

public class StringUtils {

    public static String bigDecimalToString(BigDecimal bigDecimal) {

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US); //Uso de punto en decimales
        symbols.setDecimalSeparator('.');

        DecimalFormat decimalFormat = new DecimalFormat("#.##", symbols); //Define el formato
        return decimalFormat.format(bigDecimal);
    }

    public static String stringCleaner(String valor) {
        if (valor == null || valor.isEmpty()) {
            return "";
        }

        // Normalizar el texto y eliminar acentos
        String limpio = Normalizer.normalize(valor, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

        // Reemplazar la "ñ" por "n"
        limpio = limpio.replaceAll("ñ", "n").replaceAll("Ñ", "N");

        // Eliminar caracteres especiales, dejando solo letras, números y espacios
        limpio = limpio.replaceAll("[^a-zA-Z0-9 ]", "").trim();

        return limpio;
    }

    public static Integer longToInteger(Long valor){
        return Math.toIntExact(valor);
    }
}
