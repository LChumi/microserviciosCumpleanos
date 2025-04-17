package com.cumpleanos.core.models.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CreposicionTiposEnum {
    //CRP_TIPO
    TIPO_REP(1, "REP"),
    TIPO_INV(2, "INV"),
    TIPO_SOT(3, "SOT"),
    TIPO_ECOM(4, "E-COM"),
    TIPO_DEV(5, "DEV"),
    TIPO_SHOWROOM(6, "SHOWROOM"),
    TIPO_SUGERIDO(7, "SUGERIDO"),
    //CRP_ESTADO
    ESTADO_PROCESO(0, "EN PROCESO"),
    ESTADO_APROBAR(1, "POR APROBAR"),
    ESTADO_PREPEDIDO(2, "PREPEDIDO"),
    ESTADO_PEDIDO(3, "PEDIDO"),
    ESTADO_FACTURADO(4, "FACTURADO"),
    //CRP_FINALIZADO
    FINALIZADO(1, "FINALIZADO"),
    NO_FINALIZADO(0, "NO FINALIZADO"),
    //CRP_TIPO_TRANSPORTE
    INT(1, "INTERNO"),
    EXT(2, "EXTERNO"),
    //CRP_TIPO_RETIRO
    DOM(1, "DOMICILIO"),
    ALM(2, "ALMACEN");

    private final Integer codigo;
    private final String descripcion;
}
