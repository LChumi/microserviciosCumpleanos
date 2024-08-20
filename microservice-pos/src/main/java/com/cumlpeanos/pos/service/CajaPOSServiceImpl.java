package com.cumlpeanos.pos.service;

import com.cumlpeanos.pos.models.entity.CajaPOS;
import com.cumlpeanos.pos.repository.CajaPOSRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CajaPOSServiceImpl implements ICajaPOSService {

    private final CajaPOSRepository cajaPOSRepository;

    @Override
    public CajaPOS findByAlmacenAndPventa(int almacen, int pventa) {
        return cajaPOSRepository.findByAlmacenAndPventa(almacen, pventa)
                .orElseThrow(() -> new RuntimeException("CajaPOS no encontrada"));
    }
}
