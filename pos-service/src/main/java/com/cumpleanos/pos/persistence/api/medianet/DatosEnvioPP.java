package com.cumpleanos.pos.persistence.api.medianet;

public record DatosEnvioPP(
        double subtotal,
        double subtotal0,
        double iva,
        double total,
        String tipoTransaccion,
        String codigoDiferido,
        String plazo,
        String mid,
        String tid,
        String cid,
        String pVenta,
        //Campos anulacion
        String referencia,
        String lote,
        String numeroAutorizacion
) {
}
