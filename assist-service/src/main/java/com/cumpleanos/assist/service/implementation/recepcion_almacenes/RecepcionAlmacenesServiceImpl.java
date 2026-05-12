package com.cumpleanos.assist.service.implementation.recepcion_almacenes;

import com.cumpleanos.assist.persistence.inmutables.ComprobantesCcoRequest;
import com.cumpleanos.assist.persistence.repository.views.PendingInvoiceHeaderViewRepository;
import com.cumpleanos.assist.persistence.repository.views.PendingInvoiceProductDetailViewRepository;
import com.cumpleanos.core.models.views.FacRevprodWebV;
import com.cumpleanos.core.models.views.FacVerifiFacingWebV;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecepcionAlmacenesServiceImpl {

    private final PendingInvoiceProductDetailViewRepository detailViewRepository;
    private final PendingInvoiceHeaderViewRepository headerViewRepository;

    public List<FacVerifiFacingWebV> getComprobantes(){
        return headerViewRepository.findAllByOrderByFechaFacDesc();
    }

    public List<FacRevprodWebV> detalleProductoPendientes(BigInteger cco){
        return detailViewRepository.findByCcoCodigoOrderBySecuencia(cco);
    }

    public List<FacRevprodWebV> detalleProductoPendientesVariosComprobantes(ComprobantesCcoRequest request){
        return detailViewRepository.findByCcoCodigoIn(request.ccoCodigos());
    }
}
