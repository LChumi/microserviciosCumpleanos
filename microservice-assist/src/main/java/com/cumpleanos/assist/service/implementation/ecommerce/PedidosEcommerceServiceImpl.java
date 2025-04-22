package com.cumpleanos.assist.service.implementation.ecommerce;

import com.cumpleanos.assist.persistence.dto.ProductoDTO;
import com.cumpleanos.assist.service.implementation.ClientServiceImpl;
import com.cumpleanos.assist.service.interfaces.IProductoService;
import com.cumpleanos.assist.service.interfaces.ecommerce.IPedidosEcommerceService;
import com.cumpleanos.common.builders.ecommerce.Ing;
import com.cumpleanos.common.builders.ecommerce.LineItem;
import com.cumpleanos.common.builders.ecommerce.PedidoWoocommerce;
import com.cumpleanos.common.records.AlmacenDTO;
import com.cumpleanos.common.records.BodegaDTO;
import com.cumpleanos.common.records.ClienteRecord;
import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.entities.Cliente;
import com.cumpleanos.core.models.entities.Creposicion;
import com.cumpleanos.core.models.entities.Dreposicion;
import com.cumpleanos.core.models.entities.Sistema;
import com.cumpleanos.core.models.enums.ParametroEnum;
import com.cumpleanos.core.models.ids.DreposicionId;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.cumpleanos.assist.utils.ClienteEcomUtil.*;
import static com.cumpleanos.assist.utils.PedidoEcommerceUtil.*;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PedidosEcommerceServiceImpl implements IPedidosEcommerceService {

    private final EcommerceClientServiceImpl ecommerceClient;
    private final ClientServiceImpl clienteService;
    private final IProductoService productoService;

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
            log.error("No se encontro los datos del cliente en el pedido");
            return;
        }
        Long cliId = findOrCreateCliente(cedRuc.trim(), pedido.getBilling());
        BodegaDTO bod = findBodegaSis();
        Long alm = findAlmacen(bod.getAlmacen(), bod.getEmpresa());
        Sistema sis = getSistema(bod.getEmpresa());

        Creposicion c = generarCabecera(pedido, sis, alm, bod.getId(), cliId);
        Creposicion creposicion = clienteService.saveCreposicion(c);
        if (creposicion == null) {
            log.error("No se pudo crear el registro de creposicion");
            return;
        }else {
            //Insertar Detalle de productos
            crearDetalles(pedido.getLine_items(), c, pedido.getCustomer_note());
        }
    }

    private Long findOrCreateCliente(String cedRuc, Ing shiping) {
        ClienteRecord cliente = clienteService.getByRucAndEmpresa(cedRuc,(short) 1, 2L);
        if (cliente == null) {
            log.info("CLiente no ecnontrado agregando {}....", cedRuc);
            Long tipClient= clienteService.verificarJuridico(cedRuc);
            Cliente cliEcom = createClienteEcommerce(cedRuc, shiping, 2L, tipClient);
            Long empresa = cliEcom.getId().getEmpresa();

            cliEcom.setCliId(generarIdCliente(cliEcom.getNombre(), cliEcom.getId().getEmpresa()));
            cliEcom.setCliCategoria(obtenerParametro(empresa, ParametroEnum.CXC_CATEGORIA_CLIENTE));
            cliEcom.setCliPolitica(obtenerParametro(empresa, ParametroEnum.CXC_POLITICA_CLIENTE));
            cliEcom.setCliCiudad(obtenerParametro(empresa, ParametroEnum.CXC_CIUDADES_CLIENTES));
            cliEcom.setTipoCli(obtenerParametro(empresa, ParametroEnum.CXC_TIPOCLI_ECOOMERCE_CLIENTES));
            cliEcom.setCliAgente(obtenerParametro(empresa, ParametroEnum.CXC_AGENTE_ECOMMERCE_CLIENTES));
            cliEcom.setCliListapre(obtenerParametro(empresa, ParametroEnum.CXC_LISTAPRE_CLIENTES));

            Cliente c =clienteService.saveCliente(cliEcom);
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

    private String generarIdCliente(String nombre, Long empresa){

        String nuevoIdBase = generarPrefix(nombre);

        //Lista de Ids existentes
        List<String> ids = clienteService.getIdsClientes(nuevoIdBase, empresa);

        if (ids.isEmpty()) {
            return nuevoIdBase+"001";
        } else {
            int maxNum =0;
            for (String id : ids) {
                String numStr = id.substring(nuevoIdBase.length());
                int num = Integer.parseInt(numStr);
                if (num > maxNum) {
                    maxNum = num;
                }
            }
            return nuevoIdBase+String.format("%03d", maxNum + 1);
        }
    }

    /**
     * Metodo para insertar los detalles con los valores de los productos;
     * @param items lista de items o productos desde WhooCommerce
     * @param c la cabecera creada anteriormente en la base de datos.
     * @param obs la observacion en caso de que exista.
     */
    private void crearDetalles(List<LineItem> items, Creposicion c, String obs) {
        Dreposicion dreposicion;
        DreposicionId id = new DreposicionId();

        id.setEmpresa(c.getId().getEmpresa());

        for (LineItem item : items) {
            ProductoDTO producto = productoService.getProductoByBarraAndEmpresa(item.getSku(), c.getId().getEmpresa());
            if (producto == null){
                log.error("Producto no existe en el sistema: {} ", item.getSku());
                return;
            } else {
                dreposicion = new Dreposicion();

                dreposicion.setId(id);
                dreposicion.setCreposicionId(c.getId().getCodigo());
                dreposicion.setProductoId(producto.codigo());
                dreposicion.setCantSol(item.getQuantity());
                dreposicion.setCantApr(item.getQuantity());
                dreposicion.setUsuario(USER);
                dreposicion.setPrecio(BigDecimal.valueOf(item.getPrice()));
                dreposicion.setTotal(BigDecimal.valueOf(item.getTotal()));
                extractDiscountAndObservation(dreposicion, item);
                clienteService.saveDreposicion(dreposicion);
            }

        }
        if (c.getTipoRetiro() == 1){
            ProductoDTO trans = productoService.getProductoByBarraAndEmpresa("TRANSIVA", c.getId().getEmpresa());
            dreposicion = new Dreposicion();

            dreposicion.setId(id);
            dreposicion.setCreposicionId(c.getId().getCodigo());
            dreposicion.setProductoId(trans.codigo());
            dreposicion.setCantSol(1L);
            dreposicion.setCantApr(1L);
            if (obs!=null){
                dreposicion.setObservacion(obs);
            }
            dreposicion.setUsuario(USER);
            dreposicion.setPrecio(c.getTransporte());
            dreposicion.setTotal(c.getTransporte());

            clienteService.saveDreposicion(dreposicion);
        }
    }

    /**
     * Metodo para extraer la observacion y el descuento en caso de que exista
     * @param dreposicion clase creada anteriormente
     * @param item item del pedido de Woocommerce.
     */
    private static void extractDiscountAndObservation(Dreposicion dreposicion, LineItem item) {
        if (item.getMeta_data() != null && !item.getMeta_data().isEmpty()) {
            for (Map<String, Object> metaMap : item.getMeta_data()) {
                String key = (String) metaMap.get("key");

                // Descuento
                if ("_discount".equals(key)) {
                    Map<String, Object> discountDetails = (Map<String, Object>) metaMap.get("value");
                    try {
                        double discountAmount = Double.parseDouble(discountDetails.get("amount").toString());
                        double discountPercentage = Double.parseDouble(discountDetails.get("percentage").toString());

                        log.info("Descuento aplicado: {}$", discountAmount);
                        log.info("Porcentaje de descuento: {}", discountPercentage + "%");

                        dreposicion.setPorcDesc(BigDecimal.valueOf(discountPercentage));
                        dreposicion.setValDesc(BigDecimal.valueOf(discountAmount));
                    } catch (Exception e) {
                        System.err.println("Error al leer el descuento: " + e.getMessage());
                    }
                }

                // Observación
                if ("observacion".equals(key)) {
                    try {
                        String observacion = metaMap.get("value").toString();
                        log.info("Observación: {}", observacion);
                        dreposicion.setObservacion(observacion);
                    } catch (Exception e) {
                        log.error("Error al leer la observación: {} " , e.getMessage());
                    }
                }
            }
        }
    }

}
