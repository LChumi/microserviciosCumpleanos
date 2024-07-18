package com.cumlpeanos.pos.service;

import com.cumlpeanos.pos.models.api.DatosRecepcionResponse;
import com.cumlpeanos.pos.models.entity.ReciboPOSView;
import com.cumlpeanos.pos.repository.ReciboPOSViewRepositorio;
import com.cumlpeanos.pos.service.api.ApiConsumoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReciboPOSViewServiceImpl implements IReciboPOSViewService{

    private final ReciboPOSViewRepositorio repositorio;
    private final ApiConsumoService apiService;

    @Override
    public ReciboPOSView findByAlmacenAndPventa(int almacen, int pventa) {
        return repositorio.findByAlmacenAndPventa(almacen, pventa)
                .orElseThrow(() -> new RuntimeException("No se encontro datos en la vista Recibo POS "));
    }

    @Override
    public ReciboPOSView findByUsrLiquidaAndEmpresa(Long usrLiquida, Long empresa) {
        ReciboPOSView v= repositorio.findByUsrLiquidaAndEmpresa(usrLiquida,empresa)
                .orElseThrow(()-> new RuntimeException("No se encontro datos en la vista Recibo POS por UsrLiquida "));

        DatosRecepcionResponse response=apiService.procesarPago(v.getIp(),v.getPuertoCom());
        //update bd recibo_pos repositorio.update
        return null;
    }
}
