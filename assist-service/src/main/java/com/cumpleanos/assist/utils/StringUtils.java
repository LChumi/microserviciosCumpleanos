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
        if (bigDecimal == null) {
            return "";
        }

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setDecimalSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#.##", symbols);
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
        if (categoria == null || categoria.isEmpty()) {
            log.info("Categoría vacía");
            return "";
        }

        // Proteger ñ y Ñ antes de normalizar
        categoria = categoria.replace("ñ", "__enie__").replace("Ñ", "__ENIE__");

        //Normalizar y eliminar tildes
        String limpio = Normalizer.normalize(categoria, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}", "");

        //Restaurar ñ y Ñ
        limpio = limpio.replace("__enie__", "ñ").replace("__ENIE__", "Ñ");

        //Quitar caracteres no deseados
        limpio = limpio.replaceAll("\\s*/\\s*", " ");
        limpio = limpio.replaceAll("[^a-zA-ZñÑ0-9\\- ]", " ");
        limpio = limpio.replaceAll("\\s{2,}", " ").trim();

        return limpio;
    }

    public static Integer longToInteger(Long valor) {
        return Math.toIntExact(valor);
    }
}
