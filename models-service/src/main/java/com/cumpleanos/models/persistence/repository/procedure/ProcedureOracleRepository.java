package com.cumpleanos.models.persistence.repository.procedure;

import com.cumpleanos.common.records.ReposicionGenerado;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProcedureOracleRepository {

    private final EntityManager em;

    private static final String SP_GENERAR_REPOSICION = "PRG_USR.AST_WEB.GENERAR_REPOSICION_GENERAL";

    public ReposicionGenerado generarReposicion(Long empresa, Long bodega, Long almacen, Long usrLiquida, String usuario) {
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery(SP_GENERAR_REPOSICION);

            query.registerStoredProcedureParameter("PN_EMPRESA", Long.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("PN_BODEGA", Long.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("PN_ALMACEN", Long.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("PN_USR_LIQUIDA", Long.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("PN_USUARIO", String.class, ParameterMode.IN);

            query.registerStoredProcedureParameter("PN_CODIGO", BigDecimal.class, ParameterMode.OUT);
            query.registerStoredProcedureParameter("PN_VALOR", String.class, ParameterMode.OUT);

            query.setParameter("PN_EMPRESA", empresa);
            query.setParameter("PN_BODEGA", bodega);
            query.setParameter("PN_ALMACEN", almacen);
            query.setParameter("PN_USR_LIQUIDA", usrLiquida);
            query.setParameter("PN_USUARIO", usuario);

            query.execute();

            BigDecimal codigoDecimal = (BigDecimal) query.getOutputParameterValue("PN_CODIGO");
            BigInteger codigo = codigoDecimal != null ? codigoDecimal.toBigInteger() : null;
            String prp = (String) query.getOutputParameterValue("PN_VALOR");

            return new ReposicionGenerado(prp, codigo);

        } catch (Exception e) {
            log.error("Error ejecutando {} para usuario={}", SP_GENERAR_REPOSICION, usuario, e);
            throw new RuntimeException("Error ejecutando procedimiento generar Reposicion con el usuario: " + usuario, e);
        }
    }

}