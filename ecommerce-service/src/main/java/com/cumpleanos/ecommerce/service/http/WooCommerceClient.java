package com.cumpleanos.ecommerce.service.http;

import com.cumpleanos.common.builders.ecommerce.PedidoWoocommerce;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "wooCommerceClient", url = "${woocommerce.url}/wp-json/wc/v3")
public interface WooCommerceClient {

    //CATEGORÍAS

    @GetMapping("/products/categories")
    List<Map<String, Object>> getAllCategories(@RequestParam("consumer_key") String consumerKey,
                                               @RequestParam("consumer_secret") String consumerSecret,
                                               @RequestParam("search") String search);

    @PostMapping("/products/categories")
    Map<String, Object> createCategory(@RequestBody Map<String, Object> categoryData,
                                       @RequestParam("consumer_key") String consumerKey,
                                       @RequestParam("consumer_secret") String consumerSecret);

    //PRODUCTOS

    @GetMapping("/products")
    List<Map<String, Object>> getAllProducts(@RequestParam("consumer_key") String consumerKey,
                                             @RequestParam("consumer_secret") String consumerSecret,
                                             @RequestParam("sku") String sku);

    @PostMapping("/products")
    Map<String, Object> createProduct(@RequestBody Map<String, Object> productData,
                                      @RequestParam("consumer_key") String consumerKey,
                                      @RequestParam("consumer_secret") String consumerSecret);

    @PutMapping("/products/{id}")
    Map<String, Object> updateProduct(@PathVariable("id") int id,
                                      @RequestBody Map<String, Object> productData,
                                      @RequestParam("consumer_key") String consumerKey,
                                      @RequestParam("consumer_secret") String consumerSecret);


    //ORDERS
    @GetMapping("/orders")
    List<Map<String, Object>> getOrders(@RequestParam("consumer_key") String consumerKey,
                                        @RequestParam("consumer_secret") String consumerSecret);

    @GetMapping("/orders")
    List<PedidoWoocommerce> getOrdersByDate(
            @RequestParam("consumer_key") String consumerKey,
            @RequestParam("consumer_secret") String consumerSecret,
            @RequestParam("after") String after,  // Fecha inicio (ej. "2024-03-30T00:00:00")
            @RequestParam("before") String before // Fecha fin (ej. "2024-03-30T23:59:59")
    );
}