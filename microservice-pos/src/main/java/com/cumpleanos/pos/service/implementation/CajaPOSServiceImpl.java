package com.cumlpeanos.pos.service.implementation;

import com.cumlpeanos.pos.persistence.entity.CajaPOS;
import com.cumlpeanos.pos.persistence.repository.CajaPOSRepository;
import com.cumlpeanos.pos.service.interfaces.ICajaPOSService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CajaPOSServiceImpl implements ICajaPOSService {

    private final CajaPOSRepository cajaPOSRepository;

    @Override
    public CajaPOS findByAlmacenAndPventa(Long almacen, Long pventa) {
        return cajaPOSRepository.findByAlmacenAndPventa(almacen, pventa)
                .orElseThrow(() -> new RuntimeException("CajaPOS no encontrada"));
    }
}
