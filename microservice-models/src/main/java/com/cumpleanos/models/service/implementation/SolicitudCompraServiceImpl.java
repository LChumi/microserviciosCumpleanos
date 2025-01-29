package com.cumpleanos.models.service.implementation;

import com.cumpleanos.core.models.dto.SolicitudComproImportacionDTO;
import com.cumpleanos.models.persistence.repository.AlmacenRepository;
import com.cumpleanos.models.persistence.repository.CcomprobaRepository;
import com.cumpleanos.models.persistence.repository.CtipocomRepository;
import com.cumpleanos.models.persistence.repository.DfacturaRepository;
import com.cumpleanos.models.service.interfaces.ISolicitudCompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class SolicitudCompraServiceImpl implements ISolicitudCompraService {

    private final DfacturaRepository dfacturaRepository;
    private final CcomprobaRepository ccoRepository;
    private final AlmacenRepository almacenRepository;
    private final CtipocomRepository ctipocomRepository;

    @Override
    public SolicitudComproImportacionDTO getSolicitudComproImportacion(BigInteger cco) {
        return null;
    }
}
