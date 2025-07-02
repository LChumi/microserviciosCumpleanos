package com.cumpleanos.ecommerce.service.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "wordpressAuthClient", url = "${wordpress.url}")
public interface WordpressAuthClient {

    @PostMapping("/wp-json/jwt-auth/v1/token")
    Map<String, Object> authenticate(@RequestParam("username") String username, @RequestParam("password") String password);

}
