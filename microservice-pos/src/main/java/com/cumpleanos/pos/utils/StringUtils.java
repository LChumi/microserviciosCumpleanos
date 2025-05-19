package com.cumpleanos.pos.utils;

import com.cumpleanos.pos.persistence.entity.ReciboPOSView;

import static com.cumpleanos.pos.utils.DateUtils.obtenerFechaHora;

public class StringUtils {

    public static String getTransactionReference(ReciboPOSView v) {
        return obtenerFechaHora() + v.getCodigo() + v.getPventa() + v.getFinanciera();
    }

    public static Long getEmpresa(String codigoTransaccion) {
        if (codigoTransaccion == null || !codigoTransaccion.contains("-")) {
            throw new IllegalArgumentException("Código de transacción inválido.");
        }

        String[] partes = codigoTransaccion.split("-");
        try {
            return Long.parseLong(partes[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Formato de empresa inválido.", e);
        }
    }

}
