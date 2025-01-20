package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.persistence.repository.functions.FunctionOracleRepository;
import com.cumpleanos.assist.persistence.repository.functions.ProcedureOracleRepository;
import com.cumpleanos.assist.service.exception.ProcedureNotCompletedException;
import com.cumpleanos.assist.service.interfaces.ISolicitudImportacionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class SolicitudImportacionServiceImpl implements ISolicitudImportacionService {

    private final ProcedureOracleRepository procedureRepository;
    private final FunctionOracleRepository functionRepository;


    @Override
    public String procesarSolicitud(Long empresa, Long tipoDoc, Long almacen, Long pventa, Long sigla, Long codCliPro, Long usuario, LocalDate fecha , Long modulo) {

        try {
            BigInteger cco= procedureRepository.getCabeceraIdByProcedure(
                    empresa, tipoDoc, almacen, pventa, sigla, codCliPro, usuario, fecha, modulo
            );
        } catch (ProcedureNotCompletedException e) {
            log.error("Error al generar la cabecera SCI: {}", e.getMessage(), e);
            throw e;
        }
        return "";
    }

     private String getComprobante(Long empresa, BigInteger coo) {
        try {
            return functionRepository.getComprobante(empresa, coo);
        } catch (Exception e) {
            throw new ProcedureNotCompletedException("Error al obtener el comprobante", e);
        }
    }
}
