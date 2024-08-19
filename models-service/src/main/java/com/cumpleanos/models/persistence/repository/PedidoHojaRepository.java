package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.PedidoHoja;
import com.cumpleanos.core.models.ids.PedidoHojaId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface PedidoHojaRepository extends JpaRepository<PedidoHoja, PedidoHojaId> {

    PedidoHoja findById_CcoComprobaAndId_Hoja(BigInteger idCcoComproba, Long idHoja);
}