package com.cumpleanos.core.models.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class SolicitudComproImportacionDTO {
    private BigInteger cco;
    private String almacen;
    private String alamcenId;
    private LocalDate fecha;
    private String sigla;
    private String docuento;
    private String concepto;
    private List<DfacturaDto> items;
}
