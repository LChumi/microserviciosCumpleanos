package com.cumpleanos.reccomprobantes.service.http;

import com.cumpleanos.common.records.CompanyParametersRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "mongo-service")
public interface MongoClient {

    @GetMapping("/mongo/company/{companyId}")
    ResponseEntity<CompanyParametersRecord> getByCompanyId(@PathVariable Long companyId);
}
