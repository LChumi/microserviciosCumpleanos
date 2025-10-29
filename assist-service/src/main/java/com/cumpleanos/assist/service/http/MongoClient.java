package com.cumpleanos.assist.service.http;

import com.cumpleanos.common.dtos.IndexNowRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "mongo-service")
public interface MongoClient {

    @GetMapping("/mongo/index/get/{name}")
    ResponseEntity<IndexNowRequest> getIndex(@PathVariable String name);
}
