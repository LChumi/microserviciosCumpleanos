package com.cumpleanos.assist.utils;

import com.cumpleanos.assist.persistence.transformers.ImpProdTrancitoTransformer;
import com.cumpleanos.assist.persistence.transformers.ProductImportTransformer;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class FileUtils {

    private static final String[] HeadersImport = {"ID", "ITEM", "NOMBRE", "CANTIDAD", "FOB", "CBM", "CXB"};
    
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

    // Conversion de archivos a DTO
    public static List<ProductImportTransformer> mapRowsToProducts(Sheet sheet){
        List<ProductImportTransformer> productosList = new ArrayList<>();
        Iterator<Row> rowIterator = sheet.iterator();

        // Leer encabezado
        Row headerRow = rowIterator.next();
        /*if (!isValidHeaderImpor(headerRow)) {
            throw new IOException("El formato del archivo Excel no es válido");
        }*/

        int count = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next(); // Obtén la siguiente fila

            // Verifica si la fila está vacía
            if (isRowEmpty(row)) break;

            try {
                // Mapea la fila a un objeto ProductImportTransformer
                ProductImportTransformer item = mapRowToProductImport(row);
                // Incrementa el contador solo si la fila no está vacía
                count++;
                // Asigna el contador de secuencia
                item.setSecuencia(count);
                // Agrega el objeto a la lista de productos
                productosList.add(item);
            } catch (ParseException e) {
                // Registra un error si ocurre una excepción de tipo ParseException
                log.error("Error al procesar la fila: {}", e.getMessage());
            }
        }

        return productosList;
    }

    // Mapeo de una fila a un objeto ProductImportTransformer
    private static ProductImportTransformer mapRowToProductImport(Row row) throws ParseException {
        return ProductImportTransformer.builder()
                .id(getCellValueSafely(row.getCell(0)))
                .item(getCellValueSafely(row.getCell(1)))
                .nombre(getCellValueSafely(row.getCell(2)))
                .cantidad(parseIntegerSafely(getCellValueSafely(row.getCell(3)))) // Valor predeterminado 0
                .fob(parseDoubleSafely(getCellValueSafely(row.getCell(4)))) // Valor predeterminado 0.0
                .cbm(parseDoubleSafely(getCellValueSafely(row.getCell(5)))) // Valor predeterminado 0.0
                .cxb(parseIntegerSafely(getCellValueSafely(row.getCell(6))))
                .codFabrica(getCellValueSafely(row.getCell(7)))
                .cantidadTotal(0)
                .cbmTotal(0)
                .fobTotal(0)
                .cantidadTrancito(0)
                .trancitos(new HashSet<ImpProdTrancitoTransformer>())
                .build();
    }

    // Validación de fila vacía
    private static boolean isRowEmpty(Row row) {
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
            // Elimina cualquier símbolo de moneda y caracteres no numéricos
            value = value.replaceAll("[^\\d,.-]", "");

            // Usa NumberFormat para analizar el valor
            NumberFormat format = NumberFormat.getInstance();
            return format.parse(value).doubleValue();
        } catch (Exception e) {
            System.err.println("Error al convertir valor a Double: " + value);
            return 0.0; // Valor predeterminado
        }
    }

}