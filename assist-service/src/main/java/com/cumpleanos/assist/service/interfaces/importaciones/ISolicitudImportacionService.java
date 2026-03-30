package com.cumpleanos.assist.service.interfaces.importaciones;

import com.cumpleanos.assist.persistence.dto.SolicitudRequestDTO;
import com.cumpleanos.assist.persistence.inmutables.ImportacionRequest;
import com.cumpleanos.assist.persistence.inmutables.SciResponse;

public interface ISolicitudImportacionService {

    SciResponse procesarSolicitud(SolicitudRequestDTO solicitudRequestDTO);

    SciResponse procesarOrden(SolicitudRequestDTO solicitudRequestDTO);

    SciResponse procesarImportacion(ImportacionRequest request);

}
