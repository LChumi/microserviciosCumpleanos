package com.cumpleanos.assist.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "PRODUCTO_TEMP")
@Data
@SequenceGenerator(name = "PRODUCTO_TEMP_S_CODIGO", sequenceName = "PRODUCTO_TEMP_S_CODIGO", allocationSize = 1)
public class ProductoTemp {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCTO_TEMP_S_CODIGO")
    @Column(name = "PRO_CODIGO", nullable = false)
    private Long codigo;

    @Column(name = "PRO_EMPRESA", nullable = false)
    private Long empresa;

    @Column(name = "PRO_ID", nullable = false, length = 20)
    private String proId;

    @Column(name = "PRO_NOMBRE", nullable = false, length = 500)
    private String nombre;

    @Column(name = "PRO_INVENTARIO")
    private Boolean inventario;

    @Column(name = "PRO_TPRODUCTO")
    private Long tProducto;

    @Column(name = "PRO_CTA_INVENTARIO", nullable = false)
    private Long ctaInventario;

    @Column(name = "PRO_CTA_VENTA", nullable = false)
    private Long ctaVenta;

    @Column(name = "PRO_CTA_COSTO", nullable = false)
    private Long ctaCosto;

    @Column(name = "PRO_PORC_COMISION", precision = 5, scale = 2)
    private BigDecimal porcComision;

    @Column(name = "PRO_STOCK_MINIMO")
    private Integer stockMinimo;

    @Column(name = "PRO_STOCK_MAXIMO")
    private Integer stockMaximo;

    @Column(name = "PRO_UNIDAD", nullable = false)
    private Long unidad;

    @Column(name = "PRO_IMPUESTO")
    private Boolean impuesto;

    @Column(name = "PRO_FECHA_LANZAM")
    private LocalDate fechaLanzam;

    @Column(name = "PRO_FECHA_VENCE")
    private LocalDate fechaVence;

    @Column(name = "PRO_NIVEL", nullable = false)
    private Short nivel;

    @Column(name = "PRO_SERIE")
    private Boolean serie;

    @Column(name = "PRO_PESO", precision = 9, scale = 2)
    private BigDecimal peso;

    @Column(name = "PRO_INFSERIE")
    private Long infserie;

    @Column(name = "PRO_INACTIVO")
    private Boolean inactivo;

    @Column(name = "PRO_PROVEEDOR")
    private Long proveedor;

    @Column(name = "PRO_UBICACION")
    private Long ubicacion;

    @Column(name = "PRO_ULT_COSTO", precision = 23, scale = 10)
    private BigDecimal ultCosto;

    @Column(name = "PRO_GPRODUCTO", nullable = false)
    private Long gproducto;

    @Column(name = "PRO_ALCOHOL")
    private Boolean alcohol;

    @Column(name = "PRO_GRADO", precision = 17, scale = 4)
    private BigDecimal grado;

    @ColumnDefault("0")
    @Column(name = "PRO_CRITICO")
    private Boolean critico;

    @Column(name = "PRO_ENVASE")
    private Long envase;

    @Column(name = "PRO_TRANSPORTE")
    private Boolean transporte;

    @Column(name = "PRO_ICE1", length = 10)
    private String ice1;

    @Column(name = "PRO_ICE2", length = 10)
    private String ice2;

    @Column(name = "PRO_ICE3", length = 10)
    private String ice3;

    @Column(name = "PRO_ICE4", length = 10)
    private String ice4;

    @Column(name = "PRO_ICE5", length = 10)
    private String ice5;

    @Column(name = "PRO_BUFFER")
    private Boolean buffer;

    @Column(name = "PRO_ID1", length = 50)
    private String id1;

    @Column(name = "PRO_MARCA")
    private Long marca;

    @Column(name = "PRO_CTA_INVENTARIO1")
    private Long ctaInventario1;

    @Column(name = "PRO_CTA_VENTA1")
    private Long ctaVenta1;

    @Column(name = "PRO_CTA_COSTO1")
    private Long ctaCosto1;

    @Column(name = "PRO_PROCEDENCIA")
    private Boolean procedencia;

    @Column(name = "PRO_DETALLE")
    private Short detalle;
}
