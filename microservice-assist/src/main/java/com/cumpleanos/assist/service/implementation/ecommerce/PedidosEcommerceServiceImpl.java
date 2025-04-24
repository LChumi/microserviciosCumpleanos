package com.cumpleanos.assist.service.implementation.ecommerce;

import com.cumpleanos.assist.persistence.dto.ProductoDTO;
import com.cumpleanos.assist.service.implementation.ClientServiceImpl;
import com.cumpleanos.assist.service.interfaces.IProductoService;
import com.cumpleanos.assist.service.interfaces.ecommerce.IPedidosEcommerceService;
import com.cumpleanos.assist.utils.DiscountObs;
import com.cumpleanos.common.builders.ecommerce.Ing;
import com.cumpleanos.common.builders.ecommerce.LineItem;
import com.cumpleanos.common.builders.ecommerce.PedidoWoocommerce;
import com.cumpleanos.common.records.AlmacenDTO;
import com.cumpleanos.common.records.BodegaDTO;
import com.cumpleanos.common.records.ClienteRecord;
import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.entities.*;
import com.cumpleanos.core.models.enums.ParametroEnum;
import com.cumpleanos.core.models.ids.DreposicionId;
import com.cumpleanos.core.models.ids.ReposicionPagoId;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.cumpleanos.assist.utils.ClienteEcomUtil.*;
import static com.cumpleanos.assist.utils.PedidoEcommerceUtil.*;
import static com.cumpleanos.assist.utils.PedidoEcommerceUtil.StringToLocalDate;

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

        LocalDate prueba = StringToLocalDate("2025-02-03T00:00:00");
        List<PedidoWoocommerce> pedidosWoo = ecommerceClient.getOrdesrByDate(prueba, prueba);
        if (pedidosWoo == null || pedidosWoo.isEmpty()) {
            return new ServiceResponse("No se encontraron pedidos en WhooCommerce", Boolean.TRUE);
        }else{
            String response =validatePedido(pedidosWoo);
            return new ServiceResponse(response, Boolean.TRUE);
        }
    }

    private String validatePedido(List<PedidoWoocommerce> pedidosWoo ){
        int add =0;
        int count =0;
        for (PedidoWoocommerce pedido : pedidosWoo) {
            Boolean exist = clienteService.findCreposicionByReferencia(String.valueOf(pedido.getId()), 2L);
            if (exist){
                count++;
                log.info("Pedido ya registrado omitiendo: {}", pedido.getId());
                continue;
            } else {
                log.info("Pedido no existe agregando al sistema idPedido: {} ...", pedido.getId());
                createCpedido(pedido);
                add++;
            }
        }
        return "Pedidos totales:" + pedidosWoo.size() + " agregados al sistema:" + add + " existentes:" + count;
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

        Creposicion cabecera = generarCabecera(pedido, sis, alm, bod.getId(), cliId);
        Creposicion c = clienteService.saveCreposicion(cabecera);
        if (c == null) {
            log.error("No se pudo crear el registro de creposicion");
            return;
        } else {
            //Insertar Detalle de productos
            int detalles = crearDetalles(pedido.getLine_items(), c, pedido.getCustomer_note());
            if (detalles == 0) {
                log.warn("Lista de detalles vacia");
            } else {
                ReposicionPago pagoCreado = crearFormaPago(c, pedido);
                log.debug("Forma de pago registrada: {}", pagoCreado.getId());
            }
        }
    }

    private Long findOrCreateCliente(String cedRuc, Ing shiping) {
        ClienteRecord cliente = clienteService.getByRucAndEmpresa(cedRuc, (short) 1, 2L);
        if (cliente == null) {
            log.info("CLiente no ecnontrado agregando {}....", cedRuc);
            Long tipClient = clienteService.verificarJuridico(cedRuc);
            Cliente cliEcom = createClienteEcommerce(cedRuc, shiping, 2L, tipClient);
            Long empresa = cliEcom.getId().getEmpresa();

            cliEcom.setCliId(generarIdCliente(cliEcom.getNombre(), cliEcom.getId().getEmpresa()));
            cliEcom.setCliCategoria(obtenerParametro(empresa, ParametroEnum.CXC_CATEGORIA_CLIENTE));
            cliEcom.setCliPolitica(obtenerParametro(empresa, ParametroEnum.CXC_POLITICA_CLIENTE));
            cliEcom.setCliCiudad(obtenerParametro(empresa, ParametroEnum.CXC_CIUDADES_CLIENTES));
            cliEcom.setTipoCli(obtenerParametro(empresa, ParametroEnum.CXC_TIPOCLI_ECOOMERCE_CLIENTES));
            cliEcom.setCliAgente(obtenerParametro(empresa, ParametroEnum.CXC_AGENTE_ECOMMERCE_CLIENTES));
            cliEcom.setCliListapre(obtenerParametro(empresa, ParametroEnum.CXC_LISTAPRE_CLIENTES));

            Cliente c = clienteService.saveCliente(cliEcom);
            return c.getId().getCodigo();
        }
        return cliente.codigo();
    }

    private BodegaDTO findBodegaSis() {
        BodegaDTO bodega = clienteService.getBodegaDTO(2L);
        if (bodega == null) {
            throw new EntityNotFoundException("Bodega no encontrada");
        }
        return bodega;
    }

    private Long findAlmacen(Long codigo, Long empresa) {
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

    private String generarIdCliente(String nombre, Long empresa) {

        String nuevoIdBase = generarPrefix(nombre);

        //Lista de Ids existentes
        List<String> ids = clienteService.getIdsClientes(nuevoIdBase, empresa);

        if (ids.isEmpty()) {
            return nuevoIdBase + "001";
        } else {
            int maxNum = 0;
            for (String id : ids) {
                String numStr = id.substring(nuevoIdBase.length());
                int num = Integer.parseInt(numStr);
                if (num > maxNum) {
                    maxNum = num;
                }
            }
            return nuevoIdBase + String.format("%03d", maxNum + 1);
        }
    }

    /**
     * Metodo para insertar los detalles con los valores de los productos;
     *
     * @param items lista de items o productos desde WhooCommerce
     * @param c     la cabecera creada anteriormente en la base de datos.
     * @param obs   la observacion en caso de que exista.
     */
    private int crearDetalles(List<LineItem> items, Creposicion c, String obs) {

        int count = 0;
        DreposicionId id = new DreposicionId();
        id.setEmpresa(c.getId().getEmpresa());

        List<String> errores = new ArrayList<>();

        for (LineItem item : items) {

            /* parsear meta_data */
            DiscountObs meta = DiscountObs.of(item.getMeta_data());

            /* obtener el producto por sku */
            ProductoDTO producto = productoService.getProductoByBarraAndEmpresa(item.getSku(), c.getId().getEmpresa());
            if (producto == null) {
                errores.add("Producto no existe en el sistema: " + item.getSku());
                continue;
            }

            Dreposicion detalle = crearDreposicion(id, c, producto, item.getQuantity(), BigDecimal.valueOf(item.getPrice()), BigDecimal.valueOf(item.getTotal()), meta.observacion());

            if (meta.hasDiscount()) {
                detalle.setPorcDesc(meta.porcDesc());
                detalle.setValDesc(meta.valDesc());
                clienteService.saveDreposicion(detalle);
            }

            count++;
        }
        if (c.getTipoRetiro() == 1) {
            ProductoDTO trans = productoService.getProductoByBarraAndEmpresa("TRANSIVA", c.getId().getEmpresa());
            crearDreposicion(id, c, trans, 1L, c.getTransporte(), c.getTransporte(), obs);
            count++;
        }
        errores.forEach(log::error);
        return count;
    }

    private Dreposicion crearDreposicion(DreposicionId id, Creposicion c, ProductoDTO producto, Long cantidad, BigDecimal precio, BigDecimal total, String observacion) {
        Dreposicion dreposicion = new Dreposicion();
        dreposicion.setId(id);
        dreposicion.setCreposicionId(c.getId().getCodigo());
        dreposicion.setProductoId(producto.codigo());
        dreposicion.setCantSol(cantidad);
        dreposicion.setCantApr(cantidad);
        dreposicion.setUsuario(USER);
        dreposicion.setPrecio(precio);
        dreposicion.setTotal(total);

        if (observacion != null) {
            dreposicion.setObservacion(observacion);
        }

        return clienteService.saveDreposicion(dreposicion);
    }

    private ReposicionPago crearFormaPago(Creposicion c, PedidoWoocommerce p) {

        final ReposicionPagoId id = new ReposicionPagoId();
        id.setEmpresa(c.getId().getEmpresa());

        final ReposicionPago pago = new ReposicionPago();
        pago.setId(id);
        pago.setCreposicionId(c.getId().getCodigo());
        pago.setFecha(StringToLocalDate(p.getDate_created()));
        pago.setMonto(safeParseBigDecimal(p.getTotal()));
        pago.setTipopago(getTipoPago(p.getPayment_method()));
        pago.setNroDocum(String.valueOf(p.getId()));
        pago.setLote(formatDate(pago.getFecha()));

        return clienteService.saveReposicionPago(pago);
    }

}
