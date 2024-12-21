package com.cumpleanos.assist.service.implementation.files;

import com.cumpleanos.assist.persistence.entity.ProductoTemp;
import com.cumpleanos.assist.persistence.transformers.ImpProdTrancitoTransformer;
import com.cumpleanos.assist.persistence.transformers.ProductImportTransformer;
import com.cumpleanos.assist.persistence.dto.ProductoDTO;
import com.cumpleanos.assist.persistence.views.ImpProdTrancitoVw;
import com.cumpleanos.assist.service.interfaces.IImpProdTrancitoVwService;
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
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FilesServicesImpl {

    private final IProductoService productoService;
    private final IProductoTempService productoTempService;
    private final IImpProdTrancitoVwService impProdTrancitoVwService;

    public List<ProductImportTransformer> readExcelFile(MultipartFile file, Long empresa) throws IOException {

        List<ProductImportTransformer> productosList = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0); // Leer la primera hoja
            Iterator<Row> rowIterator = sheet.iterator();

            // Encabezados
            Row headerRow = rowIterator.next();
            /*if (!FileUtils.isValidHeaderImpor(headerRow)) {
                throw new IOException("El formato del archivo Excel no es válido");
            }*/

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                // Verificar si la fila está vacía
                if (FileUtils.isRowEmpty(row)) {
                    break; // Detener el procesamiento si la fila está vacía
                }

                try {
                    productosList.add(FileUtils.mapRowToProductImport(row));
                } catch (ParseException e) {
                    log.error("Error al procesar la fila: {} " , e.getMessage());
                }
            }
        }

        // Verificar los productos antes de retornar
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
                    //ProductoTemp productoNuevo = productoTempService.save(productoTemp);
                    log.info("Nuevo producto {}", productoTemp);
                } else {
                    Set<ImpProdTrancitoVw> importaciones = impProdTrancitoVwService.getImpProdTrancitoVwsByProdAndEmpresa(temp.getCodigo(),empresa);
                    item.setTrancitos(chekImports(importaciones));
                    item.setStatus("Proceso");
                    calcularTotales(item);
                }
            }else {
                Set<ImpProdTrancitoVw> importaciones = impProdTrancitoVwService.getImpProdTrancitoVwsByProdAndEmpresa(producto.codigo(),empresa);
                item.setTrancitos(chekImports(importaciones));
                item.setStatus("Reposicion");
                calcularTotales(item);
            }
        }
    }

    private Set<ImpProdTrancitoTransformer> chekImports(Set<ImpProdTrancitoVw> items){
        if (items.isEmpty()) {
            return new HashSet<>();
        }
        Set<ImpProdTrancitoTransformer> importaciones = new HashSet<>();
        for (ImpProdTrancitoVw item : items) {
            importaciones.add(ImpProdTrancitoTransformer.mapToImpProdTrancitoVw(item));
        }
        return importaciones;
    }

    private void calcularTotales(ProductImportTransformer item){
        item.calcularCantidadEnTrancito();
        item.calcularCantidadTotal();
        item.calcularCbmTotal();
        item.calcularFobTotal();
    }

}
