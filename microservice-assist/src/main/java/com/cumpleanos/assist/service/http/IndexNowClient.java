package com.cumpleanos.assist.service.http;

import com.cumpleanos.common.dtos.IndexNowRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "indexNowClient", url = "https://api.indexnow.org")
public interface IndexNowClient {

    @PostMapping("/indexnow")
    ResponseEntity<String> indexNow(@RequestBody IndexNowRequest request);
}
