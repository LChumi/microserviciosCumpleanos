package com.cumpleanos.pos.service.implementation;

import com.cumpleanos.core.models.exception.ApiResponse;
import com.cumpleanos.pos.persistence.api.deuna.infoPayments.InfoRequest;
import com.cumpleanos.pos.persistence.api.deuna.infoPayments.InfoResponse;
import com.cumpleanos.pos.persistence.api.deuna.payments.PaymentRequest;
import com.cumpleanos.pos.persistence.api.deuna.payments.PaymentResponse;
import com.cumpleanos.pos.service.exception.HttpResponseHandler;
import com.cumpleanos.pos.service.http.IDeunaPaymentClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@PropertySource("classpath:api-keys.properties")
@Slf4j
public class DeunaPaymentClientService {

    private final IDeunaPaymentClient deunaPaymentClient;
    @Value("${x-api-key}")
    String apiKey;

    @Value("${x-api-secret}")
    String apiSecret;

    public ApiResponse<PaymentResponse> getPayment(PaymentRequest paymentRequest) {
        return HttpResponseHandler.handle(() ->
                        deunaPaymentClient.requestPaymet(apiKey, apiSecret, paymentRequest),
                "Error en la obtencion del pago"
        );
    }

    public ApiResponse<InfoResponse> getInfo(InfoRequest infoRequest) {
        return HttpResponseHandler.handle(() ->
                deunaPaymentClient.requestInfoPayment(apiKey,apiSecret,infoRequest),
                "Error en la obtencion de la info de pago"
                );
    }

}
