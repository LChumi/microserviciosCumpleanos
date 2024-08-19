package com.cumpleanos.ecommerce.service.http;

import com.cumpleanos.common.builders.ecommerce.PedidoWoocommerce;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "wooCommerceClient", url = "${wordpress.url}")
public interface WooCommerceClient {

    //CATEGORÍAS

    @GetMapping("/wp-json/wc/v3/products/categories")
    List<Map<String, Object>> getAllCategories(@RequestParam("consumer_key") String consumerKey,
                                               @RequestParam("consumer_secret") String consumerSecret,
                                               @RequestParam("search") String search,
                                               @RequestParam(value = "per_page", defaultValue = "100") Integer perPage)
            ;

    @PostMapping("/wp-json/wc/v3/products/categories")
    Map<String, Object> createCategory(@RequestBody Map<String, Object> categoryData,
                                       @RequestParam("consumer_key") String consumerKey,
                                       @RequestParam("consumer_secret") String consumerSecret);

    //PRODUCTOS

    @GetMapping("/wp-json/wc/v3/products")
    List<Map<String, Object>> getAllProducts(@RequestParam("consumer_key") String consumerKey,
                                             @RequestParam("consumer_secret") String consumerSecret,
                                             @RequestParam("sku") String sku);

    @PostMapping("/wp-json/wc/v3/products")
    Map<String, Object> createProduct(@RequestBody Map<String, Object> productData,
                                      @RequestParam("consumer_key") String consumerKey,
                                      @RequestParam("consumer_secret") String consumerSecret);

    @PutMapping("/wp-json/wc/v3/products/{id}")
    Map<String, Object> updateProduct(@PathVariable("id") int id,
                                      @RequestBody Map<String, Object> productData,
                                      @RequestParam("consumer_key") String consumerKey,
                                      @RequestParam("consumer_secret") String consumerSecret);


    //ORDERS
    @GetMapping("/wp-json/wc/v3/orders")
    List<Map<String, Object>> getOrders(@RequestParam("consumer_key") String consumerKey,
                                        @RequestParam("consumer_secret") String consumerSecret);

    @GetMapping("/wp-json/wc/v3/orders")
    List<PedidoWoocommerce> getOrdersByDate(
            @RequestParam("consumer_key") String consumerKey,
            @RequestParam("consumer_secret") String consumerSecret,
            @RequestParam("after") String after,  // Fecha inicio (ej. "2024-03-30T00:00:00")
            @RequestParam("before") String before // Fecha fin (ej. "2024-03-30T23:59:59")
    );
}