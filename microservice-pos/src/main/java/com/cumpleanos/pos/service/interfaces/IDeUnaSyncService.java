package com.cumpleanos.pos.service.interfaces;

import com.cumpleanos.core.models.exception.ApiResponse;
import com.cumpleanos.pos.persistence.api.deuna.infoPayments.InfoResponse;
import com.cumpleanos.pos.persistence.api.deuna.payments.PaymentResponse;

public interface IDeUnaSyncService {
    ApiResponse<PaymentResponse> procesarPago(Long usrLiquida, Long empresa);
    InfoResponse procesarInfoPayment(Long usrLiquida, Long empresa);
}
