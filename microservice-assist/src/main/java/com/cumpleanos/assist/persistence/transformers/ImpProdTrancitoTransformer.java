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
    private String ccomproba;
    private LocalDate fecha;
    private String observacion;
    private Long cantidadPedida;
    private BigDecimal fob;
    private BigDecimal fobTotalPedido;
    private BigDecimal fobTotalAprobado;
    private String tipoDoc;

    public static ImpProdTrancitoTransformer mapToImpProdTrancitoVw(ImpProdTrancitoVw imp) {
        return ImpProdTrancitoTransformer.builder()
                .nroComprobante(imp.getNroComprobante())
                .ccomproba(String.valueOf(imp.getCcoComproba()))
                .fecha(imp.getFecha())
                .observacion(imp.getObservacion())
                .cantidadPedida(imp.getCantPedida() != null ? imp.getCantPedida() : 0)
                .fob(imp.getFob())
                .fobTotalPedido(imp.getFobTotalPedido())
                .fobTotalAprobado(imp.getFobTotalAprobado())
                .tipoDoc(imp.getTipoDoc())
                .build();
    }
}