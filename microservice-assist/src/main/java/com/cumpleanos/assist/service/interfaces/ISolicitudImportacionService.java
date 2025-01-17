package com.cumpleanos.assist.service.interfaces;

import java.math.BigInteger;
import java.time.LocalDate;

public interface ISolicitudImportacionService {
    BigInteger generarCabeceraSCI(Long empresa, Long tipoDoc, Long almacen, Long pventa, Long sigla, Long codCliPro, Long usuario, LocalDate fecha , Long modulo);
    void insertarDetalle(String detalle);
    void guardarSolicitud();
    void anularSCI();
}
