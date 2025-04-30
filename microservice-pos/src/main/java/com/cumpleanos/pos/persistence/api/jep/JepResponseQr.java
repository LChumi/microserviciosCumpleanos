package com.cumpleanos.pos.persistence.api.jep;

public record JepResponseQr(
        Data data,
        String codigoTransaccion,
        String mensaje,
        String[] errores
) {}