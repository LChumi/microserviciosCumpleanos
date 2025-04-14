package com.cumpleanos.assist.service.implementation.ecommerce;

import com.cumpleanos.assist.service.implementation.ClientServiceImpl;
import com.cumpleanos.assist.service.interfaces.ecommerce.IPedidosEcommerceService;
import com.cumpleanos.common.builders.ecommerce.PedidoWoocommerce;
import com.cumpleanos.common.builders.ecommerce.PedidosWoocommerceMetaDatum;
import com.cumpleanos.common.records.ClienteRecord;
import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.entities.Creposicion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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

    }

    private void findOrCreateCliente(String cedRuc, Long empresa) {
        ClienteRecord cliEcom = clienteService.getByRucAndEmpresa(cedRuc,(short) 1, empresa);
        if (cliEcom == null) {
            log.info("CLiente no ecnontrado agregando {}....", cedRuc);
        }
    }

    private static String generarPrefix(String nombre){
        return "ECOM-"+nombre.substring(0,3).toUpperCase().trim();
    }

    public static String getBillingDocument(PedidosWoocommerceMetaDatum[] metadata) {
        return Arrays.stream(metadata)
                .filter(meta -> "billing_document".equals(meta.getKey()))
                .map(PedidosWoocommerceMetaDatum::getValue)
                .findFirst()
                .orElse("Clave no encontrada");
    }
}
