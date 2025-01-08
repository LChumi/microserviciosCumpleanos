package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.service.interfaces.ISolicitudImportacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class SolicitudImportacionServiceImpl implements ISolicitudImportacionService {

    @Override
    public void generarCabeceraSCI() {

    }

    @Override
    public void insertarDetalle(String detalle) {

    }

    @Override
    public void guardarSolicitud() {

    }

    @Override
    public void anularSCI() {

    }
}
