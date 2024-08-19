package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.Dfactura;
import com.cumpleanos.core.models.ids.DfacturaId;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

@Repository
public interface DfacturaRepository extends JpaRepository<Dfactura, DfacturaId> {

    Set<Dfactura> findById_CcoOrderById_Secuencia(@NotNull BigInteger cco);

    List<Dfactura> findByFacComprobaAndDfacProducto(BigInteger facComproba, Long dfacProducto);

}
