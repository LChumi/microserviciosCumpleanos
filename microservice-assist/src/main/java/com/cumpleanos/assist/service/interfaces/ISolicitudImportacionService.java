package com.cumpleanos.assist.service.interfaces;

import java.math.BigInteger;

public interface ISolicitudImportacionService {
    BigInteger generarCabeceraSCI(Long empresa, Long tipoDoc, Long almacen, Long codCliPro, Long usuario);
    void insertarDetalle(String detalle);
    void guardarSolicitud();
    void anularSCI();
}
