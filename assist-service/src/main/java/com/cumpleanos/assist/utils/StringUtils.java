package com.cumpleanos.assist.utils;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Normalizer;
import java.util.*;

@Slf4j
public class StringUtils {

    public static String bigDecimalToString(BigDecimal bigDecimal) {

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US); //Uso de punto en decimales
        symbols.setDecimalSeparator('.');

        DecimalFormat decimalFormat = new DecimalFormat("#.##", symbols); //Define el formato
        return decimalFormat.format(bigDecimal);
    }

    /**
     * Metodo para sanatizar un string y subir un texto limpio a la pagina web
     *
     * @param valor texto a verificar si tiene caracteres especiales
     * @return el texto limpio de caracteres
     */
    public static String stringCleaner(String valor) {
        if (valor == null || valor.isEmpty()) return "";

        // Normalizar y quitar acentos
        String limpio = Normalizer.normalize(valor, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}", "");

        // Reemplazar ñ por n
        limpio = limpio.replaceAll("ñ", "n").replaceAll("Ñ", "N");

        // Reemplazar cualquier forma de / por espacio
        limpio = limpio.replaceAll("\\s*/\\s*", " ");

        // Eliminar caracteres especiales excepto letras, números, guiones y espacios
        limpio = limpio.replaceAll("[^a-zA-Z0-9\\- ]", " ").trim();

        // Eliminar códigos al inicio (más flexible)
        limpio = limpio.replaceFirst("^(?i)[A-Z0-9\\-/]+\\s+", "");

        // Tokenizar y filtrar
        String[] tokens = limpio.split("\\s+");

        Set<String> descartables = new HashSet<>(Arrays.asList(
                "ART", "ART.", "DEC", "DEC.", "NAV", "NAV.", "FIESTA", "NAVIDAD", "DECORACION"
        ));

        List<String> resultado = new ArrayList<>();
        for (String token : tokens) {
            if (!descartables.contains(token.toUpperCase())) {
                resultado.add(token);
            }
        }

        return String.join(" ", resultado).replaceAll("\\s{2,}", " ").trim();
    }

    public static String limpiarCategoria(String categoria) {
        if (categoria == null || categoria.isEmpty()){
            log.info("Categoria vacia ");
            return "";
        }

        // Normalizar y quitar acentos
        String limpio = Normalizer.normalize(categoria, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}", "");

        // Reemplazar ñ por n
        limpio = limpio.replaceAll("ñ", "n").replaceAll("Ñ", "N");

        // Reemplazar cualquier forma de / por espacio
        limpio = limpio.replaceAll("\\s*/\\s*", " ");

        // Eliminar caracteres especiales excepto letras, números, guiones y espacios
        limpio = limpio.replaceAll("[^a-zA-Z0-9\\- ]", " ");

        // Compactar espacios múltiples
        return limpio.replaceAll("\\s{2,}", " ").trim();
    }


    public static Integer longToInteger(Long valor) {
        return Math.toIntExact(valor);
    }
}
