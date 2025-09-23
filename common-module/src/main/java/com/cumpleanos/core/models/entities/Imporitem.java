package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.ImporitemId;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "IMPORITEM", schema = "DATA_USR", indexes = {
        @Index(name = "IMPORITEM_NIDX1", columnList = "IIT_PRODUCTO, IIT_EMPRESA"),
        @Index(name = "IMPORITEM_NIDX2", columnList = "IIT_PARTIDA, IIT_EMPRESA"),
        @Index(name = "IMPORITEM_NIDX10", columnList = "IIT_BODEGA, IIT_EMPRESA")
})
public class Imporitem {

    @EmbeddedId
    private ImporitemId id;

    @Column(name = "IIT_TIPO_ITEM", nullable = false)
    private Boolean tipoItem = false;

    @Column(name = "IIT_CANT_PEDIDA", nullable = false, precision = 17, scale = 4)
    private BigDecimal cantPedida;

    @Column(name = "IIT_CANT_LLEGADA", precision = 17, scale = 4)
    private BigDecimal cantLlegada;

    @Column(name = "IIT_CANT_LIQUIDADA", precision = 17, scale = 4)
    private BigDecimal cantLiquidada;

    @Column(name = "IIT_COSTO", precision = 18, scale = 5)
    private BigDecimal costo;

    @Column(name = "IIT_PRECIO", nullable = false, precision = 18, scale = 5)
    private BigDecimal precio;

    @Column(name = "IIT_VALORCF", precision = 17, scale = 4)
    private BigDecimal valorcf;

    @Column(name = "IIT_VALORCIF", precision = 17, scale = 4)
    private BigDecimal valorcif;

    @Column(name = "IIT_VOLUMEN", precision = 17, scale = 4)
    private BigDecimal volumen;

    @Column(name = "CREA_USR", length = 10)
    private String creaUsr;

    @Column(name = "CREA_FECHA")
    private LocalDate creaFecha;

    @Column(name = "MOD_USR", length = 10)
    private String modUsr;

    @Column(name = "MOD_FECHA")
    private LocalDate modFecha;

    @Column(name = "IIT_PORCENTAJE", precision = 5, scale = 2)
    private BigDecimal porcentaje;

    @Column(name = "IIT_PROCEDENCIA")
    private Long procedencia;

    @Column(name = "IIT_CLIENTE")
    private Long cliente;

    @Column(name = "IIT_DFAC_COMPROBA")
    private Long dfacComproba;

    @Column(name = "IIT_DFAC_SECUENCIA")
    private Long dfacSecuencia;

    @Column(name = "IIT_SCOSTO")
    private Boolean iitScosto;

    @Column(name = "IIT_VALORAPOR", precision = 17, scale = 4)
    private BigDecimal valorapor;

    @Column(name = "IIT_VALORAESP", precision = 17, scale = 4)
    private BigDecimal valoraesp;

    @ColumnDefault("0")
    @Column(name = "IIT_DISAESP")
    private Boolean disaesp;

    @ColumnDefault("0")
    @Column(name = "IIT_VALORSPOR", precision = 17, scale = 4)
    private BigDecimal valorspor;

    @ColumnDefault("0")
    @Column(name = "IIT_VALORGASTO", precision = 17, scale = 4)
    private BigDecimal valorgasto;

    @Column(name = "IIT_NO_IVA")
    private Boolean noIva;

    @Column(name = "IIT_PRECIO1", precision = 18, scale = 5)
    private BigDecimal precio1;

    @Column(name = "IIT_PARTIDA1")
    private Long partida1;

    @Column(name = "IIT_PORCENTAJE1", precision = 5, scale = 2)
    private BigDecimal porcentaje1;

    @Column(name = "IIT_VALORAESP1", precision = 17, scale = 4)
    private BigDecimal valoraesp1;

    @Column(name = "IIT_VALORCF1", precision = 17, scale = 4)
    private BigDecimal valorcf1;

    @Column(name = "IIT_VALORAPOR1", precision = 17, scale = 4)
    private BigDecimal valorapor1;

    @Column(name = "IIT_VALORSPOR1", precision = 17, scale = 4)
    private BigDecimal valorspor1;

    @Column(name = "IIT_VALORGASTO1", precision = 17, scale = 4)
    private BigDecimal valorgasto1;

    @Column(name = "IIT_VALORCIF1", precision = 17, scale = 4)
    private BigDecimal valorcif1;

    @Column(name = "IIT_DISAESP1")
    private Boolean disaesp1;

    @Column(name = "IIT_COSTO1", precision = 18, scale = 5)
    private BigDecimal costo1;

    @Column(name = "IIT_OBSERVACIONES", length = 100)
    private String observaciones;

    @Column(name = "IIT_ORDEN")
    private Long orden;

    @Column(name = "IIT_VALORCIF_M", precision = 17, scale = 4)
    private BigDecimal valorcifM;

    @Column(name = "IIT_VALORCIF_M1", precision = 17, scale = 4)
    private BigDecimal valorcifM1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "IIT_PRODUCTO", referencedColumnName = "PRO_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "IIT_EMPRESA", referencedColumnName = "PRO_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Producto producto;

    @Column(name = "IIT_PRODUCTO")
    private Long iitProducto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "IIT_PARTIDA", referencedColumnName = "IPR_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "IIT_EMPRESA", referencedColumnName = "IPR_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Imppartida imppartida;

    @Column(name = "IIT_PARTIDA")
    private Long iitPartida;

    @Column(name = "IIT_BODEGA")
    private Long iitBodega;
}
