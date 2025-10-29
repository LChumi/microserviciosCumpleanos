package com.cumpleanos.assist.utils;

import com.cumpleanos.common.builders.ecommerce.PedidoWoocommerce;
import com.cumpleanos.common.builders.ecommerce.ShippingLine;
import com.cumpleanos.common.builders.ecommerce.TaxLine;
import com.cumpleanos.core.models.entities.Creposicion;
import com.cumpleanos.core.models.entities.Sistema;
import com.cumpleanos.core.models.ids.CreposicionId;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.cumpleanos.core.models.enums.CreposicionTiposEnum.*;
import static com.cumpleanos.core.models.enums.CreposicionTiposEnum.ALM;
import static com.cumpleanos.core.models.enums.CreposicionTiposEnum.DOM;

public class PedidoEcommerceUtil {

    public static final String USER = "WEB_USR";
    private static final String OBS = "PEDIDO GENERADO DESDE E-COMMERCE ";
    private static final String METODO_RETIRO = "local_pickup";

    //TODO cabecera Creposicion

    /**
     * Metodo para generar la cabecera en BD
     *
     * @param pedido -> datos del pedido recibido desde WooCommerce
     * @param sis    -> empresa donde se va a registrar la informacion
     * @param alm    codigo del Almacen
     * @param bod    codigo de Bodega
     * @param cliId  codigo del cliente
     * @return creposicion cabecera con la informacion del pedido
     */
    public static Creposicion generarCabecera(PedidoWoocommerce pedido, Sistema sis, Long alm, Long bod, Long cliId) {
        String address1 = pedido.getShipping().getAddress_1() != null ? pedido.getShipping().getAddress_1().trim().toUpperCase() : "";
        String address2 = pedido.getShipping().getAddress_2() != null ? pedido.getShipping().getAddress_2().trim().toUpperCase() : "";
        String referencia = pedido.getCustomer_note() != null ? pedido.getCustomer_note().trim().toUpperCase() : "";

        Creposicion creposicion = new Creposicion();
        CreposicionId id = new CreposicionId();

        id.setEmpresa(2L);

        creposicion.setId(id);
        creposicion.setUsuario(USER);
        creposicion.setObservacion(OBS + pedido.getId() + " " + referencia.trim().toUpperCase());
        creposicion.setFecha(LocalDate.now());
        creposicion.setEstado(ESTADO_PROCESO.getCodigo());
        creposicion.setFinalizado(NO_FINALIZADO.getCodigo());
        creposicion.setAlmacenId(alm);
        creposicion.setBodegaId(bod);
        creposicion.setTipo(TIPO_ECOM.getCodigo());
        creposicion.setEmpresaGrupo(sis.getEmpresaGrupo());
        creposicion.setReferencia(String.valueOf(pedido.getId()));

        //Detalle envió shipping
        creposicion.setCliDirEntrega(address1);
        creposicion.setReferenciaEntrega(address2);
        creposicion.setCodigoPostal(pedido.getShipping().getPostcode());

        //Detalle entrega
        creposicion.setTipoRetiro(getTipoRetiro(pedido.getShipping_lines()));

        //Cliente
        creposicion.setClienteId(cliId);

        createTaxAndShipingLine(creposicion, pedido);

        return creposicion;
    }

    /**
     * Metodo para agregar la información de Transporte y la información de impuesto
     *
     * @param creposicion Objeto que se crea campos vacios.
     * @param pedido      Objeto con la información del pedido recibida desde WooCommerce
     */
    private static void createTaxAndShipingLine(Creposicion creposicion, PedidoWoocommerce pedido) {

        BigDecimal total = safeParseBigDecimal(pedido.getTotal());
        BigDecimal iva = safeParseBigDecimal(pedido.getTotal_tax());

        creposicion.setTotal(total);

        BigDecimal subtotal = total.subtract(iva);

        //Forma de pago
        creposicion.setSubtotal(subtotal);
        creposicion.setDescuento(safeParseBigDecimal(pedido.getDiscount_total()));

        //Línea de impuesto IVA
        List<TaxLine> taxLine = pedido.getTax_lines();
        //Línea de envío
        List<ShippingLine> shipping = pedido.getShipping_lines();

        if (taxLine != null && !taxLine.isEmpty()) {
            TaxLine impuesto = taxLine.get(0);

            //Impuesto IVA 15/12
            creposicion.setPorcimpuesto(impuesto.getRate_percent());
            //Valor del impuesto aplicado al total del pedido
            creposicion.setValImpuesto(safeParseBigDecimal(impuesto.getTax_total()));
        }
        if (shipping != null && !shipping.isEmpty()) {
            //Valor de Transporte
            ShippingLine envio = shipping.get(0);
            creposicion.setTransporte(safeParseBigDecimal(envio.getTotal()));
        }

    }

    /**
     * Determina el tipo de retiro basado en la información de envío.
     *
     * @param shipping Lista de líneas de envío (debe contener al menos un elemento).
     * @return Código correspondiente al tipo de retiro (ALM o DOM).
     * @throws IllegalArgumentException si la lista de envíos está vacía o nula.
     */
    private static Integer getTipoRetiro(List<ShippingLine> shipping) {
        if (shipping == null || shipping.isEmpty()) {
            throw new IllegalArgumentException("La lista de envíos no puede estar vacía ni nula.");
        }
        ShippingLine envio = shipping.get(0);
        return envio != null && envio.getMethod_id().equalsIgnoreCase(METODO_RETIRO)
                ? ALM.getCodigo()
                : DOM.getCodigo();
    }

    /**
     * Metodo para validar la forma de pago de WhooCommerce y retornar su valor
     *
     * @param payment_method String con el metodo de pago
     * @return String con la categoria
     */
    public static String getTipoPago(String payment_method) {
        for (TipoPagoEnum tipoPago : TipoPagoEnum.values()) {
            if (tipoPago.getCategoria().equalsIgnoreCase(payment_method)) {
                return tipoPago.getTipo();
            }
        }
        return null;
    }

    /**
     * @param value Dato de valor String con formato de decimales 0.00
     * @return Bigdecimal convertiendo el string en decimal
     */
    public static BigDecimal safeParseBigDecimal(String value) {
        try {
            return value != null ? new BigDecimal(value) : BigDecimal.ZERO;
        } catch (NumberFormatException e) {
            return BigDecimal.ZERO; //Manejo de formatos no validos
        }
    }

    /**
     * @param date String definido por WhooCommerce 2025-04-12T10:16:06
     * @return LocalDate con la fecha parseada
     */
    public static LocalDate StringToLocalDate(String date) {
        LocalDateTime dateTime = LocalDateTime.parse(date);
        return dateTime.toLocalDate();
    }

    /**
     * Funcion que devuelve un int con el formato establecido Usado para Lote
     *
     * @param date localdate de la fecha
     * @return Integer con la fecha convertida 20250412
     */
    public static Integer formatDate(LocalDate date) {
        return Integer.parseInt(date.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
    }
}
