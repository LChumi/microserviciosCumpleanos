package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.ImporitemId;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
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
    private Boolean iitTipoItem = false;

    @Column(name = "IIT_CANT_PEDIDA", nullable = false, precision = 17, scale = 4)
    private BigDecimal iitCantPedida;

    @Column(name = "IIT_CANT_LLEGADA", precision = 17, scale = 4)
    private BigDecimal iitCantLlegada;

    @Column(name = "IIT_CANT_LIQUIDADA", precision = 17, scale = 4)
    private BigDecimal iitCantLiquidada;

    @Column(name = "IIT_COSTO", precision = 18, scale = 5)
    private BigDecimal iitCosto;

    @Column(name = "IIT_PRECIO", nullable = false, precision = 18, scale = 5)
    private BigDecimal iitPrecio;

    @Column(name = "IIT_VALORCF", precision = 17, scale = 4)
    private BigDecimal iitValorcf;

    @Column(name = "IIT_VALORCIF", precision = 17, scale = 4)
    private BigDecimal iitValorcif;

    @Column(name = "IIT_VOLUMEN", precision = 17, scale = 4)
    private BigDecimal iitVolumen;

    @Column(name = "CREA_USR", length = 10)
    private String creaUsr;

    @Column(name = "CREA_FECHA")
    private LocalDate creaFecha;

    @Column(name = "MOD_USR", length = 10)
    private String modUsr;

    @Column(name = "MOD_FECHA")
    private LocalDate modFecha;

    @Column(name = "IIT_PORCENTAJE", precision = 5, scale = 2)
    private BigDecimal iitPorcentaje;

    @Column(name = "IIT_PROCEDENCIA")
    private Long iitProcedencia;

    @Column(name = "IIT_CLIENTE")
    private Long iitCliente;

    @Column(name = "IIT_DFAC_COMPROBA")
    private Long iitDfacComproba;

    @Column(name = "IIT_DFAC_SECUENCIA")
    private Long iitDfacSecuencia;

    @Column(name = "IIT_SCOSTO")
    private Boolean iitScosto;

    @Column(name = "IIT_VALORAPOR", precision = 17, scale = 4)
    private BigDecimal iitValorapor;

    @Column(name = "IIT_VALORAESP", precision = 17, scale = 4)
    private BigDecimal iitValoraesp;

    @ColumnDefault("0")
    @Column(name = "IIT_DISAESP")
    private Boolean iitDisaesp;

    @ColumnDefault("0")
    @Column(name = "IIT_VALORSPOR", precision = 17, scale = 4)
    private BigDecimal iitValorspor;

    @ColumnDefault("0")
    @Column(name = "IIT_VALORGASTO", precision = 17, scale = 4)
    private BigDecimal iitValorgasto;

    @Column(name = "IIT_NO_IVA")
    private Boolean iitNoIva;

    @Column(name = "IIT_PRECIO1", precision = 18, scale = 5)
    private BigDecimal iitPrecio1;

    @Column(name = "IIT_PARTIDA1")
    private Long iitPartida1;

    @Column(name = "IIT_PORCENTAJE1", precision = 5, scale = 2)
    private BigDecimal iitPorcentaje1;

    @Column(name = "IIT_VALORAESP1", precision = 17, scale = 4)
    private BigDecimal iitValoraesp1;

    @Column(name = "IIT_VALORCF1", precision = 17, scale = 4)
    private BigDecimal iitValorcf1;

    @Column(name = "IIT_VALORAPOR1", precision = 17, scale = 4)
    private BigDecimal iitValorapor1;

    @Column(name = "IIT_VALORSPOR1", precision = 17, scale = 4)
    private BigDecimal iitValorspor1;

    @Column(name = "IIT_VALORGASTO1", precision = 17, scale = 4)
    private BigDecimal iitValorgasto1;

    @Column(name = "IIT_VALORCIF1", precision = 17, scale = 4)
    private BigDecimal iitValorcif1;

    @Column(name = "IIT_DISAESP1")
    private Boolean iitDisaesp1;

    @Column(name = "IIT_COSTO1", precision = 18, scale = 5)
    private BigDecimal iitCosto1;

    @Column(name = "IIT_OBSERVACIONES", length = 100)
    private String iitObservaciones;

    @Column(name = "IIT_ORDEN")
    private Long iitOrden;

    @Column(name = "IIT_VALORCIF_M", precision = 17, scale = 4)
    private BigDecimal iitValorcifM;

    @Column(name = "IIT_VALORCIF_M1", precision = 17, scale = 4)
    private BigDecimal iitValorcifM1;
}
