package com.cumpleanos.pos.service.implementation;

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

    @Override
    public String procecarPago(Long usrLiquida, Long empresa) {
        return "";
    }

    private PaymentRequest crearPaymentRequest(ReciboPOSView v){
        return null;
    }
}
