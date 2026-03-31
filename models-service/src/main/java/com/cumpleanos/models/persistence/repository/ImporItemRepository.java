package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.Imporitem;
import com.cumpleanos.core.models.ids.ImporitemId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface ImporItemRepository extends JpaRepository<Imporitem, ImporitemId> {

    List<Imporitem> findById_ComprobaAndIitProducto(BigInteger idIitImpComproba, Long iitProducto);

    List<Imporitem> findById_Comproba(BigInteger idComproba);
}