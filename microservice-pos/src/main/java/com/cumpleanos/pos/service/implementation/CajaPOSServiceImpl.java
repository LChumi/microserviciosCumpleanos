package com.cumpleanos.pos.service.implementation;

import com.cumpleanos.pos.persistence.entity.CajaPOS;
import com.cumpleanos.pos.persistence.repository.CajaPOSRepository;
import com.cumpleanos.pos.service.interfaces.ICajaPOSService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CajaPOSServiceImpl implements ICajaPOSService {

    private final CajaPOSRepository cajaPOSRepository;

    @Override
    public CajaPOS findByAlmacenAndPventa(Long almacen, Long pventa) {
        return cajaPOSRepository.findByAlmacenAndPventa(almacen, pventa)
                .orElseThrow(() -> new RuntimeException("CajaPOS no encontrada"));
    }
}
