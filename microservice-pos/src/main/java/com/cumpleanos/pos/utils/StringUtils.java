package com.cumpleanos.pos.utils;

import com.cumpleanos.pos.persistence.entity.ReciboPOSView;

import static com.cumpleanos.pos.utils.DateUtils.obtenerFechaHora;

public class StringUtils {

    public static String getTransactionReference(ReciboPOSView v) {
        return obtenerFechaHora() + v.getCodigo() + v.getPventa() + v.getFinanciera();
    }

}
