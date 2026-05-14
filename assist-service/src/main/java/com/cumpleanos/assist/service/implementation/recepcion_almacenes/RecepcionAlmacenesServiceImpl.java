package com.cumpleanos.assist.service.implementation.recepcion_almacenes;

import com.cumpleanos.assist.persistence.inmutables.ComprobantesCcoRequest;
import com.cumpleanos.assist.persistence.repository.views.PendingInvoiceHeaderViewRepository;
import com.cumpleanos.assist.persistence.repository.views.PendingInvoiceProductDetailViewRepository;
import com.cumpleanos.assist.service.implementation.ClientServiceImpl;
import com.cumpleanos.common.dtos.BodegaDTO;
import com.cumpleanos.common.records.AlmacenDTO;
import com.cumpleanos.core.models.entities.Creposicion;
import com.cumpleanos.core.models.entities.Dreposicion;
import com.cumpleanos.core.models.views.FacRevprodWebV;
import com.cumpleanos.core.models.views.FacVerifiFacingWebV;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

import static com.cumpleanos.assist.utils.ReposicionAlmacenUtil.generarCabeceraRevision;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecepcionAlmacenesServiceImpl {

    private final PendingInvoiceProductDetailViewRepository detailViewRepository;
    private final ClientServiceImpl modelsService;
    private final PendingInvoiceHeaderViewRepository headerViewRepository;

    public List<FacVerifiFacingWebV> getComprobantes() {
        return headerViewRepository.findAllByOrderByFechaFacDesc();
    }

    public List<FacVerifiFacingWebV> getComprobantesByEmpresa(Long empresa) {
        return headerViewRepository.findByEmpresaCompraOrderByFechaFacDesc(empresa);
    }

    public List<FacVerifiFacingWebV> getComprobantesByBodega(Long bodCodigo){
        return headerViewRepository.findByBodCodigo(bodCodigo);
    }

    //PRODUCTO DETALLE

    public List<FacRevprodWebV> detalleProductoPendientes(BigInteger cco) {
        return detailViewRepository.findByCcoCodigoOrderBySecuencia(cco);
    }

    public List<FacRevprodWebV> detalleProductoPendientesVariosComprobantes(ComprobantesCcoRequest request) {
        return detailViewRepository.findByCcoCodigoIn(request.ccoCodigos());
    }

    private void createRecepcionUpdateCco (ComprobantesCcoRequest r, List<FacRevprodWebV> list){

        BodegaDTO bod = modelsService.getBodega(r.empresa(), r.bodega());
        AlmacenDTO alm = modelsService.getAlmacenDTO(bod.getAlmacen(), r.empresa());

        Creposicion creposicion = generarCabeceraRevision(r.empresa(), r.usuario(), alm.codigo(), bod.getId());

        Creposicion c = modelsService.saveCreposicion(creposicion);
        if (c == null) {
            log.error("No se pudo crear el registro de revision mercaderia");
            throw new EntityNotFoundException("No se pudo crear el registro Revision mercaderia");
        }
    }

    private Dreposicion crearDetalle(){
        final Dreposicion d = new Dreposicion();

    }

}