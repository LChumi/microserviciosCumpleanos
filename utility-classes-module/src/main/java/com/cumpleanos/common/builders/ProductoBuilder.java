package com.cumpleanos.common.builders;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductoBuilder {

    private Long  codigo;
    private Long empresa;

    private String proId;
    private String proId1;
    private String nombre;
    private BigDecimal precio1;
    private Long impuesto;
    private Short cargaWeb;

    private Long proveedorId;
    private Long categoriaId;

}