package com.cumpleanos.pos.service.implementation;

import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.pos.persistence.api.jep.JepResponseQr;
import com.cumpleanos.pos.persistence.repository.ReciboPOSRepository;
import com.cumpleanos.pos.persistence.repository.ReciboPOSViewRepositorio;
import com.cumpleanos.pos.service.interfaces.IJepFasterSyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class JepFasterSyncServiceImpl implements IJepFasterSyncService {

    private final ReciboPOSRepository reciboPOSRepository;
    private final ReciboPOSViewRepositorio viewRepositorio;
    private final ModelsClientServiceImpl modelsClientService;
    private final JepFasterClientService jepFasterClientService;

    @Override
    public JepResponseQr generarQR(Long usrLiquida, Long empresa) {
        return null;
    }

    @Override
    public ServiceResponse validarPago(Long usrLiquida, Long empresa) {
        return null;
    }

    @Override
    public ServiceResponse procesarPago(Long usrLiquida, Long empresa) {
        return null;
    }
}
