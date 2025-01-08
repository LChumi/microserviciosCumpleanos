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
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
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
        productsFlow(productosList, empresa);

        return productosList;
    }

    private void productsFlow(List<ProductImportTransformer> productosList, Long empresa) {
        for (ProductImportTransformer product : productosList) {
            //Producto no tiene barra en el excel
            if (product.getId()==null || product.getId().isEmpty()){
                ProductoTemp temp = productoTempService.getProductoTempByCodFabricaAndEmpresa(product.getCodFabrica(), empresa);
                //Si producto Temporal no existe
                if (temp==null){
                    product.setStatus("Nuevo");
                    saveOrUpdateProduct(product, empresa);
                }else {
                    product.setStatus("Reposicion");
                    saveOrUpdateProduct(product, empresa);
                    getTrancitos(product, temp.getCodigo(), empresa);
                }
            }else {
                chekProduct(product, empresa);
            }

        }
    }

    private void chekProduct(ProductImportTransformer item, Long empresa){
            ProductoDTO producto= productoService.getProductoByBarraAndEmpresa(item.getId(),empresa);
            //Si producto no existe en Tabla Producto
            if (producto == null) {
                log.info("Producto no encontrado buscando en ProductoTemp por barra");
                ProductoTemp temp = productoTempService.getProductoTempByBarraAndEmpresa(item.getId(),empresa);
                //Si producto Temporal no existe por barra
                if (temp == null) {
                    log.warn("Producto no encontrado en ProductoTemp buscando por codFabrica");
                    ProductoTemp tempCodFabrica = productoTempService.getProductoTempByCodFabricaAndEmpresa(item.getCodFabrica(), empresa);
                    //Si producto temporal no existe por codigo fabrica
                    if (tempCodFabrica == null) {
                        item.setStatus("Sin registro");
                        log.error("Producto no se encuentra registrado dentro del sistema");
                    } else { //Producto temporal existe por codigo fabrica
                        item.setStatus("Reposicion");
                        saveOrUpdateProduct(item, empresa);
                        getTrancitos(item,tempCodFabrica.getCodigo(),empresa);
                    }
                } else { //Producto temporal existe por barra
                    saveOrUpdateProduct(item, empresa);
                    item.setStatus("Reposicion");
                    getTrancitos(item, temp.getCodigo(),empresa);
                }
            }else { //Producto existe en Tabla producto
                item.setStatus("Reposicion");
                getTrancitos(item,producto.codigo(),empresa);
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

    private void saveOrUpdateProduct(ProductImportTransformer item, Long empresa){
        ProductoTemp productoTemp = new ProductoTemp();
        productoTemp.setNombre(item.getNombre());
        productoTemp.setEmpresa(empresa);
        productoTemp.setCodFabrica(item.getCodFabrica());
        productoTemp.setProId(item.getId() != null ? item.getId() : "");
        log.info("Producto registrado en ProductoTemp {}", productoTemp);
        //ProductoTemp productoNuevo = productoTempService.save(productoTemp);
    }

    private void getTrancitos(ProductImportTransformer item ,Long proCodigo, Long empresa){
        Set<ImpProdTrancitoVw> importaciones = impProdTrancitoVwService.getImpProdTrancitoVwsByProdAndEmpresa(proCodigo,empresa);
        if (importaciones.isEmpty()){
            log.error("Importaciones vacias no se registran los trancitos ");
        }else {
            item.setTrancitos(chekImports(importaciones));
            calcularTotales(item);
        }
    }

}
