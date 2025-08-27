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
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class ProcedureOracleRepository {

    private final EntityManager em;

    public BigInteger getCabeceraIdByProcedure(Long empresa,
                                               Long tipoDoc,
                                               Long almacen,
                                               Long pventa,
                                               Long sigla,
                                               Long codCliPro,
                                               Long usuario,
                                               LocalDate fecha,
                                               Long modulo,
                                               Long bodega,
                                               String concepto
    ) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PRG_USR.AST_WEB.CREAR_CABECERA_CCO");

            query.registerStoredProcedureParameter("PN_EMPRESA", Long.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("PN_TIPODOC", Long.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("PN_ALMACEN", Long.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("PN_PVENTA", Long.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("PN_SIGLA", Long.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("PN_CODCLIPRO", Long.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("PN_USUARIO", Long.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("PN_FECHA", Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("PN_MODULO", Long.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("PN_BODEGA", Long.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("PN_CONCEPTO", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("PN_COMPROBANTE", BigInteger.class, ParameterMode.OUT);

            query.setParameter("PN_EMPRESA", empresa);
            query.setParameter("PN_TIPODOC", tipoDoc);
            query.setParameter("PN_ALMACEN", almacen);
            query.setParameter("PN_PVENTA", pventa);
            query.setParameter("PN_SIGLA", sigla);
            query.setParameter("PN_CODCLIPRO", codCliPro);
            query.setParameter("PN_USUARIO", usuario);
            query.setParameter("PN_FECHA", Date.valueOf(fecha));
            query.setParameter("PN_MODULO", modulo);
            query.setParameter("PN_BODEGA", bodega);
            query.setParameter("PN_CONCEPTO", concepto);

            query.execute();

            return (BigInteger) query.getOutputParameterValue("PN_COMPROBANTE");
        } catch (Exception e) {
            log.error("Error al crear la cabecera :{}", e.getMessage(), e);
            throw new ProcedureNotCompletedException("Error al crear cabecera." + e.getMessage());
        }
    }

    public Map<String, Long> generaUsrLiquidaWeb(Long empresa, Long codigo, Long liquida) {
        Map<String, Long> resultado = new HashMap<>();

        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("PRG_USR.AST_WEB.ASIGNA_USR_LIQUIDA");

            query.registerStoredProcedureParameter("PN_EMPRESA", Long.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("PN_CODIGO", Long.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("PN_LIQUIDA", Long.class, ParameterMode.IN);

            query.registerStoredProcedureParameter("PN_USR_LIQUIDA", Long.class, ParameterMode.OUT);
            query.registerStoredProcedureParameter("PN_ERROR", Long.class, ParameterMode.OUT);

            query.setParameter("PN_EMPRESA", empresa);
            query.setParameter("PN_CODIGO", codigo);
            query.setParameter("PN_LIQUIDA", liquida);

            query.execute();

            resultado.put("usrLiquida", (Long) query.getOutputParameterValue("PN_USR_LIQUIDA"));
            resultado.put("error", (Long) query.getOutputParameterValue("PN_ERROR"));

        } catch (Exception e) {
            throw new RuntimeException("Error ejecutando procedimiento generar UsrLiquida", e);
        }

        return resultado;
    }

    public String generarReposicion(Long empresa, Long bodega, Long almacen, Long usrLiquida, String usr){
        try{
            StoredProcedureQuery query = em.createStoredProcedureQuery("PRG_USR.AST_WEB.GENERAR_REPOSICION_GENERAL");

            query.registerStoredProcedureParameter("PN_EMPRESA", Long.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("PN_BODEGA", Long.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("PN_ALMACEN", Long.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("PN_USR_LIQUIDA", Long.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("PN_USUARIO", String.class, ParameterMode.IN);

            query.registerStoredProcedureParameter("PN_CODIGO", BigInteger.class, ParameterMode.OUT);
            query.registerStoredProcedureParameter("PN_VALOR", String.class, ParameterMode.OUT);

            query.setParameter("PN_EMPRESA", empresa);
            query.setParameter("PN_BODEGA", bodega);
            query.setParameter("PN_ALMACEN", almacen);
            query.setParameter("PN_USR_LIQUIDA", usrLiquida);
            query.setParameter("PN_USUARIO", usr);

            query.execute();

            return (String)query.getOutputParameterValue("PN_VALOR");

        } catch (Exception e) {
            throw new RuntimeException("Error ejecutando procedimiento generar Reposicion", e);
        }
    }

}
