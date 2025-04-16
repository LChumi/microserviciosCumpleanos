package com.cumpleanos.assist.utils;

import com.cumpleanos.common.builders.ecommerce.Ing;
import com.cumpleanos.common.builders.ecommerce.PedidoWoocommerce;
import com.cumpleanos.common.builders.ecommerce.PedidosWoocommerceMetaDatum;
import com.cumpleanos.core.models.entities.Cliente;
import com.cumpleanos.core.models.entities.Creposicion;
import com.cumpleanos.core.models.entities.Sistema;
import com.cumpleanos.core.models.ids.ClienteId;
import com.cumpleanos.core.models.ids.CreposicionId;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import static com.cumpleanos.common.utils.CedulatUtils.*;

public class ClienteEcomUtil {

    private static final String USER = "WEB_USR";
    private static final String OBS="PEDIDO GENERADO DESDE E-COMMERCE ";


    private static String generarPrefix(String nombre){
        return "ECOM-"+nombre.substring(0,3).toUpperCase().trim();
    }

    public static String getBillingDocument(PedidosWoocommerceMetaDatum[] metadata) {
        return Arrays.stream(metadata)
                .filter(meta -> "billing_document".equals(meta.getKey()))
                .map(PedidosWoocommerceMetaDatum::getValue)
                .findFirst()
                .orElse(null);
    }

    public static Cliente createClienteEcommerce(String cedRuc, Ing billing, Long empresa, Long tipCliente) {
        String nombre = (billing.getLast_name() != null ? billing.getLast_name().trim() : "") +
                " " +
                (billing.getFirst_name() != null ? billing.getFirst_name().trim() : "");
        nombre = nombre.trim().toUpperCase();
        String direccion = (billing.getAddress_1() != null ? billing.getAddress_1().trim() : "") +
                " " +
                (billing.getAddress_2() != null ? billing.getAddress_2().trim() : "");
        direccion = direccion.trim().toUpperCase();

        nombre = validarCaracteres(nombre.toUpperCase().trim(),100);
        direccion = validarCaracteres(direccion.toUpperCase().trim(),100);

        String email = billing.getEmail() != null ? billing.getEmail().trim() : "";
        String telefono = billing.getPhone() != null ? billing.getPhone().trim() : "";
        String postal = billing.getPostcode() != null ? billing.getPostcode().trim() : "";

        Cliente ecommerce = new Cliente();
        ClienteId id = new ClienteId();
        id.setEmpresa(empresa);

        ecommerce.setId(id);
        ecommerce.setNombre(nombre);
        ecommerce.setRucCedula(cedRuc);
        ecommerce.setTipoced(tipoCedula(cedRuc));
        ecommerce.setTipo((short) 1);
        ecommerce.setDireccion(direccion);
        ecommerce.setTipoper(tipCliente.shortValue());
        ecommerce.setInactivo(true);
        ecommerce.setCupo(BigDecimal.ZERO);
        ecommerce.setImpuestos((short)1);
        ecommerce.setFechaing(LocalDate.now());
        ecommerce.setMail(email);
        ecommerce.setTelefono1(telefono);
        ecommerce.setApartPostal(postal);

        return ecommerce;
    }

    public static Creposicion createCreposicion(PedidoWoocommerce pedido, Sistema sis, Long alm, Long bod, Long cliId){
        String address1 = pedido.getShipping().getAddress_1() != null ? pedido.getShipping().getAddress_1().trim().toUpperCase() : "";
        String address2 = pedido.getShipping().getAddress_2() != null ? pedido.getShipping().getAddress_2().trim().toUpperCase() : "";

        Creposicion creposicion = new Creposicion();
        CreposicionId id = new CreposicionId();

        id.setEmpresa(2L);

        creposicion.setId(id);
        creposicion.setUsuario(USER);
        creposicion.setObservacion(OBS+ pedido.getId());
        creposicion.setFecha(LocalDate.now());
        creposicion.setEstado((short) 0);
        creposicion.setFinalizado((short) 0);
        creposicion.setAlmacenId(alm);
        creposicion.setBodegaId(bod);
        creposicion.setTipo(4L);
        creposicion.setEmpresaGrupo(sis.getEmpresaGrupo());
        creposicion.setReferencia(String.valueOf(pedido.getId()));

        //Detalle envio shipping
        creposicion.setCliDirEntrega(address1);
        creposicion.setReferenciaEntrega(address2);
        creposicion.setCodigoPostal(pedido.getShipping().getPostcode());

        //Detalle entrega
        return null;
    }


}
