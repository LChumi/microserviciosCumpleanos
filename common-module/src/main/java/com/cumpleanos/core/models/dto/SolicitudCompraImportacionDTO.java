package com.cumpleanos.core.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
public class SolicitudCompraImportacionDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigInteger cco;
    private String almacen;
    private String almacenId;
    private LocalDate fecha;
    private String sigla;
    private String documento;
    private String concepto;
    private Set<DfacturaDTO> items;
}
