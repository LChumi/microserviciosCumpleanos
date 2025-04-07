package com.cumpleanos.ecommerce.service.implementations;

import com.cumpleanos.ecommerce.configuration.WooCommerceProperties;
import com.cumpleanos.ecommerce.persistence.dto.ProductRequest;
import com.cumpleanos.ecommerce.service.exceptions.ImageUploadException;
import com.cumpleanos.ecommerce.service.exceptions.WoocommerceDataNotFound;
import com.cumpleanos.ecommerce.service.http.WooCommerceClient;
import com.cumpleanos.ecommerce.service.interfaces.WooCommerceService;
import com.cumpleanos.ecommerce.utils.ProcessType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static com.cumpleanos.ecommerce.utils.WoocommerceUtils.*;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@PropertySource("classpath:ecommerce.properties")
public class WooCommerceServiceImpl implements WooCommerceService {

    private final WooCommerceClient wooCommerce;
    private final WooCommerceProperties properties;
    private final WooCommerceMediaServiceImpl mediaService;

    // Obtener una categoría por nombre
    @Override
    public Integer obtenerCategoriaId(String nombreCategoria) {
        List<Map<String, Object>> categories = wooCommerce.getAllCategories(properties.getClient(), properties.getSecretClient(), nombreCategoria);

        if (!categories.isEmpty()) {
            return (Integer) categories.get(0).get("id");
        }
        return null;
    }

    @Override
    public Integer obtenerProductoId(String sku) {
        List<Map<String, Object>> existingProducts = wooCommerce.getAllProducts(properties.getClient(), properties.getSecretClient(), sku);
        if (!existingProducts.isEmpty()) {
            return (Integer) existingProducts.get(0).get("id");
        }
        return null;
    }

    // Crear una categoría si no existe
    @Override
    public Integer crearCategoria(String nombreCategoria, Integer categoriaPadre) {
        if (nombreCategoria == null || nombreCategoria.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría no puede estar vacío");
        }

        Map<String, Object> categoriaData = new HashMap<>();
        categoriaData.put("name", nombreCategoria);

        // Si la categoría tiene un padre, lo agregamos
        if (categoriaPadre != null) {
            categoriaData.put("parent", categoriaPadre);
        }

        // Enviar la categoría a WooCommerce
        Map<String, Object> categoriaCreada = wooCommerce.createCategory(categoriaData, properties.getClient(), properties.getSecretClient());

        // Verificar si la API devolvió un ID correcto
        if (categoriaCreada != null && categoriaCreada.containsKey("id")) {
            return (Integer) categoriaCreada.get("id");
        } else {
            log.error("Error al crear la categoría: {} ", categoriaCreada);
            return null; // Manejo de error
        }
    }

    // Subir un producto con categoría y subcategoría
    @Override
    public Map<String, Object> subirProducto(ProductRequest request) {
        // Verificar si la categoría existe, si no, crearla
        Integer categoriaId = ensureCategoryExists(request.categoria(), null);

        // Verificar si la subcategoría existe, si no, crearla bajo la categoría padre
        Integer subcategoriaId = ensureCategoryExists(request.subcategoria(), categoriaId);

        Integer imageId=null;
        try {
            imageId = mediaService.uploadImage(request.sku());
        }catch (IOException e){
            log.error("Advertencia error en el servicio la imagen no existe o no se pudo subir", e);
        }
        // Crear el producto con la subcategoría asignada y convertir a Map
        Map<String, Object> productData = convertObjectToMap(request, imageId);

        //  Asegurar que categories[] contenga un Integer
        List<Map<String, Object>> categorias = new ArrayList<>();
        categorias.add(Map.of("id", subcategoriaId));
        productData.put("categories", categorias);

        return wooCommerce.createProduct(productData, properties.getClient(), properties.getSecretClient());
    }

    @Override
    public Map<String, Object> actualizarProducto(String sku, Integer process, ProductRequest request) {
        if (sku == null) {
            throw new IllegalArgumentException("El ID del producto no puede ser nulo");
        }

        Integer productId= obtenerProductoId(sku);
        if (productId == null) {
            throw new WoocommerceDataNotFound("Producto no encontrado para SKU: " + sku);
        }

        ProcessType processType =ProcessType.getByProcess(process);

        if (processType == null) {
            throw new IllegalArgumentException("Tipo de proceso no válido para el valor proporcionado: " + process);
        }

        Optional<Integer> imageId = uploadImage(processType, request.sku());

        // Convertimos el objeto de request a un Map
        Map<String, Object> productData = convertObjectToMap(request, imageId.orElse(null));

        // Validamos y actualizamos la categoría si es necesario
        Integer categoriaId = ensureCategoryExists(request.categoria(), null);

        Integer subcategoriaId = ensureCategoryExists(request.subcategoria(), categoriaId);

        // Agregamos las categorías al producto
        List<Map<String, Object>> categorias = new ArrayList<>();
        categorias.add(Map.of("id", subcategoriaId));
        productData.put("categories", categorias);

        // Llamamos a la API de WooCommerce para actualizar el producto
        return wooCommerce.updateProduct(productId, productData, properties.getClient(), properties.getSecretClient());
    }

    @Override
    public List<Map<String, Object>> getOrders() {
        return wooCommerce.getOrders(properties.getClient(), properties.getSecretClient());
    }

    @Override
    public List<Map<String, Object>> getOrdesrByDate(LocalDate fecha, LocalDate fechaFin) {
        return wooCommerce.getOrdersByDate(properties.getClient(), properties.getSecretClient(), startOfDay(fecha) , endOfDay(fechaFin));
    }

    private Optional<Integer> uploadImage(ProcessType process, String sku) {
        try {
            return Optional.ofNullable(switch (process) {
                case UPLOAD_IMAGE -> mediaService.uploadImage(sku);
                case UPDATE_PRODUCT_ONLY -> null;
                default -> throw new IllegalArgumentException("Proceso no reconocido: " + process);
            });
        } catch (IOException e) {
            throw new ImageUploadException("Error al subir la imagen", e);
        }
    }

    private Integer ensureCategoryExists(String categoryName, Integer parentId){
        Integer categoryId = obtenerCategoriaId(categoryName.trim());
        if (categoryId == null) {
            categoryId = crearCategoria(categoryName, parentId);
        }
        return categoryId;
    }
}