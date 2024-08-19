package com.cumpleanos.common.builders;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductoPartidaBuilder {

    //Datos Producto
    private Long prodCodigo;
    private String prodNombre;
    private String barra;
    private String item;

    //Datos Partida
    private Long partCodigo;
    private String partidaNombre;
    private String partidaId;
    private Integer porcentaje;
    private String arancel;

}