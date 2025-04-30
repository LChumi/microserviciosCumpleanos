package com.cumpleanos.pos.service.interfaces;

import com.cumpleanos.common.exception.ApiResponse;
import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.pos.persistence.api.jep.JepResponseQr;

public interface IJepFasterSyncService {

    JepResponseQr generarQR(Long usrLiquida, Long empresa);

    ServiceResponse validarPago(Long usrLiquida, Long empresa);

    ServiceResponse procesarPago(Long usrLiquida, Long empresa);
}
