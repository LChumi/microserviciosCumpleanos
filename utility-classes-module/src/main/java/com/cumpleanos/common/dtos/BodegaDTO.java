package com.cumpleanos.common.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BodegaDTO {
    private Long id;
    private Long empresa;
    private String bodId;
    private String nombre;
    private String bodUbicacion;
    private Short proveedor;
    private Long almacen;

}
