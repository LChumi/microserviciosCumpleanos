package com.cumpleanos.assist.service.implementation.ecommerce;

import com.cumpleanos.assist.service.implementation.ClientServiceImpl;
import com.cumpleanos.assist.service.interfaces.ecommerce.IPedidosEcommerceService;
import com.cumpleanos.common.builders.ecommerce.PedidoWoocommerce;
import com.cumpleanos.common.records.ServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PedidosEcommerceServiceImpl implements IPedidosEcommerceService {

    private final EcommerceClientServiceImpl ecommerceClient;
    private final ClientServiceImpl clienteService;

    @Override
    public ServiceResponse getPedidosAndUpdateSystem() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
        List<PedidoWoocommerce> pedidosWoo = ecommerceClient.getOrdesrByDate(today, yesterday);
        return null;
    }

    private void createCpedido(){

    }
}
