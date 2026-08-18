package com.cumpleanos.common.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductoReposicionDTO {
    private Long codigo;
    private String descripcion;
    private String observacion;
    private String gondola;
    private Integer canSol;
    private Integer canApr;
    private Integer stock;
    private Integer transito;
}
