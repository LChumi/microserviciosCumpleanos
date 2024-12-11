package com.cumpleanos.assist.utils;

import com.cumpleanos.assist.persistence.transformers.ProductImportTransformer;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;

import java.text.NumberFormat;
import java.text.ParseException;

@Slf4j
public class FileUtils {

    private static final String[] HeadersImport = {"ID", "ITEM", "NOMBRE", "CANTIDAD", "FOB", "PROVEEDOR", "CBM"};

    // Validación del encabezado del archivo
    public static boolean isValidHeaderImpor(Row headerRow) {
        if (headerRow.getLastCellNum() != HeadersImport.length) {
            return false; // Si el número de columnas no coincide, no es válido
        }

        for (int i = 0; i < HeadersImport.length; i++) {
            Cell cell = headerRow.getCell(i);
            if (cell == null || cell.getCellType() != CellType.STRING) {
                return false;
            }
            String cellValue = cell.getStringCellValue().trim();
            if (!cellValue.equalsIgnoreCase(HeadersImport[i])) {
                return false;
            }
        }
        return true;
    }

    // Mapeo de una fila a un objeto ProductImportTransformer
    public static ProductImportTransformer mapRowToProductImport(Row row) throws ParseException {
        return ProductImportTransformer.builder()
                .id(getCellValueSafely(row.getCell(0)))
                .item(getCellValueSafely(row.getCell(1)))
                .nombre(getCellValueSafely(row.getCell(2)))
                .cantidad(parseIntegerSafely(getCellValueSafely(row.getCell(3)))) // Valor predeterminado 0
                .fob(parseDoubleSafely(getCellValueSafely(row.getCell(4)))) // Valor predeterminado 0.0
                .proveedor(parseLongSafely(getCellValueSafely(row.getCell(5)))) // Valor predeterminado 0L
                .cbm(parseDoubleSafely(getCellValueSafely(row.getCell(6)))) // Valor predeterminado 0.0
                .build();
    }

    // Validación de fila vacía
    public static boolean isRowEmpty(Row row) {
        if (row == null) {
            return true;
        }
        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

    // Obtener valor seguro de una celda
    private static String getCellValueSafely(Cell cell) {
        if (cell == null) {
            return "";
        }
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell).trim();
    }

    // Conversión segura de String a Integer
    private static int parseIntegerSafely(String value) {
        try {
            return value.isEmpty() ? 0 : Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    // Conversión segura de String a Double
    private static double parseDoubleSafely(String value) {
        if (value == null || value.isEmpty()) {
            return 0.0;
        }

        try {
            NumberFormat format = NumberFormat.getInstance(); // Usa configuración regional actual
            return format.parse(value).doubleValue();
        } catch (Exception e) {
            System.err.println("Error al convertir valor a Double: " + value);
            return 0.0; // Valor predeterminado
        }
    }


    // Conversión segura de String a Long
    private static long parseLongSafely(String value) {
        try {
            return value.isEmpty() ? 0L : Long.parseLong(value);
        } catch (NumberFormatException e) {
            return 0L;
        }
    }
}