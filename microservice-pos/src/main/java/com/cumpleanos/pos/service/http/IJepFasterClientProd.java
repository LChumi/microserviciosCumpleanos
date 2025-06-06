package com.cumpleanos.pos.service.http;

import com.cumpleanos.pos.persistence.api.jep.JepRequestQr;
import com.cumpleanos.pos.persistence.api.jep.JepResponseQr;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "jepFfaster", url = "http://157.100.17.30:8285/serviciosenlineaBasev9/")
public interface IJepFasterClientProd {

    @PostMapping("/integracioncomercial/qr-generation-process")
    ResponseEntity<JepResponseQr> getQR(@RequestBody JepRequestQr request);
}
