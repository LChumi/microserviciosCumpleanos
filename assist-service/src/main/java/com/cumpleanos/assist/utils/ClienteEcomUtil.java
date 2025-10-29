package com.cumpleanos.assist.utils;

import com.cumpleanos.common.builders.ecommerce.*;
import com.cumpleanos.core.models.entities.Cliente;
import com.cumpleanos.core.models.ids.ClienteId;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import static com.cumpleanos.common.utils.CedulatUtils.*;

public class ClienteEcomUtil {

    public static String generarPrefix(String nombre) {
        return "ECOM-" + nombre.substring(0, 3).toUpperCase().trim();
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

        nombre = validarCaracteres(nombre.toUpperCase().trim(), 100);
        direccion = validarCaracteres(direccion.toUpperCase().trim(), 100);

        String email = billing.getEmail() != null ? billing.getEmail().trim() : "";
        String telefono = billing.getPhone() != null ? billing.getPhone().trim() : "";
        String postal = billing.getPostcode() != null ? billing.getPostcode().trim() : "";

        final Cliente ecommerce = new Cliente();
        final ClienteId id = new ClienteId();
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
        ecommerce.setImpuestos((short) 1);
        ecommerce.setFechaing(LocalDate.now());
        ecommerce.setMail(email);
        ecommerce.setTelefono1(telefono);
        ecommerce.setApartPostal(postal);

        return ecommerce;
    }

}
