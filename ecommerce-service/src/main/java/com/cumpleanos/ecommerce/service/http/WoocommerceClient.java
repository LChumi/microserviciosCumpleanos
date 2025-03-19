package com.cumpleanos.ecommerce.service.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "wooCommerceClient", url = "${woocommerce.url}/wp-json/wc/v3")
public interface WoocommerceClient {

    // Obtener todos los productos
    @GetMapping("/products")
    List<Map<String, Object>> getAllProducts(@RequestParam("consumer_key") String consumerKey,
                                             @RequestParam("consumer_secret") String consumerSecret);

    // Obtener un producto por ID
    @GetMapping("/products/{id}")
    Map<String, Object> getProductById(@PathVariable("id") int id,
                                       @RequestParam("consumer_key") String consumerKey,
                                       @RequestParam("consumer_secret") String consumerSecret);

    // Crear un producto
    @PostMapping("/products")
    Map<String, Object> createProduct(@RequestBody Map<String, Object> productData,
                                      @RequestParam("consumer_key") String consumerKey,
                                      @RequestParam("consumer_secret") String consumerSecret);

    // Actualizar un producto
    @PutMapping("/products/{id}")
    Map<String, Object> updateProduct(@PathVariable("id") int id,
                                      @RequestBody Map<String, Object> productData,
                                      @RequestParam("consumer_key") String consumerKey,
                                      @RequestParam("consumer_secret") String consumerSecret);

    // Eliminar un producto
    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable("id") int id,
                       @RequestParam("consumer_key") String consumerKey,
                       @RequestParam("consumer_secret") String consumerSecret);
}
