package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.Ccomproba;
import com.cumpleanos.core.models.ids.CcomprobaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface CcomprobaRepository extends JpaRepository<Ccomproba, CcomprobaId> {
    Optional<Ccomproba> findById_Codigo(BigInteger cco);

    @Query(value = "SELECT PRG_USR.AST_GEN.NUMERO_COMPROBANTE(:empresa, :coo) FROM DUAL", nativeQuery = true)
    String getComprobante(@Param("empresa") Long empresa, @Param("coo") BigInteger coo);
}
