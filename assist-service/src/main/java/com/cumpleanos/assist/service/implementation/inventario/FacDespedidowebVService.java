package com.cumpleanos.assist.service.implementation.inventario;

import com.cumpleanos.assist.persistence.repository.views.FacDespedidowebVRepository;
import com.cumpleanos.core.models.views.FacDespedidowebV;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacDespedidowebVService {

    private final FacDespedidowebVRepository repository;

    public List<FacDespedidowebV> pedidosPendientes(String usuario, Integer estado) {
        if (usuario.equalsIgnoreCase("data_usr")) {
            return repository.findByEstadoOrderByUrgenteDescComprobanteAscHojaAsc(estado);
        } else {
            return repository.findByUsrIdLikeAndEstadoOrderByUrgenteDescComprobanteAscHojaAsc(usuario, estado);
        }
    }

}