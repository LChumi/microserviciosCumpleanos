package com.cumpleanos.assist.utils;

import com.cumpleanos.assist.persistence.records.ProductImportResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

import java.text.NumberFormat;
import java.text.ParseException;

@Slf4j
public class FileUtils {

    private static final String[] HeadersImport ={"ID", "ITEM", "NOMBRE", "CANTIDAD", "FOB", "PROVEEDOR"};

    public static boolean isValidHeaderImpor(Row headerRow){
        for(int i=0; i<HeadersImport.length; i++){
            Cell cell = headerRow.getCell(i);
            if (cell == null || cell.getCellType() != CellType.STRING) {
                return false;
            }
            String cellValue = cell.getStringCellValue().trim();
            if (!cellValue.equalsIgnoreCase(HeadersImport[i])){
                return false;
            }
        }
        return true;
    }

    public static ProductImportResponse mapRowToProductImport(Row row) throws ParseException {
        DataFormatter formatter = new DataFormatter();
        NumberFormat numberFormat = NumberFormat.getInstance();

        return  ProductImportResponse.builder()
                .id(formatter.formatCellValue(row.getCell(0)))
                .item(formatter.formatCellValue(row.getCell(1)))
                .nombre(formatter.formatCellValue(row.getCell(2)))
                .cantidad((int) numberFormat.parse(formatter.formatCellValue(row.getCell(3))).intValue())
                .fob(numberFormat.parse(formatter.formatCellValue(row.getCell(4))).doubleValue())
                .proveedor((long) numberFormat.parse(formatter.formatCellValue(row.getCell(5))).longValue())
                .build();
    }
}
