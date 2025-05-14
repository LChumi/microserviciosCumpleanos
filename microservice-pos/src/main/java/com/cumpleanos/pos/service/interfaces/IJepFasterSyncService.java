package com.cumpleanos.pos.service.interfaces;

import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.pos.persistence.api.jep.JepResponseQr;
import com.cumpleanos.pos.persistence.api.jep.NotificacionJep;

public interface IJepFasterSyncService {

    JepResponseQr generarQR(Long usrLiquida, Long empresa);

    ServiceResponse validarPago(Long usrLiquida, Long empresa);

    ServiceResponse procesarPago(NotificacionJep notificacionJep);

    ServiceResponse verificarPago(Long usrLiquida, Long empresa);
}
