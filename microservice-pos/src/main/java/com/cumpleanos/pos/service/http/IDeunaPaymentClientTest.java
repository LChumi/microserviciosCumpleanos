package com.cumpleanos.pos.service.http;

import com.cumpleanos.pos.persistence.api.deuna.cancelTransaction.CancelRequest;
import com.cumpleanos.pos.persistence.api.deuna.cancelTransaction.CancelResponse;
import com.cumpleanos.pos.persistence.api.deuna.devolutions.DevolutionRequest;
import com.cumpleanos.pos.persistence.api.deuna.devolutions.DevolutionResponse;
import com.cumpleanos.pos.persistence.api.deuna.infoPayments.InfoRequest;
import com.cumpleanos.pos.persistence.api.deuna.infoPayments.InfoResponse;
import com.cumpleanos.pos.persistence.api.deuna.payments.PaymentRequest;
import com.cumpleanos.pos.persistence.api.deuna.payments.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "paymentClientTest", url = "https://deuna-test.apigee.net")
public interface IDeunaPaymentClientTest {

    @PostMapping("/merchant/api/v2/payment/request")
    ResponseEntity<PaymentResponse> requestPaymet(
            @RequestHeader("x-api-key") String apikey,
            @RequestHeader("x-api-secret") String apiSecret,
            @RequestBody PaymentRequest paymentRequest
    );

    @PostMapping("/merchant/api/v1/payment/info")
    ResponseEntity<InfoResponse> requestInfoPayment(
            @RequestHeader("x-api-key") String apikey,
            @RequestHeader("x-api-secret") String apiSecret,
            @RequestBody InfoRequest infoRequest
    );

    @PostMapping("/merchant/api/v1/payment/cancel")
    ResponseEntity<CancelResponse> requestCancelPayment(
            @RequestHeader("x-api-key") String apikey,
            @RequestHeader("x-api-secret") String apiSecret,
            @RequestBody CancelRequest cancelRequest
    );

    @PostMapping("/merchant/api/v1/payment/void")
    ResponseEntity<DevolutionResponse> requestDevolutionPayment(
            @RequestHeader("x-api-key") String apikey,
            @RequestHeader("x-api-secret") String apiSecret,
            @RequestBody DevolutionRequest devolutionRequest
    );

}
