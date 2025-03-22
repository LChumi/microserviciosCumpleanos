package com.cumpleanos.core.models.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Immutable
@Table(name = "CARGA_PRODUCTO_ECOM_V", schema = "DATA_USR")
public class CargaProductoEcomV {

    @Id
    @Column(name = "PRE_CODIGO", length = 80)
    private String codigo;

    @Column(name = "PRE_EMPRESA", nullable = false)
    private Long empresa;

    @Column(name = "PRE_PRODUCTO", nullable = false)
    private Long producto;

    @Column(name = "PRE_PRO_ID", nullable = false, length = 20)
    private String pro_id;

    @Column(name = "PRE_PRO_ID1", length = 50)
    private String pro_id1;

    @Column(name = "PRE_PRO_NOMBRE", length = 100)
    private String pro_nombre;

    @Column(name = "PRE_SECCION")
    private Long seccion;

    @Column(name = "PRE_PRECIO1", precision = 17, scale = 4)
    private BigDecimal precio1;

    @Column(name = "PRE_EMPRESA_GRUPO", nullable = false)
    private Long empresaGrupo;

    @Column(name = "PRE_URL_WS", length = 500)
    private String urlWs;

    @Column(name = "PRE_STOCK")
    private Long stock;

    @Column(name = "PRE_CANT_MIN")
    private Long cantMin;

    @Column(name = "PRE_TIPO", length = 100)
    private String subcategoria;

    @Column(name = "PRE_GRUPO", nullable = false, length = 100)
    private String categoria;
}
