package com.cumpleanos.assist.service.implementation.ecommerce;

import com.cumpleanos.assist.service.implementation.ClientServiceImpl;
import com.cumpleanos.assist.service.interfaces.ecommerce.IPedidosEcommerceService;
import com.cumpleanos.common.builders.ecommerce.Ing;
import com.cumpleanos.common.builders.ecommerce.PedidoWoocommerce;
import com.cumpleanos.common.records.AlmacenDTO;
import com.cumpleanos.common.records.BodegaDTO;
import com.cumpleanos.common.records.ClienteRecord;
import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.entities.Cliente;
import com.cumpleanos.core.models.entities.Creposicion;
import com.cumpleanos.core.models.entities.Sistema;
import com.cumpleanos.core.models.enums.ParametroEnum;
import com.cumpleanos.core.models.ids.CreposicionId;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.cumpleanos.assist.utils.ClienteEcomUtil.*;

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
        Long cliId = findOrCreateCliente(cedRuc.trim(), pedido.getBilling());
        BodegaDTO bod = findBodegaSis();
        Long alm = findAlmacen(bod.getAlmacen(), bod.getEmpresa());
        Sistema sis = getSistema(bod.getEmpresa());

        Creposicion creposicion = createCreposicion(pedido, sis, alm, bod.getId(), cliId);
    }

    private Long findOrCreateCliente(String cedRuc, Ing shiping) {
        ClienteRecord cliente = clienteService.getByRucAndEmpresa(cedRuc,(short) 1, 2L);
        if (cliente == null) {
            log.info("CLiente no ecnontrado agregando {}....", cedRuc);
            Long tipClient= clienteService.verificarJuridico(cedRuc);
            Cliente cliEcom = createClienteEcommerce(cedRuc, shiping, 2L, tipClient);
            Long empresa = cliEcom.getId().getEmpresa();
            cliEcom.setCliCategoria(obtenerParametro(empresa, ParametroEnum.CXC_CATEGORIA_CLIENTE));
            cliEcom.setCliPolitica(obtenerParametro(empresa, ParametroEnum.CXC_POLITICA_CLIENTE));
            cliEcom.setCliCiudad(obtenerParametro(empresa, ParametroEnum.CXC_CIUDADES_CLIENTES));
            cliEcom.setTipoCli(obtenerParametro(empresa, ParametroEnum.CXC_TIPOCLI_ECOOMERCE_CLIENTES));
            cliEcom.setCliAgente(obtenerParametro(empresa, ParametroEnum.CXC_AGENTE_ECOMMERCE_CLIENTES));
            cliEcom.setCliListapre(obtenerParametro(empresa, ParametroEnum.CXC_LISTAPRE_CLIENTES));

            Cliente c =clienteService.save(cliEcom);
            return c.getId().getCodigo();
        }
        return cliente.codigo();
    }

    private BodegaDTO findBodegaSis(){
        BodegaDTO bodega = clienteService.getBodegaDTO(2L);
        if (bodega == null) {
            throw new EntityNotFoundException("Bodega no encontrada" );
        }
        return bodega;
    }

    private Long findAlmacen(Long codigo, Long empresa){
        AlmacenDTO almacen = clienteService.getAlmacenDTO(codigo, empresa);
        if (almacen == null) {
            throw new EntityNotFoundException("Almacén no encontrada en la empresa: " + empresa);
        }
        return almacen.codigo();
    }

    private Sistema getSistema(Long empresa) {
        Sistema sis = clienteService.getEmpresa(empresa);
        if (sis == null) {
            throw new EntityNotFoundException("Empresa no encontrada");
        }
        return sis;
    }

    private Long obtenerParametro(Long empresa, ParametroEnum parametro) {
        return clienteService.verificarParametro(
                empresa,
                parametro.getSigla(),
                parametro.getSecuencia(),
                2
        );
    }

}
