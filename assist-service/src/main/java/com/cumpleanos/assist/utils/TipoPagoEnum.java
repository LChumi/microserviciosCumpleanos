package com.cumpleanos.assist.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TipoPagoEnum {
    TARJETA("pagomedios", "TARJETA_CREDITO"),
    DEPOSITO("bacs", "DEPOSITO"),
    TRANSFERENCIA("", "TRANSFERENCIA");

    private final String categoria;
    private final String tipo;
}
