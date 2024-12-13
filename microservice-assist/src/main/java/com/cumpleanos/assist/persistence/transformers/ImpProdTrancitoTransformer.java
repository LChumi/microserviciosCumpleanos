package com.cumpleanos.assist.persistence.transformers;

import com.cumpleanos.assist.persistence.views.ImpProdTrancitoVw;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class ImpProdTrancitoTransformer {
    private String nroComprobante;
    private LocalDate fecha;
    private String observacion;
    private Long cantidadPedida;
    private BigDecimal fob;
    private BigDecimal fobTotal;
    private String tipoDoc;

    public static ImpProdTrancitoTransformer mapToImpProdTrancitoVw(ImpProdTrancitoVw imp) {
        return ImpProdTrancitoTransformer.builder()
                .nroComprobante(imp.getNroComprobante())
                .fecha(imp.getFecha())
                .observacion(imp.getObservacion())
                .cantidadPedida(imp.getCantPedida())
                .fob(imp.getFob())
                .fobTotal(imp.getFobTotal())
                .tipoDoc(imp.getTipoDoc())
                .build();
    }
}