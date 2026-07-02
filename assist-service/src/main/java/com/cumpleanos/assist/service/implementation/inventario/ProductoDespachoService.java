package com.cumpleanos.assist.service.implementation.inventario;

import com.cumpleanos.assist.persistence.repository.views.ProductoDespachoRepository;
import com.cumpleanos.assist.service.implementation.ClientServiceImpl;
import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.views.FacDesprodWebV;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoDespachoService {

    private final ProductoDespachoRepository repository;
    private final ClientServiceImpl clientService;

    public List<FacDesprodWebV> getProductosDespacho(Long empresa, BigInteger ccoCodigo, Long hoja){
        if (hoja == null) {
            return repository.findByEmpresaAndCcoCodigo(empresa, ccoCodigo);
        } else {
            return repository.findByEmpresaAndCcoCodigoAndHoja(empresa, ccoCodigo, hoja);
        }
    }

    public ServiceResponse actualizarCantidad(FacDesprodWebV p){

        if (p.getCanapr().compareTo(BigDecimal.ZERO) >= 0) {
            if (p.getCanapr().compareTo(BigDecimal.valueOf(p.getStock())) > 0) {
                return new ServiceResponse("Cantidad no puede ser mayor al stock", false);
            } else {
                ServiceResponse response = null;
                if (p.getTipodoc() == 26){
                    response = clientService.addedCanAprDespacho(p.getCcoCodigo(),p.getProCodigo(),p.getCanapr().intValue());
                } else if (p.getTipodoc() == 113){
                    response = clientService.addedCanAprDespachoDmovinv(p.getCcoCodigo(),p.getProCodigo(),p.getCanapr().intValue());
                }
                return response;
            }
        } else{
            return new ServiceResponse("Cantidad debe ser mayor o igual a 0", false);
        }

    }

}