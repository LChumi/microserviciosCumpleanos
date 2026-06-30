package com.cumpleanos.assist.service.implementation.inventario;

import com.cumpleanos.assist.persistence.repository.views.PedidoDespachoRepository;
import com.cumpleanos.core.models.views.FacDespedidowebV;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoDespachoService {

    private final PedidoDespachoRepository repository;

    public List<FacDespedidowebV> pedidosPendientes(String usuario, Integer estado){
        if (usuario.equalsIgnoreCase("data_usr")){
            return repository.findByEstadoOrderByComprobanteAscHojaAsc(estado);
        } else {
            return repository.findByUsrIdLikeAndEstadoOrderByComprobanteAscHojaAsc(usuario, estado);
        }
    }
    
}