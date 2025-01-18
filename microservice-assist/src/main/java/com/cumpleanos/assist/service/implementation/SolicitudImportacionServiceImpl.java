package com.cumpleanos.assist.service.implementation;

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

    @Override
    public BigInteger generarCabeceraSCI(Long empresa,
                                         Long tipoDoc,
                                         Long almacen,
                                         Long pventa,
                                         Long sigla,
                                         Long codCliPro,
                                         Long usuario,
                                         LocalDate fecha,
                                         Long modulo) {
        // Validar parámetros
        if (empresa == null || tipoDoc == null || almacen == null || pventa == null || sigla == null ||
                codCliPro == null || usuario == null || fecha == null || modulo == null) {
            throw new IllegalArgumentException("Todos los parámetros son obligatorios.");
        }

        // Llamar al repositorio para ejecutar el procedimiento
        try {
            return procedureRepository.getCabeceraIdByProcedure(
                    empresa, tipoDoc, almacen, pventa, sigla, codCliPro, usuario, fecha, modulo
            );
        } catch (ProcedureNotCompletedException e) {
            // Puedes capturar y registrar detalles adicionales si es necesario
            log.error("Error al generar la cabecera SCI: {}", e.getMessage(), e);
            throw e; // Re-lanzar la excepción para que suba al controlador
        }
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

    @Override
    public String procesarSolicitud(Long empresa, Long tipoDoc, Long almacen, Long pventa, Long sigla, Long codCliPro, Long usuario, LocalDate fecha , Long modulo) {

        return "";
    }
}
