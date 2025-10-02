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

    /**
     * Metodo para sanatizar un string y subir un texto limpio a la pagina web
     * @param valor texto a verificar si tiene caracteres especiales
     * @return el texto limpio de caracteres
     */
    public static String stringCleaner(String valor) {
        if (valor == null || valor.isEmpty()) {
            return "";
        }

        // Normalizar y quitar acentos
        String limpio = Normalizer.normalize(valor, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}", "");

        // Reemplazar ñ por n
        limpio = limpio.replaceAll("ñ", "n").replaceAll("Ñ", "N");

        // Reemplazar / sin espacio por espacio
        limpio = limpio.replaceAll("/(?! )", " ");

        // Eliminar caracteres especiales excepto letras, números y espacios
        limpio = limpio.replaceAll("[^a-zA-Z0-9 ]", "").trim();

        // Eliminar códigos al inicio (alfanuméricos sin espacios)
        limpio = limpio.replaceFirst("^[A-Z0-9\\-]{5,}\\s+", "");

        return limpio;
    }

    public static Integer longToInteger(Long valor) {
        return Math.toIntExact(valor);
    }
}
