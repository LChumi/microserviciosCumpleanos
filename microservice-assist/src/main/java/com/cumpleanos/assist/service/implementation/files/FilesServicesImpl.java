package com.cumpleanos.assist.service.implementation.files;

import com.cumpleanos.assist.persistence.transformers.ImpProdTrancitoTransformer;
import com.cumpleanos.assist.persistence.transformers.ProductImportTransformer;
import com.cumpleanos.assist.persistence.dto.ProductoDTO;
import com.cumpleanos.assist.persistence.views.ImpProdTrancitoVw;
import com.cumpleanos.assist.service.interfaces.importaciones.IImpProdTrancitoVwService;
import com.cumpleanos.assist.service.interfaces.IProductoService;
import com.cumpleanos.assist.service.interfaces.importaciones.IProductoTempService;
import com.cumpleanos.assist.utils.FileUtils;
import com.cumpleanos.core.models.entities.ProductoTemp;
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
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class FilesServicesImpl {

    private final IProductoService productoService;
    private final IProductoTempService productoTempService;
    private final IImpProdTrancitoVwService impProdTrancitoVwService;

    public List<ProductImportTransformer> readExcelFile(MultipartFile file, Long empresa) throws IOException {
        List<ProductImportTransformer> productosList;
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            productosList = mapRowsToProducts(sheet);
        }
        // Procesar flujo de productos
        productsFlow(productosList, empresa);
        return productosList;
    }

    public List<ProductImportTransformer> getInfoFromExcel(MultipartFile file, Long empresa) throws IOException {
        List<ProductImportTransformer> productosList;
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            productosList = mapRowsToProducts(sheet);
        }
        return productosList;
    }

    private List<ProductImportTransformer> mapRowsToProducts(Sheet sheet){
        List<ProductImportTransformer> productosList = new ArrayList<>();
        Iterator<Row> rowIterator = sheet.iterator();

        // Leer encabezado
        Row headerRow = rowIterator.next();
        /*if (!FileUtils.isValidHeaderImpor(headerRow)) {
            throw new IOException("El formato del archivo Excel no es válido");
        }*/

        int count = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next(); // Obtén la siguiente fila

            // Verifica si la fila está vacía
            if (FileUtils.isRowEmpty(row)) break;

            try {
                // Mapea la fila a un objeto ProductImportTransformer
                ProductImportTransformer item = FileUtils.mapRowToProductImport(row);
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

    private void productsFlow(List<ProductImportTransformer> productosList, Long empresa) {
        productosList.forEach(product -> {
            if (product.getId() == null || product.getId().isEmpty()) {
                handleNewOrRepositionedProduct(product, empresa);
            } else {
                checkProduct(product, empresa);
            }
        });
    }

    private void handleNewOrRepositionedProduct(ProductImportTransformer product, Long empresa) {
        ProductoTemp temp = productoTempService.getProductoTempByCodFabricaAndEmpresa(product.getCodFabrica(), empresa);
        if (temp == null) {
            product.setStatus("NUEVO");
            calcularTotales(product);
            saveOrUpdateProduct(Optional.empty(), product, empresa);
        } else {
            product.setStatus("REPOSICION");
            saveOrUpdateProduct(Optional.of(temp), product, empresa);
            getTrancitos(product, temp.getCodigo(), empresa);
            calcularTotales(product);
        }
    }

    private void checkProduct(ProductImportTransformer item, Long empresa) {
        ProductoDTO producto = productoService.getProductoByBarraAndEmpresa(item.getId(), empresa);
        if (producto == null) {
            ProductoTemp temp = findProductoTemp(item.getId(), item.getCodFabrica(), empresa);
            if (temp == null) {
                item.setStatus("NUEVO");
                calcularTotales(item);
                saveOrUpdateProduct(Optional.empty(), item, empresa);
            } else {
                item.setStatus("REGISTRADO");
                saveOrUpdateProduct(Optional.of(temp), item, empresa);
                getTrancitos(item, temp.getCodigo(), empresa);
            }
        } else {
            item.setStatus("REPOSICION");
            calcularTotales(item);
            getTrancitos(item, producto.codigo(), empresa);
        }
    }

    private ProductoTemp findProductoTemp(String id, String codFabrica, Long empresa) {
        ProductoTemp temp = productoTempService.getProductoTempByBarraAndEmpresa(id, empresa);
        if (temp == null) {
            temp = productoTempService.getProductoTempByCodFabricaAndEmpresa(codFabrica, empresa);
        }
        return temp;
    }

    private Set<ImpProdTrancitoTransformer> chekImports(Set<ImpProdTrancitoVw> items) {
        return items.stream()
                .map(ImpProdTrancitoTransformer::mapToImpProdTrancitoVw)
                .collect(Collectors.toSet());
    }

    private void calcularTotales(ProductImportTransformer item) {
        item.calcularCantidadTotal();
        item.calcularCbmTotal();
        item.calcularFobTotal();
    }

    private void saveOrUpdateProduct(Optional<ProductoTemp> prod, ProductImportTransformer item, Long empresa) {
        ProductoTemp productoTemp = prod.orElseGet(ProductoTemp::new);
        productoTemp.setNombre(item.getNombre().toUpperCase());
        productoTemp.setEmpresa(empresa);
        productoTemp.setCodFabrica(item.getCodFabrica());
        String proId = (item.getId() != null && !item.getId().isEmpty()) ? item.getId() : item.getCodFabrica();
        productoTemp.setProId(proId);
        ProductoTemp productoNuevo = productoTempService.save(productoTemp);
        log.info("Producto registrado en ProductoTemp {}", productoNuevo);
        calcularTotales(item);
    }

    private void getTrancitos(ProductImportTransformer item, Long proCodigo, Long empresa) {
        Set<ImpProdTrancitoVw> importaciones = impProdTrancitoVwService.getImpProdTrancitoVwsByProdAndEmpresa(proCodigo, empresa);
        if (importaciones.isEmpty()) {
            log.error("Importaciones vacías, no se registran los tránsitos.");
            item.setTrancitos(new HashSet<>());
        } else {
            item.setTrancitos(chekImports(importaciones));
            item.calcularCantidadEnTrancito();
        }
    }
}