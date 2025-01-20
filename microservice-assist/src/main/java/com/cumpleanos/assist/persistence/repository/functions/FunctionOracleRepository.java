package com.cumpleanos.assist.persistence.repository.functions;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface FunctionOracleRepository {

    @Query(value = "SELECT PRG_USR.AST_GEN.NUMERO_COMPROBANTE(:empresa, :coo) FROM DUAL", nativeQuery = true)
    String getComprobante(@Param("empresa") Long empresa, @Param("coo") BigInteger coo);
}
