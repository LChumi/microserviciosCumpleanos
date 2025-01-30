package com.cumpleanos.assist.service.interfaces;

import com.cumpleanos.assist.persistence.dto.SolicitudRequestDTO;
import com.cumpleanos.assist.persistence.inmutables.SciResponse;

public interface ISolicitudImportacionService {
    SciResponse procesarSolicitud(SolicitudRequestDTO solicitudRequestDTO);
}
