package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.persistence.repository.functions.ProcedureOracleRepository;
import com.cumpleanos.assist.service.interfaces.ISolicitudImportacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class SolicitudImportacionServiceImpl implements ISolicitudImportacionService {

    private final ProcedureOracleRepository procedureRepository;

    @Override
    public BigInteger generarCabeceraSCI(Long empresa, Long tipoDoc, Long almacen, Long pventa, Long sigla,  Long codCliPro, Long usuario) {
        return procedureRepository.getCabeceraIdByProcedure(empresa, tipoDoc, almacen, pventa, sigla, codCliPro, usuario);
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
