package com.cumpleanos.assist.service.interfaces;

import com.cumpleanos.assist.persistence.dto.SolicitudRequestDTO;

import java.time.LocalDate;

public interface ISolicitudImportacionService {
    String procesarSolicitud(SolicitudRequestDTO solicitudRequestDTO);
}
