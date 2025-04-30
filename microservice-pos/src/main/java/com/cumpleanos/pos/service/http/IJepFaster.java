package com.cumpleanos.pos.service.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "jepFfaster", url = "https://192.168.100.130:8685/serviciosenlineaBaseETH")
public interface IJepFaster {

}
