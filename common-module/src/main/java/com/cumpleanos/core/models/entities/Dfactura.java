package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.DfacturaId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Table(name = "DFACTURA", indexes = {
        @Index(name = "DFACTURA_UIDX1", columnList = "DFAC_CFAC_COMPROBA, DFAC_SECUENCIA, DFAC_PRODUCTO, DFAC_CATPRODUCTO, DFAC_BODEGA", unique = true),
        @Index(name = "DFACTURA_UIDX2", columnList = "DFAC_CFAC_COMPROBA, DFAC_EMPRESA"),
        @Index(name = "DFACTURA_UIDX3", columnList = "DFAC_PRODUCTO, DFAC_EMPRESA"),
        @Index(name = "DFACTURA_NIDX23", columnList = "DFAC_PRODUCTO, DFAC_SECUENCIA, DFAC_CFAC_COMPROBA, DFAC_EMPRESA"),
        @Index(name = "DFACTURA_UIDX4", columnList = "DFAC_CUENTA, DFAC_EMPRESA"),
        @Index(name = "DFACTURA_NIDX4", columnList = "DFAC_ACTIVO, DFAC_EMPRESA"),
        @Index(name = "DFACTURA_NIDX12", columnList = "DFAC_CATPRODUCTO, DFAC_EMPRESA"),
        @Index(name = "DFACTURA_UIDX5", columnList = "DFAC_BODEGA, DFAC_EMPRESA"),
        @Index(name = "DFACTURA_NIDX21", columnList = "DFAC_UDIGITADA, DFAC_EMPRESA"),
        @Index(name = "DFACTURA_NIDX7", columnList = "DFAC_UDIGITADA, DFAC_PRODUCTO, DFAC_EMPRESA"),
        @Index(name = "DFACTURA_NIDX1", columnList = "DFAC_CENTRO, DFAC_EMPRESA"),
        @Index(name = "DFACTURA_NIDX20", columnList = "DFAC_TLIQEVENTO, DFAC_EMPRESA"),
        @Index(name = "DFACTURA_NIDX2", columnList = "DFAC_CLIENTE, DFAC_EMPRESA"),
        @Index(name = "DFACTURA_NIDX5", columnList = "DFAC_AGENTE, DFAC_EMPRESA"),
        @Index(name = "DFACTURA_NIDX6", columnList = "DFAC_ALMACEN, DFAC_EMPRESA"),
        @Index(name = "DFACTURA_NIDX3", columnList = "DFAC_DDO_PAGO, DFAC_DDO_DOCTRAN, DFAC_DDO_TRANSACC, DFAC_DDO_COMPROBA, DFAC_EMPRESA"),
        @Index(name = "DFACTURA_NIDX10", columnList = "DFAC_GASTO_VEHICULO, DFAC_EMPRESA"),
        @Index(name = "DFACTURA_UIDX9", columnList = "DFAC_PEDIDO_IMP, DFAC_EMPRESA"),
        @Index(name = "DFACTURA_UIDX21", columnList = "DFAC_FACTURA, DFAC_EMPRESA")
})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
        "cliente", "factor", "producto", "listaPre", "bodega", "ccomproba", "ccomproba1", "umedida", "agente", "almacen", "centro"
})
public class Dfactura {

    @EmbeddedId
    private DfacturaId id;

    @Column(name = "DFAC_CUENTA")
    private Long cuenta;

    @NotNull
    @Column(name = "DFAC_CANTIDAD", nullable = false, precision = 17, scale = 4)
    private Long cantidad;

    @NotNull
    @Column(name = "DFAC_CANAPR", nullable = false, precision = 17, scale = 4)
    private BigDecimal canapr;

    @NotNull
    @Column(name = "DFAC_PRECIO", nullable = false, precision = 20, scale = 7)
    private BigDecimal precio;

    @Column(name = "DFAC_DESCUENTO", precision = 5, scale = 2)
    private BigDecimal descuento;

    @NotNull
    @Column(name = "DFAC_TOTAL", nullable = false, precision = 17, scale = 4)
    private BigDecimal total;

    @NotNull
    @Column(name = "DFAC_CANENT", nullable = false, precision = 17, scale = 4)
    private BigDecimal canent;

    @NotNull
    @Column(name = "DFAC_CANDEV", nullable = false, precision = 17, scale = 4)
    private BigDecimal candev;

    @NotNull
    @Column(name = "DFAC_CANRES", nullable = false, precision = 17, scale = 4)
    private BigDecimal canres;

    @NotNull
    @Column(name = "DFAC_DSCITEM", nullable = false, precision = 17, scale = 4)
    private BigDecimal dscitem;

    @NotNull
    @Column(name = "DFAC_TRAITEM", nullable = false, precision = 17, scale = 4)
    private BigDecimal traitem;

    @Column(name = "DFAC_COMBO")
    private Boolean combo;

    @Size(max = 10)
    @Column(name = "CREA_USR", length = 10)
    private String creaUsr;

    @Column(name = "CREA_FECHA")
    private LocalDate creaFecha;

    @Size(max = 10)
    @Column(name = "MOD_USR", length = 10)
    private String modUsr;

    @Column(name = "MOD_FECHA")
    private LocalDate modFecha;

    @NotNull
    @Column(name = "DFAC_IVAITEM", nullable = false, precision = 20, scale = 7)
    private BigDecimal ivaitem;

    @Column(name = "DFAC_GRABAIVA")
    private Boolean grabaiva;

    @Column(name = "DFAC_TEMPERATURA", precision = 5, scale = 2)
    private BigDecimal temperatura;

    @Column(name = "DFAC_GRADO", precision = 5, scale = 2)
    private BigDecimal grado;

    @Column(name = "DFAC_CDIGITADA", precision = 17, scale = 4)
    private BigDecimal cdigitada;

    @Column(name = "DFAC_CAPRDIGITADA", precision = 17, scale = 4)
    private BigDecimal caprdigitada;

    @Column(name = "DFAC_PDIGITADO", precision = 20, scale = 7)
    private BigDecimal pdigitado;

    @Column(name = "DFAC_GASITEM", precision = 17, scale = 4)
    private BigDecimal gasitem;

    @Column(name = "DFAC_ICEITEM", precision = 17, scale = 4)
    private BigDecimal iceitem;

    @Size(max = 20)
    @Column(name = "DFAC_IDAUX", length = 20)
    private String idaux;

    @Column(name = "DFAC_TIPOGASTO")
    private Long tipogasto;

    @Column(name = "DFAC_TRANSACC")
    private Long transacc;

    @Column(name = "DFAC_IMPITEM", precision = 20, scale = 7)
    private BigDecimal impitem;

    @Column(name = "DFAC_TOTAL1", precision = 17, scale = 4)
    private BigDecimal total1;

    @Column(name = "DFAC_PRO_COMBO")
    private Long proCombo;

    @Column(name = "DFAC_KILOMETRAJE", precision = 17, scale = 4)
    private BigDecimal kilometraje;

    @Column(name = "DFAC_LIQUIDA")
    private Boolean liquida;

    @Column(name = "DFAC_CONCE_IMP")
    private Long conceImp;

    @Column(name = "DFAC_TIPO_LIQ_IMP")
    private Long tipoLiqImp;

    @Column(name = "DFAC_GRUPO_IMP")
    private Long grupoImp;

    @Column(name = "DFAC_PROCEDENCIA")
    private Long procedencia;

    @Column(name = "DFAC_SECUENCIA_PED")
    private Long secuenciaPed;

    @Column(name = "DFAC_CANBAJA", precision = 17, scale = 4)
    private BigDecimal canbaja;

    @Column(name = "DFAC_ESPECIFICACION")
    private Long especificacion;

    @Size(max = 500)
    @Column(name = "DFAC_OBSERVACION", length = 500)
    private String observacion;

    @Column(name = "DFAC_VERSION")
    private Long version;

    @ColumnDefault("0")
    @Column(name = "DFAC_APROB_CRI")
    private Boolean aprobCri;

    @Column(name = "DFAC_BITACORA")
    private Long bitacora;

    @Size(max = 50)
    @Column(name = "DFAC_LOTE", length = 50)
    private String lote;

    @Column(name = "DFAC_PRECIO_REAL")
    private Long precioReal;

    @Column(name = "DFAC_CANT_PCL")
    private Long cantPcl;

    @Column(name = "DFAC_CANT_ING")
    private Long cantIng;

    @Column(name = "DFAC_SECCION")
    private Long seccion;

    @Column(name = "DFAC_COMPONENTE")
    private Long componente;

    @Column(name = "DFAC_CCO_FECHA")
    private LocalDate ccoFecha;

    @Column(name = "DFAC_CCO_ESTADO")
    private Boolean ccoEstado;

    @Column(name = "DFAC_CCO_TIPODOC")
    private Long ccoTipodoc;

    @Column(name = "DFAC_OBSEQUIO")
    private Boolean obsequio;

    @Column(name = "DFAC_SEC_IMP")
    private Long secImp;

    @Column(name = "DFAC_DSCITEM_LIN", precision = 17, scale = 4)
    private BigDecimal dscitemLin;

    @Column(name = "DFAC_CANTINI", precision = 17, scale = 4)
    private BigDecimal cantini;

    @Column(name = "DFAC_DESPACHAR")
    private Boolean despachar;

    @Column(name = "DFAC_HOJA")
    private Long hoja;

    @Column(name = "DFAC_FECHA_DESPACHO")
    private LocalDate fechaDespacho;

    @Column(name = "DFAC_FECHA_EMPAQUE")
    private LocalDate fechaEmpaque;

    @Size(max = 100)
    @Column(name = "DFAC_CAJAEMP", length = 100)
    private String cajaemp;

    @Column(name = "DFAC_PRODUCTO_TEMP")
    private Long productTemp;

    @Column(name = "DFAC_CANT_BULTO")
    private BigDecimal cantBulto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "DFAC_CLIENTE", referencedColumnName = "CLI_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "DFAC_EMPRESA", referencedColumnName = "CLI_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "DFAC_UDIGITADA", referencedColumnName = "FAC_UNIDAD", insertable = false, updatable = false),
            @JoinColumn(name = "DFAC_PRODUCTO", referencedColumnName = "FAC_PRODUCTO", insertable = false, updatable = false),
            @JoinColumn(name = "DFAC_EMPRESA", referencedColumnName = "FAC_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Factor factor;

    @Column(name = "DFAC_PRODUCTO")
    private Long dfacProducto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "DFAC_PRODUCTO", referencedColumnName = "PRO_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "DFAC_EMPRESA", referencedColumnName = "PRO_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "DFAC_LISTAPRE", referencedColumnName = "LPR_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "DFAC_EMPRESA", referencedColumnName = "LPR_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private ListaPre listaPre;

    @Column(name = "DFAC_BODEGA", nullable = false)
    private Long dfacBodega;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "DFAC_BODEGA", referencedColumnName = "BOD_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "DFAC_EMPRESA", referencedColumnName = "BOD_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Bodega bodega;

    @Column(name = "DFAC_CFAC_COMPROBA")
    private BigInteger facComproba;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "DFAC_CFAC_COMPROBA", referencedColumnName = "CCO_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "DFAC_EMPRESA", referencedColumnName = "CCO_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Ccomproba ccomproba;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "DFAC_FACTURA", referencedColumnName = "CCO_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "DFAC_EMPRESA", referencedColumnName = "CCO_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Ccomproba ccomproba1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "DFAC_UDIGITADA", referencedColumnName = "UMD_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "DFAC_EMPRESA", referencedColumnName = "UMD_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Umedida umedida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "DFAC_AGENTE", referencedColumnName = "AGE_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "DFAC_EMPRESA", referencedColumnName = "AGE_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Agente agente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "DFAC_ALMACEN", referencedColumnName = "ALM_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "DFAC_EMPRESA", referencedColumnName = "ALM_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Almacen almacen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "DFAC_CENTRO", referencedColumnName = "CEN_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "DFAC_EMPRESA", referencedColumnName = "CEN_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Centro centro;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "DFAC_PRODUCTO_TEMP", referencedColumnName = "PRO_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "DFAC_EMPRESA", referencedColumnName = "PRO_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private ProductoTemp productoTemp;
}
