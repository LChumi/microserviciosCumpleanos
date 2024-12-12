package com.cumpleanos.pos.service.http;

import com.cumpleanos.pos.persistence.api.deuna.payments.PaymentRequest;
import com.cumpleanos.pos.persistence.api.deuna.payments.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "paymentClient", url = "https://deuna-dev.apigee.net")
public interface IDeunaPaymentClient {

    @PostMapping("/merchant/api/v2/payment/request")
    ResponseEntity<PaymentResponse> requestPaymet(
            @RequestHeader("x-api-key") String apikey,
            @RequestHeader("x-api-secret") String apiSecret,
            @RequestBody PaymentRequest paymentRequest
    );
}
