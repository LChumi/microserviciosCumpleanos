package com.cumpleanos.assist.service.implementation.files;

import com.cumpleanos.assist.persistence.entity.ProductoTemp;
import com.cumpleanos.assist.persistence.transformers.ProductImportTransformer;
import com.cumpleanos.assist.persistence.dto.ProductoDTO;
import com.cumpleanos.assist.service.interfaces.IProductoService;
import com.cumpleanos.assist.service.interfaces.IProductoTempService;
import com.cumpleanos.assist.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FilesServicesImpl {

    private final IProductoService productoService;
    private final IProductoTempService productoTempService;

    public List<ProductImportTransformer> readExcelFile(MultipartFile file, Long empresa) throws IOException {

        List<ProductImportTransformer> productosList = new ArrayList<>();

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

        chekProduct(productosList, empresa);

        return productosList;
    }


    private void chekProduct(List<ProductImportTransformer> items, Long empresa){
        for (ProductImportTransformer item : items) {
            ProductoDTO producto= productoService.getProductoByBarraAndEmpresa(item.getId(),empresa);
            if (producto == null) {
                log.info("Producto no encontrado buscando en ProductoTemp");
                ProductoTemp temp = productoTempService.getProductoTempByBarraAndEmpresa(item.getId(),empresa);
                if (temp == null) {
                    log.warn("Producto no encontrado registrando en ProductoTemp");
                    item.setStatus("Nuevo");
                    //Crear en productoTemp
                    ProductoTemp productoTemp = new ProductoTemp();
                    productoTemp.setNombre(item.getNombre());
                    productoTemp.setProId(item.getId());
                    productoTemp.setEmpresa(empresa);
                    productoTemp.setProveedor(item.getProveedor());
                    log.info("Nuevo producto {}", productoTemp);
                } else {
                    item.setStatus("Proceso");
                }
            }else {
                item.setStatus("Reposicion");
            }
        }
    }

}
