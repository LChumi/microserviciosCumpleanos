package com.cumpleanos.pos.service.implementation;

import com.cumpleanos.common.exception.ApiResponse;
import com.cumpleanos.pos.persistence.api.deuna.infoPayments.InfoRequest;
import com.cumpleanos.pos.persistence.api.deuna.infoPayments.InfoResponse;
import com.cumpleanos.pos.persistence.api.deuna.payments.PaymentRequest;
import com.cumpleanos.pos.persistence.api.deuna.payments.PaymentResponse;
import com.cumpleanos.pos.service.exception.HttpResponseHandler;
import com.cumpleanos.pos.service.http.IDeunaPaymentClientProd;
import com.cumpleanos.pos.service.http.IDeunaPaymentClientTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class DeunaPaymentClientService {

    private final IDeunaPaymentClientTest deunaPaymentClientTest;
    private final IDeunaPaymentClientProd deunaPaymentClientProd;

    public ApiResponse<PaymentResponse> getPaymentTest(String apiKeyTest,String apiSecretTest, PaymentRequest paymentRequest) {
        return HttpResponseHandler.handle(() ->
                        deunaPaymentClientTest.requestPaymet(apiKeyTest, apiSecretTest, paymentRequest),
                "Error en la obtencion del pago"
        );
    }

    public ApiResponse<InfoResponse> getInfoTest(String apiKeyTest,String apiSecretTest,InfoRequest infoRequest) {
        return HttpResponseHandler.handle(() ->
                deunaPaymentClientTest.requestInfoPayment(apiKeyTest,apiSecretTest,infoRequest),
                "Error en la obtencion de la info de pago"
                );
    }

    //metodos produccion
    public ApiResponse<PaymentResponse> getPayment(String apiKeyProd,String apiSecretProd,PaymentRequest paymentRequest) {
        return HttpResponseHandler.handle(() ->
                        deunaPaymentClientProd.requestPaymet(apiKeyProd, apiSecretProd, paymentRequest),
                "Error en la obtencion del pago"
        );
    }

    public ApiResponse<InfoResponse> getInfo(String apiKeyProd,String apiSecretProd,InfoRequest infoRequest) {
        return HttpResponseHandler.handle(() ->
                        deunaPaymentClientProd.requestInfoPayment(apiKeyProd, apiSecretProd,infoRequest),
                "Error en la obtencion de la info de pago"
        );
    }

}
