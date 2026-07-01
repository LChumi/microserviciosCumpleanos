package com.cumpleanos.assist.service.implementation.inventario;

import com.cumpleanos.assist.persistence.repository.views.ProductoDespachoRepository;
import com.cumpleanos.core.models.views.FacDesprodWebV;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoDespachoService {

    private final ProductoDespachoRepository repository;

    public List<FacDesprodWebV> getProductosDespacho(Long empresa, BigInteger ccoCodigo, Long hoja){
        if (hoja == null) {
            return repository.findByEmpresaAndCcoCodigo(empresa, ccoCodigo);
        } else {
            return repository.findByEmpresaAndCcoCodigoAndHoja(empresa, ccoCodigo, hoja);
        }
    }

}