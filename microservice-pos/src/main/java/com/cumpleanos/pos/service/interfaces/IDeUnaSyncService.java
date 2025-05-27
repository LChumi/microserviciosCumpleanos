package com.cumpleanos.pos.service.interfaces;

import com.cumpleanos.common.exception.ApiResponse;
import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.pos.persistence.api.deuna.infoPayments.InfoResponse;
import com.cumpleanos.pos.persistence.api.deuna.payments.PaymentResponse;

public interface IDeUnaSyncService {
    ApiResponse<PaymentResponse> procesarPago(Long usrLiquida, Long empresa);

    InfoResponse procesarInfoPayment(Long usrLiquida, Long empresa);

    ServiceResponse procesarInfoRecibo(Long usrLiquida, Long empresa);
}
