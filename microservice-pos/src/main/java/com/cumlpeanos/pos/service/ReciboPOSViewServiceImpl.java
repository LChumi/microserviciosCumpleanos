package com.cumlpeanos.pos.service;

import com.cumlpeanos.pos.models.api.DatosEnvioRequest;
import com.cumlpeanos.pos.models.api.DatosRecepcionResponse;
import com.cumlpeanos.pos.models.entity.ReciboPOSView;
import com.cumlpeanos.pos.repository.ReciboPOSViewRepositorio;
import com.cumlpeanos.pos.service.api.ApiConsumoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

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

        DatosEnvioRequest dEnvio= new DatosEnvioRequest();
        dEnvio.setBaseImponible(v.getSubtotal().doubleValue());
        dEnvio.setBase0(v.getSubtotal0().doubleValue());
        dEnvio.setIva(v.getValImpuesto().doubleValue());

        DatosRecepcionResponse response=apiService.procesarPago(v.getIp(),v.getPuertoCom(),dEnvio);
        //update bd recibo_pos repositorio.update
        return null;
    }

    @Override
    public Map<String,String> listarPuertos(Long usrLiquida, Long empresa) {
        ReciboPOSView v= repositorio.findByUsrLiquidaAndEmpresa(usrLiquida,empresa)
                .orElseThrow(()-> new RuntimeException("No se encontro datos en la vista Recibo POS por UsrLiquida "));

        return apiService.listarPuertos(v.getIp());
    }
}
