package com.cumpleanos.common.dtos;

import com.cumpleanos.common.records.ClienteDTO;
import com.cumpleanos.common.records.DfacturaDTO;
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
public class ComprobanteDetalleProductoDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigInteger cco;
    //Datos almacen
    private String almacen;
    private String almacenId;

    private LocalDate fecha;
    //Datos de Documento & tipo Documento
    private String sigla;
    private String documento;
    private String concepto;
    //Secuencia y sigla de comprobnte
    private String comprobante;
    private ClienteDTO cliente;
    private Set<DfacturaDTO> items;
}
