package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.ImportacionId;
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
@Table(name = "IMPORTACION", schema = "DATA_USR", indexes = {
        @Index(name = "IMPORTACION_IDX1", columnList = "IPT_PROVEEDOR, IPT_EMPRESA"),
        @Index(name = "IMPORTACION_IDX3", columnList = "IPT_FPAGO, IPT_EMPRESA"),
        @Index(name = "IMPORTACION_IDX", columnList = "IPT_PORIGEN, IPT_EMPRESA"),
        @Index(name = "IMPORTACION_IDX2", columnList = "IPT_PEMBARQUE, IPT_EMPRESA"),
        @Index(name = "IMPORTACION_IDX4", columnList = "IPT_TRANSPORTE, IPT_EMPRESA"),
        @Index(name = "IMPORTACION_IDX5", columnList = "IPT_MONEDA, IPT_EMPRESA")
})
public class Importacion {

    @EmbeddedId
    private ImportacionId id;

    @Column(name = "IPT_NRO_POLIZA", length = 20)
    private String nroPoliza;

    @Column(name = "IPT_CARTA", length = 20)
    private String carta;

    @Column(name = "IPT_FAC_FOB", length = 20)
    private String facFob;

    @Column(name = "IPT_FAC_FLETE", length = 20)
    private String facFlete;

    @Column(name = "IPT_NRO_GUIA", length = 20)
    private String nroGuia;

    @Column(name = "IPT_OBSERVACION", length = 100)
    private String observacion;

    @Column(name = "IPT_ORIGEN", length = 50)
    private String origen;

    @Column(name = "IPT_PTO_EMBARQUE", length = 100)
    private String ptoEmbarque;

    @Column(name = "IPT_VIA_TRANSPORTE", length = 50)
    private String viaTransporte;

    @Column(name = "IPT_VERIFICADOR", length = 50)
    private String verificador;

    @Column(name = "IPT_ADUANA", length = 50)
    private String aduana;

    @Column(name = "IPT_VALOR_FOB", precision = 17, scale = 4)
    private BigDecimal valorFob;

    @Column(name = "IPT_VALOR_FLETE", precision = 17, scale = 4)
    private BigDecimal valorFlete;

    @Column(name = "IPT_CANTIDAD", precision = 17, scale = 4)
    private BigDecimal cantidad;

    @Column(name = "IPT_INACTIVO")
    private Boolean inactivo;

    @Column(name = "CREA_USR", length = 10)
    private String creaUsr;

    @Column(name = "CREA_FECHA")
    private LocalDate creaFecha;

    @Column(name = "MOD_USR", length = 10)
    private String modUsr;

    @Column(name = "MOD_FECHA")
    private LocalDate modFecha;

    @Column(name = "IPT_FECHA")
    private LocalDate fecha;

    @ColumnDefault("0")
    @Column(name = "IPT_LIQUIDACION")
    private Boolean liquidacion;

    @Column(name = "IPT_REFERENDUM", length = 20)
    private String referendum;

    @Column(name = "IPT_VALOR_SEGURO", precision = 17, scale = 4)
    private BigDecimal valorSeguro;

    @Column(name = "IPT_INLAND", precision = 17, scale = 4)
    private BigDecimal inland;

    @Column(name = "IPT_VALOR_BODEGA", precision = 17, scale = 4)
    private BigDecimal valorBodega;

    @Column(name = "IPT_PORIGEN")
    private Long pOrigen;

    @Column(name = "IPT_FPAGO")
    private Long fPago;

    @Column(name = "IPT_MONEDA")
    private Long moneda;

    @Column(name ="IPT_PEMBARQUE")
    private Long embarque;

    @Column(name = "IPT_TRANSPORTE")
    private Long transporte;

    @Column(name = "IPT_PROVEEDOR")
    private Long proveedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "IPT_PORIGEN", referencedColumnName = "UBI_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "IPT_EMPRESA", referencedColumnName = "UBI_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Ubicacion ubicacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "IPT_FPAGO", referencedColumnName = "IFM_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "IPT_EMPRESA", referencedColumnName = "IFM_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Impformapago impformapago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "IPT_MONEDA", referencedColumnName = "IMM_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "IPT_EMPRESA", referencedColumnName = "IMM_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Impmoneda impmoneda;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "IPT_PEMBARQUE", referencedColumnName = "IPP_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "IPT_EMPRESA", referencedColumnName = "IPP_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Imppuerto imppuerto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "IPT_TRANSPORTE", referencedColumnName = "IMT_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "IPT_EMPRESA", referencedColumnName = "IMT_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private  Imptransporte imptransporte;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "IPT_PROVEEDOR", referencedColumnName = "CLI_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "IPT_EMPRESA", referencedColumnName = "CLI_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Cliente cliente;
}