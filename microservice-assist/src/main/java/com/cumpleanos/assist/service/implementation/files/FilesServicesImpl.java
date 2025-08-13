package com.cumpleanos.assist.service.implementation.files;

import com.cumpleanos.assist.persistence.dto.OrdenCompraListDTO;
import com.cumpleanos.assist.persistence.transformers.ProductImportTransformer;
import com.cumpleanos.common.dtos.ProductoDTO;
import com.cumpleanos.assist.persistence.views.ImpProdTrancitoVw;
import com.cumpleanos.assist.service.exception.FileProcessingException;
import com.cumpleanos.assist.service.implementation.ClientServiceImpl;
import com.cumpleanos.assist.service.interfaces.importaciones.IFileService;
import com.cumpleanos.assist.service.interfaces.importaciones.IImpProdTrancitoVwService;
import com.cumpleanos.assist.service.interfaces.importaciones.IProductoTempService;
import com.cumpleanos.core.models.entities.ProductoTemp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.*;

import static com.cumpleanos.assist.utils.FileUtils.mapRowsToProducts;
import static com.cumpleanos.assist.utils.ProductImportDTOUtils.calcularTotales;
import static com.cumpleanos.assist.utils.ProductImportDTOUtils.chekImports;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class FilesServicesImpl implements IFileService {

    private final ClientServiceImpl productoService;
    private final IProductoTempService productoTempService;
    private final IImpProdTrancitoVwService impProdTrancitoVwService;
    private final ClientServiceImpl modelClient;

    @Override
    public List<ProductImportTransformer> readExcelFile(MultipartFile file, Long empresa) {
        List<ProductImportTransformer> productosList = extractProductsFromExcel(file);
        productsFlow(productosList, empresa); // Lógica adicional específica de este metodo
        return productosList;
    }

    @Override
    public OrdenCompraListDTO getListSCi(MultipartFile file, Long empresa, BigInteger cco) {
        List<ProductImportTransformer> withSCI = new ArrayList<>();
        List<ProductImportTransformer> notSCI = new ArrayList<>();

        List<ProductImportTransformer> productsList = extractProductsFromExcel(file);

        if (productsList.isEmpty() ) {
            throw new FileProcessingException("No se pudo procesar la informacion revise el documento de excel ");
        }

        for (ProductImportTransformer product : productsList) {
            boolean verify = validateExistingProduct(product, empresa);
            if (!verify) {
                notSCI.add(product);
            }else{
                Set<ImpProdTrancitoVw> imporExist = impProdTrancitoVwService.findByCcoAndProducto(cco, product.getId());
                if (imporExist == null) {
                    log.warn("No se encontraron registros CCO del producto dentro del primer documento buscando globalmente .......");
                    Set<ImpProdTrancitoVw> importaciones = impProdTrancitoVwService.findByProdIdAndEmpresa(product.getId(), empresa);
                    if (importaciones.isEmpty()) {
                        log.warn("No se encontro ningun registro del producto dentro del sistema no contiene CCO");
                        product.setTrancitos(new HashSet<>());
                        notSCI.add(product);
                    }else{
                        log.info("Se encontraron varios registros diferentes al cco principal");
                        product.setTrancitos(chekImports(importaciones));
                        product.calcularCantidadEnTrancito();
                        notSCI.add(product);
                    }
                } else if (imporExist.size() == 1) {
                    log.info("Se encontro un solo registro CCO");
                    product.setTrancitos(chekImports(imporExist));
                    ImpProdTrancitoVw unico = imporExist.iterator().next();
                    product.setCcoOrigen(unico.getCcoComproba());
                    withSCI.add(product);
                } else {
                    log.warn("Se encontraron miltiples registros para CCO {} y producto={}", cco, product.getId());
                    product.setTrancitos(chekImports(imporExist));
                    notSCI.add(product);
                }
            }
        }

        if (!withSCI.isEmpty()) {
            if (notSCI.isEmpty()) {
                log.info("Todos los trámites con registro se validaran los estados ");
                validateOrders(withSCI, empresa);
            } else {
                log.info("Trámites mixtos con y sin registros");
            }
        } else {
            log.info("Trámites sin registros, crear SCI");
        }


        return OrdenCompraListDTO.builder()
                .listWhitSci(withSCI)
                .listNotSci(notSCI)
                .build();
    }

    /**
     * Extrae la data del archivo de Excel y la transforma en una lista de productos.
     * @param file archivo Excel a procesar
     * @return lista de productos transformados
     * @throws FileProcessingException si ocurre un error de lectura
     */
    private List<ProductImportTransformer> extractProductsFromExcel(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            return mapRowsToProducts(sheet);
        } catch (IOException e) {
            log.error("Error al leer el archivo Excel");
            throw new FileProcessingException("Error al procesar el Archivo en el sistema", e);
        }
    }

    /**
     * Itera sobre los productos y analiza uno por uno en búsqueda de información relacionada.
     * @param productosList lista de productos transformados
     * @param empresa empresa donde se realizó la conversión
     */
    private void productsFlow(List<ProductImportTransformer> productosList, Long empresa) {
        productosList.forEach(product -> {
            if (product.getId() == null || product.getId().isEmpty()) {
                handleNewOrRepositionedProduct(product, empresa);
            } else {
                checkProduct(product, empresa);
            }
        });
    }

    /**
     * Valida si un producto es nuevo o reposición basado en sus datos.
     * * @param product producto convertido por Spring desde excel
     */
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

    /**
     * Valida si un producto es nuevo, reposición o registrado temporalmente.
     */
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

    /**
     * Valida si el producto tiene registro en la tabla Producto o Producto temporal
     * crea el producto en el caso de no existir y retorna False
     */
    private boolean validateExistingProduct(ProductImportTransformer item,Long empresa){
        ProductoDTO productoSis = productoService.getProductoByBarraAndEmpresa(item.getId(), empresa);
        if (productoSis == null) {
            //Si no existe el producto busca en registro temporal
            ProductoTemp temp = findProductoTemp(item.getId(), item.getCodFabrica(), empresa);
            if (temp == null) {
                log.warn("Producto no encontrado agregando a producto temporal .....");
                item.setStatus("SIN REGISTRO");
                saveOrUpdateProduct(Optional.empty(), item, empresa);
                return false;
            } else {
                log.info("Producto encontrado en Registro temporal");
                saveOrUpdateProduct(Optional.of(temp), item, empresa);
                return true;
            }
        }else {
            log.info("Producto existente en BD");
            return true;
        }
    }

    /**
     * Busca un producto temporal por código de barra o código de fábrica.
     * @return el producto temporal encontrado, o null si no existe
     */
    private ProductoTemp findProductoTemp(String id, String codFabrica, Long empresa) {
        ProductoTemp temp = productoTempService.getProductoTempByBarraAndEmpresa(id, empresa);
        if (temp == null) {
            temp = productoTempService.getProductoTempByCodFabricaAndEmpresa(codFabrica, empresa);
        }
        return temp;
    }

    /**
     * Guarda o actualiza los productos Temporales
     * @param prod ProductoTemporal
     * @param item producto Transformado del excel
     * @param empresa empresa donde se va a realizar la carga
     */
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

    /**
     * Obtiene los productos que se encuentran en trancitos en el registro
     * @param item producto transformado
     * @param proCodigo el codigo del producto
     * @param empresa empresa donde se va a buscar los trancitos
     */
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

    private void validateOrders(List<ProductImportTransformer> items, Long empresa) {
        for (ProductImportTransformer item : items) {
            String novedad = "";
            novedad = modelClient.getMatches(empresa, item.getId(), item.getItem());
            item.setNovedad(novedad);
        }
    }
}