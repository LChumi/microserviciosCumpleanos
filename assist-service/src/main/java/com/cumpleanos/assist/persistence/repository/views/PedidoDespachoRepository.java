package com.cumpleanos.assist.persistence.repository.views;

import com.cumpleanos.core.models.views.FacDespedidowebV;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface PedidoDespachoRepository extends JpaRepository<FacDespedidowebV, BigInteger> {

    List<FacDespedidowebV> findByEstadoOrderByUrgenteDescComprobanteAscHojaAsc(Integer estado);

    List<FacDespedidowebV> findByUsrIdLikeAndEstadoOrderByUrgenteDescComprobanteAscHojaAsc(String usrId, Integer estado);

}