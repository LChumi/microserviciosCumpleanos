package com.cumpleanos.reccomprobantes.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
public class DateTimeUtils {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate parseDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            log.error("La cadena de fecha es nula o está vacía");
            return null;
        }
        try {
            return LocalDate.parse(dateStr, FORMATTER);
        } catch (DateTimeParseException e) {
            log.error("Error al parsear la fecha: {}", e.getMessage(), e);
            return null;
        }
    }

    public static ZonedDateTime parseDateTime(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            log.error("La cadena de fecha y hora es nula o está vacía");
            return null;
        }
        try {
            return ZonedDateTime.parse(dateStr);
        } catch (DateTimeParseException e) {
            log.error("Error al parsear la fecha y hora: {}", e.getMessage(), e);
            return null;
        }
    }

}
