package com.cumpleanos.reccomprobantes.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
public class DateTimeUtils {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Parsear una cadena de texto a LocalDate
     *
     * @param dateStr la cadena de texto que representa la fecha
     * @return el objeto LocalDate correspondiente, o null si no se puede parsear
     */
    public static LocalDate parseDate(String dateStr) {
        try{
            return LocalDate.parse(dateStr, FORMATTER);
        }catch (DateTimeParseException e){
            log.error("Error al parsear la fecha: {}", e.getMessage(), e);
            return null;
        }
    }

    public static ZonedDateTime parseDateTime(String dateStr) {
        return ZonedDateTime.parse(dateStr);
    }
}
