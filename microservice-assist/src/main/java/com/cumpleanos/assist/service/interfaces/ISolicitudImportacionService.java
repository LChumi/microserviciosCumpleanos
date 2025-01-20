package com.cumpleanos.assist.service.interfaces;

import java.time.LocalDate;

public interface ISolicitudImportacionService {
    String procesarSolicitud(Long empresa, Long tipoDoc, Long almacen, Long pventa, Long sigla, Long codCliPro, Long usuario, LocalDate fecha , Long modulo);
}
