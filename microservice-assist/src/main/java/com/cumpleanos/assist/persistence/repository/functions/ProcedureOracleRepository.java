package com.cumpleanos.assist.persistence.repository.functions;

import com.cumpleanos.assist.service.exception.ProcedureNotCompletedException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class ProcedureOracleRepository {

    private final EntityManager em;

    public BigInteger getCabeceraIdByProcedure(Long empresa, Long tipoDoc, Long almacen, Long codCliPro, Long usuario) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PRG_USR.AST_WEB.CREAR_CABECERA_CCO");

            query.registerStoredProcedureParameter("PN_EMPRESA", Long.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("PN_TIPODOC", Long.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("PN_ALMACEN", Long.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("PN_CODCLIPRO", Long.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("PN_USUARIO", Long.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("PN_COMPROBANTE", BigInteger.class, ParameterMode.OUT);

            query.setParameter("PN_EMPRESA", empresa);
            query.setParameter("PN_TIPODOC", tipoDoc);
            query.setParameter("PN_ALMACEN", almacen);
            query.setParameter("PN_CODCLIPRO", codCliPro);
            query.setParameter("PN_USUARIO", usuario);

            return (BigInteger) query.getOutputParameterValue("PN_COMPROBANTE");
        }catch (Exception e) {
            log.error("Error al crear la cabecera :{}", e.getMessage(), e);
            throw new ProcedureNotCompletedException("Error al crear cabecera." + e.getMessage());
        }
    }
}