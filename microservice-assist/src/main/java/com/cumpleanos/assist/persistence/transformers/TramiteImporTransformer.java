package com.cumpleanos.assist.persistence.transformers;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TramiteImporTransformer {
    private String numTramite;
    private int cantidad;
    private double fob;
    private double total;
    private String estado;
}
