package com.cumpleanos.assist.service.implementation.ecommerce;

import com.cumpleanos.assist.service.implementation.ClientServiceImpl;
import com.cumpleanos.assist.service.interfaces.ecommerce.IPedidosEcommerceService;
import com.cumpleanos.common.builders.ecommerce.Ing;
import com.cumpleanos.common.builders.ecommerce.PedidoWoocommerce;
import com.cumpleanos.common.records.ClienteRecord;
import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.entities.Cliente;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.cumpleanos.assist.utils.ClienteEcomUtil.createClienteEcommerce;
import static com.cumpleanos.assist.utils.ClienteEcomUtil.getBillingDocument;

@Slf4j
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

    private void createCpedido(PedidoWoocommerce pedido) {
        String cedRuc = getBillingDocument(pedido.getMeta_data());
        if (cedRuc == null) {
            throw new IllegalArgumentException("No se encontro los datos del cliente en el pedido");
        }
        findOrCreateCliente(cedRuc.trim(), pedido.getBilling());
    }

    private void findOrCreateCliente(String cedRuc, Ing shiping) {
        ClienteRecord cliente = clienteService.getByRucAndEmpresa(cedRuc,(short) 1, 2L);
        if (cliente == null) {
            log.info("CLiente no ecnontrado agregando {}....", cedRuc);
            Long tipClient= clienteService.verificarJuridico(cedRuc);
            Cliente cliEcom = createClienteEcommerce(cedRuc, shiping, 2L, tipClient);
        }
    }

}
