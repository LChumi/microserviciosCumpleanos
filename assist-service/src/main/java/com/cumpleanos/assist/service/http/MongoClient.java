package com.cumpleanos.assist.service.http;

import com.cumpleanos.common.dtos.IndexNowRequest;
import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.common.records.SessionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "mongo-service")
public interface MongoClient {

    @GetMapping("/mongo/index/get/{name}")
    ResponseEntity<IndexNowRequest> getIndex(@PathVariable String name);

    @PostMapping("/mongo/session/saved")
    ResponseEntity<ServiceResponse> saveSession(@RequestBody SessionDTO sessionDTO);

    @GetMapping("/mongo/seesion/last-login/{userId}")
    ResponseEntity<ServiceResponse> getLastLogin(@PathVariable String userId);
}