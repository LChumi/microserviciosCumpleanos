package com.cumpleanos.assist.utils;

import com.cumpleanos.common.builders.ecommerce.Ing;
import com.cumpleanos.common.builders.ecommerce.PedidosWoocommerceMetaDatum;
import com.cumpleanos.core.models.entities.Cliente;
import com.cumpleanos.core.models.ids.ClienteId;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import static com.cumpleanos.common.utils.CedulatUtils.*;

public class ClienteEcomUtil {

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
        String nombre = billing.getLast_name() + " " + billing.getFirst_name();
        String direccion = billing.getAddress_1() + " " + billing.getAddress_2();

        nombre = validarCaracteres(nombre.toUpperCase().trim(),100);
        direccion = validarCaracteres(direccion.toUpperCase().trim(),100);

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

        return ecommerce;
    }


}
