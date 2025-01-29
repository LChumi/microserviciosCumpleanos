package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.core.models.dto.SolicitudComproImportacionDTO;

import java.math.BigInteger;

public interface ISolicitudCompraService {
    SolicitudComproImportacionDTO getSolicitudComproImportacion(BigInteger cco);
}
