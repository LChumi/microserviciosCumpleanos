package com.cumpleanos.assist.service.implementation.ecommerce;

import com.cumpleanos.assist.persistence.repository.functions.ProcedureOracleRepository;
import com.cumpleanos.assist.service.exception.ProcedureNotCompletedException;
import com.cumpleanos.common.dtos.BodegaDTO;
import com.cumpleanos.common.dtos.ProductoDTO;
import com.cumpleanos.assist.service.implementation.ClientServiceImpl;
import com.cumpleanos.assist.service.interfaces.ecommerce.IPedidosEcommerceService;
import com.cumpleanos.assist.utils.DiscountObs;
import com.cumpleanos.assist.utils.MailTemplateLoader;
import com.cumpleanos.common.builders.ecommerce.Ing;
import com.cumpleanos.common.builders.ecommerce.LineItem;
import com.cumpleanos.common.builders.ecommerce.PedidoWoocommerce;
import com.cumpleanos.common.records.*;
import com.cumpleanos.core.models.entities.*;
import com.cumpleanos.core.models.enums.ParametroEnum;
import com.cumpleanos.core.models.ids.DreposicionId;
import com.cumpleanos.core.models.ids.ReposicionPagoId;
import com.cumpleanos.core.models.ids.UbicacionId;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static com.cumpleanos.assist.utils.ClienteEcomUtil.*;
import static com.cumpleanos.assist.utils.PedidoEcommerceUtil.*;
import static com.cumpleanos.assist.utils.PedidoEcommerceUtil.StringToLocalDate;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PedidosEcommerceServiceImpl implements IPedidosEcommerceService {

    private final EcommerceClientServiceImpl ecommerceClient;
    private final ClientServiceImpl modelsService;
    private final ProcedureOracleRepository procedureRepository;

    @Override
    public ServiceResponse getPedidosAndUpdateSystem() {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);

        log.info("Sincronizando los pedidos del Ecommerce con las siguientes fechas: fecha inicio: {} , fecha fin: {}", today, yesterday);

        List<PedidoWoocommerce> pedidosWoo = ecommerceClient.getOrdesrByDate(yesterday, today);
        if (pedidosWoo == null || pedidosWoo.isEmpty()) {
            return new ServiceResponse("No se encontraron pedidos en WhooCommerce", Boolean.TRUE);
        } else {
            String response = validatePedido(pedidosWoo);
            return new ServiceResponse(response, Boolean.TRUE);
        }
    }

    private String validatePedido(List<PedidoWoocommerce> pedidosWoo) {
        int add = 0;
        int count = 0;
        for (PedidoWoocommerce pedido : pedidosWoo) {
            Boolean exist = modelsService.findCreposicionByReferencia(String.valueOf(pedido.getId()), 2L);
            if (exist) {
                count++;
                log.info("Pedido ya registrado omitiendo: {}", pedido.getId());
            } else {
                log.info("Pedido no existe agregando al sistema idPedido: {} ...", pedido.getId());
                createCpedido(pedido);
                add++;
            }
        }

        if (add != 0) {
            enviarMailConfirmacion(add);
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
        Creposicion c = modelsService.saveCreposicion(cabecera);
        if (c == null) {
            log.error("No se pudo crear el registro de creposicion");
            throw new EntityNotFoundException("No se pudo crear el registro de creposicion");
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
        finalizarPedido(c);
    }

    private Long findOrCreateCliente(String cedRuc, Ing shiping) {
        ClienteRecord cliente = modelsService.getByRucAndEmpresa(cedRuc, (short) 1, 2L);
        if (cliente == null) {
            log.info("CLiente no ecnontrado agregando {}....", cedRuc);
            Long tipClient = modelsService.verificarJuridico(cedRuc);
            Cliente cliEcom = createClienteEcommerce(cedRuc, shiping, 2L, tipClient);
            Long empresa = cliEcom.getId().getEmpresa();

            cliEcom.setCliId(generarIdCliente(cliEcom.getNombre(), cliEcom.getId().getEmpresa()));
            cliEcom.setCliCategoria(obtenerParametro(empresa, ParametroEnum.CXC_CATEGORIA_CLIENTE));
            cliEcom.setCliPolitica(obtenerParametro(empresa, ParametroEnum.CXC_POLITICA_CLIENTE));
            cliEcom.setTipoCli(obtenerParametro(empresa, ParametroEnum.CXC_TIPOCLI_ECOOMERCE_CLIENTES));
            cliEcom.setCliAgente(obtenerParametro(empresa, ParametroEnum.CXC_AGENTE_ECOMMERCE_CLIENTES));
            cliEcom.setCliListapre(obtenerParametro(empresa, ParametroEnum.CXC_LISTAPRE_CLIENTES));

            addedCity(cliEcom, shiping.getCity(), empresa);

            Cliente c = modelsService.saveCliente(cliEcom);
            return c.getId().getCodigo();
        }
        return cliente.codigo();
    }

    private void addedCity(Cliente c, String city, Long empresa) {
        Long ciudad = Optional.ofNullable(city)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(cy -> modelsService.getUbicaion(empresa, cy))
                .filter(list -> !list.isEmpty())
                .map(list -> list.get(0))
                .map(Ubicacion::getId)
                .map(UbicacionId::getCodigo)
                .orElseGet(() -> obtenerParametro(empresa, ParametroEnum.CXC_CIUDADES_CLIENTES));

        c.setCliCiudad(ciudad);
    }

    private BodegaDTO findBodegaSis() {
        BodegaDTO bodega = modelsService.getBodegaDTO(2L);
        if (bodega == null) {
            throw new EntityNotFoundException("Bodega no encontrada");
        }
        return bodega;
    }

    private Long findAlmacen(Long codigo, Long empresa) {
        AlmacenDTO almacen = modelsService.getAlmacenDTO(codigo, empresa);
        if (almacen == null) {
            throw new EntityNotFoundException("Almacén no encontrada en la empresa: " + empresa);
        }
        return almacen.codigo();
    }

    private Sistema getSistema(Long empresa) {
        Sistema sis = modelsService.getEmpresa(empresa);
        if (sis == null) {
            throw new EntityNotFoundException("Empresa no encontrada");
        }
        return sis;
    }

    private Long obtenerParametro(Long empresa, ParametroEnum parametro) {
        return modelsService.verificarParametro(
                empresa,
                parametro.getSigla(),
                parametro.getSecuencia(),
                2
        );
    }

    private String generarIdCliente(String nombre, Long empresa) {

        String nuevoIdBase = generarPrefix(nombre);

        //Lista de Ids existentes
        List<String> ids = modelsService.getIdsClientes(nuevoIdBase, empresa);

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
        final DreposicionId id = new DreposicionId();
        id.setEmpresa(c.getId().getEmpresa());

        List<String> errores = new ArrayList<>();

        for (LineItem item : items) {

            /* parsear meta_data */
            DiscountObs meta = DiscountObs.of(item.getMeta_data());

            /* obtener el producto por sku */
            ProductoDTO producto = modelsService.getProductoByBarraAndEmpresa(item.getSku(), c.getId().getEmpresa());
            if (producto == null) {
                errores.add("Producto no existe en el sistema: " + item.getSku());
                continue;
            }

            Dreposicion detalle = crearDreposicion(id, c, producto, item.getQuantity(), BigDecimal.valueOf(item.getPrice()), BigDecimal.valueOf(item.getTotal()), meta.observacion());

            if (meta.hasDiscount()) {
                detalle.setPorcDesc(meta.porcDesc());
                detalle.setValDesc(meta.valDesc());
                modelsService.saveDreposicion(detalle);
            }

            count++;
        }
        if (c.getTipoRetiro() == 1) {
            ProductoDTO trans = modelsService.getProductoByBarraAndEmpresa("TRANSIVA", c.getId().getEmpresa());
            crearDreposicion(id, c, trans, 1L, c.getTransporte(), c.getTransporte(), obs);
            count++;
        }
        errores.forEach(log::error);
        return count;
    }

    private Dreposicion crearDreposicion(DreposicionId id, Creposicion c, ProductoDTO producto, Long cantidad, BigDecimal precio, BigDecimal total, String observacion) {

        final Dreposicion dreposicion = new Dreposicion();
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

        return modelsService.saveDreposicion(dreposicion);
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

        return modelsService.saveReposicionPago(pago);
    }

    private void finalizarPedido(Creposicion c) {
        try {
            log.info("Iniciando proceso de finalizacion de pedido");
            Map<String, Long> response = procedureRepository.generaUsrLiquidaWeb(c.getId().getEmpresa(), c.getId().getCodigo(), 0L);
            Long usrLiquida = response.get("usrLiquida");
            Long error = response.get("error");
            if (error != null && error == 1L) {
                throw new ProcedureNotCompletedException("Se econtro un error al generar la UsrLiquida");
            }

            ServiceResponse creposicionResponse = modelsService.finalizarPedido(c.getId().getEmpresa(), c.getId().getCodigo(), usrLiquida, 1);
            if (!creposicionResponse.success()) {
                throw new EntityNotFoundException("No se pudo Actualizar el pedido en la base de datos ");
            }

            String pedidoGenerado = procedureRepository.generarReposicion(c.getId().getEmpresa(), c.getBodegaId(), c.getAlmacenId(), usrLiquida, "WEB_USR");
            if (pedidoGenerado == null) {
                throw new ProcedureNotCompletedException("No se pudo finalizar el pedido en el proceso generar Reposicion ");
            }

            log.info("Pedido finalizado : {}", pedidoGenerado);
        } catch (Exception e) {
            ServiceResponse creposicionResponse = modelsService.finalizarPedido(c.getId().getEmpresa(), c.getId().getCodigo(), null, 0);
            log.error("Error al finalizar el pedido en empresa: {} codigo:{} ", c.getId().getEmpresa(), c.getId().getCodigo(), e);
        }
    }

    private void enviarMailConfirmacion(int cantidadPedidos) {

        String asunto = "Pedidos Ecommerce";

        Map<String, String> variables = new HashMap<>();
        variables.put("cantidadPedidos", String.valueOf(cantidadPedidos));

        String mensaje = MailTemplateLoader.loadAndFillTemplate("pedidos-ecommerce.html", variables);

        try {
            EmailRecord email = new EmailRecord(
                    new String[]{"amunoz@cumpleanos.com.ec", "iullauri@cumpleanos.com.ec"},
                    asunto,
                    mensaje
            );
            modelsService.enviarEmail(email);
        } catch (Exception e) {
            throw new MailSendException("No se pudo enviar el correo al usuario ");
        }
    }

}
