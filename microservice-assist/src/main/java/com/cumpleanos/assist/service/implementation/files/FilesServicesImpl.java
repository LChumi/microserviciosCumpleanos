package com.cumpleanos.assist.service.implementation.files;

import com.cumpleanos.assist.persistence.records.ProductImportResponse;
import com.cumpleanos.assist.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FilesServicesImpl {

    public List<ProductImportResponse> readExcelFile(MultipartFile file) throws IOException {

        List<ProductImportResponse> productosList = new ArrayList<>();

        try(InputStream inputStream = file.getInputStream()){
            Workbook workbook = WorkbookFactory.create(inputStream);

            Sheet sheet = workbook.getSheetAt(0); //Leer la primera hoja
            Iterator<Row> rowIterator = sheet.iterator();

            //encabezados
            Row headerRow = rowIterator.next();
            if (!FileUtils.isValidHeaderImpor(headerRow)){
                throw new IOException("El formato del archivo Excel no es valido");
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                productosList.add(FileUtils.mapRowToProductImport(row));
            }
        } catch (ParseException e) {
            throw new RuntimeException("Error al convertir la celda a un atributo", e);
        }
        return productosList;
    }

}
