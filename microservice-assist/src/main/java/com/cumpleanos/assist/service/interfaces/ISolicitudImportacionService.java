package com.cumpleanos.assist.service.interfaces;

public interface ISolicitudImportacionService {
    void generarCabeceraSCI();
    void insertarDetalle(String detalle);
    void guardarSolicitud();
    void anularSCI();
}
