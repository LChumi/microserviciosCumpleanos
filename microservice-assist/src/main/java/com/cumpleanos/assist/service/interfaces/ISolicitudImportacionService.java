package com.cumpleanos.assist.service.interfaces;

import com.cumpleanos.assist.persistence.dto.SolicitudRequestDTO;

public interface ISolicitudImportacionService {
    String procesarSolicitud(SolicitudRequestDTO solicitudRequestDTO);
}
