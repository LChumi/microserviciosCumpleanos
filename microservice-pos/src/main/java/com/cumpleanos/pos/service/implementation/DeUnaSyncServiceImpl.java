package com.cumpleanos.pos.service.implementation;

import com.cumpleanos.core.models.entities.Sistema;
import com.cumpleanos.core.models.exception.ApiResponse;
import com.cumpleanos.pos.persistence.api.deuna.payments.PaymentRequest;
import com.cumpleanos.pos.persistence.entity.ReciboPOSView;
import com.cumpleanos.pos.persistence.repository.ReciboPOSRepository;
import com.cumpleanos.pos.persistence.repository.ReciboPOSViewRepositorio;
import com.cumpleanos.pos.service.interfaces.IDeUnaSyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class DeUnaSyncServiceImpl implements IDeUnaSyncService {

    private final ReciboPOSRepository reciboPOSRepository;
    private final ReciboPOSViewRepositorio repositorio;
    private final ModelsClientServiceImpl modelsClientService;

    @Override
    public String procecarPago(Long usrLiquida, Long empresa) {
        return "";
    }

    private PaymentRequest crearPaymentRequest(ReciboPOSView v){
        ApiResponse<Sistema> empresa = modelsClientService.getEmpresa(v.getEmpresa());
        if (empresa.getData()==null){
            throw new RuntimeException("Error al obtener la empresa");
        }
        String detail = "Compra en "+ empresa.getData().getNombrecorto();
        String internalTransactioonReference = String.valueOf(v.getPventa()+ v.getCodigo());
        return new PaymentRequest(
                "4116225",
                "dynamic",
                v.getTotal().doubleValue(),
                detail,
                internalTransactioonReference,
                "2"
        );
    }
}
