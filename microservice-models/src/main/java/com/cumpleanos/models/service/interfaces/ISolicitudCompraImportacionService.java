package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.core.models.dto.SolicitudCompraImportacionDTO;

import java.math.BigInteger;

public interface ISolicitudCompraImportacionService {
    SolicitudCompraImportacionDTO getSolicitudComproImportacion(BigInteger cco);
}
