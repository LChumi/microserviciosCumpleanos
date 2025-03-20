package com.cumpleanos.ecommerce.service.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "wooCommerceClient", url = "${woocommerce.url}/wp-json/wc/v3")
public interface WooCommerceClient {

    // 📌 CATEGORÍAS

    @GetMapping("/products/categories")
    List<Map<String, Object>> getAllCategories(@RequestParam("consumer_key") String consumerKey,
                                               @RequestParam("consumer_secret") String consumerSecret,
                                               @RequestParam("search") String search);

    @PostMapping("/products/categories")
    Map<String, Object> createCategory(@RequestBody Map<String, Object> categoryData,
                                       @RequestParam("consumer_key") String consumerKey,
                                       @RequestParam("consumer_secret") String consumerSecret);

    // 📌 PRODUCTOS

    @GetMapping("/products")
    List<Map<String, Object>> getAllProducts(@RequestParam("consumer_key") String consumerKey,
                                             @RequestParam("consumer_secret") String consumerSecret,
                                             @RequestParam("sku") String sku);

    @PostMapping("/products")
    Map<String, Object> createProduct(@RequestBody Map<String, Object> productData,
                                      @RequestParam("consumer_key") String consumerKey,
                                      @RequestParam("consumer_secret") String consumerSecret);
}