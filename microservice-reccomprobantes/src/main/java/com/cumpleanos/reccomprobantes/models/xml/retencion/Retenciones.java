package com.cumpleanos.reccomprobantes.models.xml.retencion;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Data
public class Retenciones {
    private List<Retencion> retencion;

    public String getTotalRetencion() {
        double total = 0.0;
        for (Retencion retencion : retencion) {
            try {
                double valorRetenido = Double.parseDouble(retencion.getValorRetenido());
                total += valorRetenido;
            } catch (Exception e) {
                return null;
            }
        }
        // Usar BigDecimal para formatear el total con dos o tres decimales
        BigDecimal totalRounded = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP);
        return totalRounded.toString();
    }
}
