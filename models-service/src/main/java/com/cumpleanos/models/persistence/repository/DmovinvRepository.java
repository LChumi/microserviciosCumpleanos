package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.Dmovinv;
import com.cumpleanos.core.models.ids.DmovinvId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface DmovinvRepository extends JpaRepository<Dmovinv, DmovinvId> {

    Dmovinv findById_CmoComprobaAndProducto(BigInteger idCmoComproba, Long producto);
}
