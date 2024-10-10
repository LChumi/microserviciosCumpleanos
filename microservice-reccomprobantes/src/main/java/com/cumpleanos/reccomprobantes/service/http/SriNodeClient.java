package com.cumpleanos.reccomprobantes.service.http;

import com.cumpleanos.reccomprobantes.persistence.models.json.ComprobanteJson;
import com.cumpleanos.reccomprobantes.persistence.models.json.request.AutorizacionRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name ="node-sri-api",url = "http://192.168.112.245:3000/cumple/wsdl")
public interface SriNodeClient {

    @PostMapping("/autorizacion/")
    ResponseEntity<ComprobanteJson> autorizacion(@RequestBody AutorizacionRequest request);

}
