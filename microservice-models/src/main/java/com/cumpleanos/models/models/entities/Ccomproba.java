package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.CcomprobaId;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "CCOMPROBA", indexes = {
        @Index(name = "CCOMPROBA_UDX2", columnList = "CCO_NUMERO, CCO_SERIE, CCO_ALMACEN, CCO_SIGLA, CCO_PERIODO, CCO_EMPRESA", unique = true),
        @Index(name = "COMPROBA_NIDX7", columnList = "CCO_ALMACEN, CCO_EMPRESA"),
        @Index(name = "CCOMPROBA_NIDX16", columnList = "CCO_DOCTRAN, CCO_EMPRESA"),
        @Index(name = "CCOMPROBA_IDX7", columnList = "CCO_TIPODOC, CCO_EMPRESA"),
        @Index(name = "CCOMPROBA_OIDX3", columnList = "CCO_TIPODOC, CCO_ESTADO, CCO_EMPRESA"),
        @Index(name = "CCOMPROBA_NIDX19", columnList = "CCO_TIPODOC, CCO_ESTADO, CCO_DESCUADRE, CCO_EMPRESA"),
        @Index(name = "COMPROBA_NIDX13", columnList = "CCO_TIPODOC"),
        @Index(name = "CCOMPROBA_NIDX15", columnList = "CCO_MODULO"),
        @Index(name = "CCOMPROBA_IDX5", columnList = "CCO_NOCONTABLE, CCO_EMPRESA"),
        @Index(name = "CCOMPROBA_IDX4", columnList = "CCO_ESTADO, CCO_EMPRESA"),
        @Index(name = "COMPROBA_NIDX20", columnList = "CCO_ESTADO, CCO_TIPODOC, CCO_CODIGO, CCO_EMPRESA"),
        @Index(name = "COMPROBA_NIDX6", columnList = "CCO_ADESTINO, CCO_EMPRESA"),
        @Index(name = "COMPROBA_NIDX2", columnList = "CCO_PVENTA, CCO_ALMACEN, CCO_EMPRESA"),
        @Index(name = "COMPROBA_NIDX10", columnList = "CCO_CENTRO, CCO_EMPRESA"),
        @Index(name = "CCOMPROBA_IDX8", columnList = "CCO_CODCLIPRO, CCO_EMPRESA"),
        @Index(name = "CCOMPROBA_UDX3", columnList = "CCO_AGENTE, CCO_EMPRESA"),
        @Index(name = "CCOMPROBA_OIDX4", columnList = "CCO_AGENTE, CCO_TIPODOC, CCO_EMPRESA, CCO_ESTADO, CCO_FECHA"),
        @Index(name = "CCOMPROBA_NIDX20", columnList = "CCO_CUENTA, CCO_EMPRESA"),
        @Index(name = "COMPROBA_NIDX1", columnList = "CCO_CODCLIPRO1, CCO_EMPRESA"),
        @Index(name = "CCOMPROBA_IDX1", columnList = "CCO_CIE_COMPROBA, CCO_EMPRESA"),
        @Index(name = "COMPROBA_IDX3", columnList = "CCO_REF_COMPROBA, CCO_EMPRESA"),
        @Index(name = "COMPROBA_NIDX8", columnList = "CCO_ANU_COMPROBA, CCO_EMPRESA"),
        @Index(name = "COMPROBA_NIDX3", columnList = "CCO_BODEGA, CCO_EMPRESA"),
        @Index(name = "CCOMPROBA_UDX1", columnList = "CCO_DIA, CCO_MES, CCO_ANIO, CCO_EMPRESA"),
        @Index(name = "CCOMPROBA_IDX6", columnList = "CCO_MES, CCO_EMPRESA"),
        @Index(name = "COMPROBA_NIDX9", columnList = "CCO_CATCLIENTE, CCO_EMPRESA"),
        @Index(name = "COMPROBA_NIDX11", columnList = "CCO_CHOFER, CCO_EMPRESA"),
        @Index(name = "COMPROBA_NIDX4", columnList = "CCO_CADAGENTE, CCO_EMPRESA"),
        @Index(name = "COMPROBA_NIDX14", columnList = "CCO_TIPOGES, CCO_EMPRESA"),
        @Index(name = "CCOMPROBA_IDX2", columnList = "CCO_VAL_COMPROBA, CCO_EMPRESA"),
        @Index(name = "COMPROBA_NIDX12", columnList = "CCO_NOMFECHA, CCO_EMPRESA"),
        @Index(name = "COMPROBA_NIDX5", columnList = "CCO_EVENTO, CCO_EMPRESA"),
        @Index(name = "CCOMPROBA_NIDX18", columnList = "CCO_RUTA, CCO_EMPRESA")
})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
        "modulo", "cadAgente", "catCliente", "anulado", "ccomproba", "cierre", "referencia", "agente", "chofer", "destino", "almacen", "centro", "clientePro", "puntoVenta", "bodega", "accesos", "sriDocEleEmi", "ccomFacs", "pedidos", "recibos", "producciones", "anulados", "ccomprobas", "cierres", "referencias", "dfacturas", "dfacturas1", "totales"
})
public class Ccomproba {

    @EmbeddedId
    private CcomprobaId id;

    @NotNull
    @Column(name = "CCO_PERIODO", nullable = false)
    private Short ccoPeriodo;

    @NotNull
    @Column(name = "CCO_SIGLA", nullable = false)
    private Long ccoSigla;

    @NotNull
    @Column(name = "CCO_SERIE", nullable = false)
    private Long ccoSerie;

    @NotNull
    @Column(name = "CCO_NUMERO", nullable = false)
    private Long ccoNumero;

    @Size(max = 30)
    @Column(name = "CCO_DOCTRAN", length = 30)
    private String ccoDoctran;

    @NotNull
    @Column(name = "CCO_FECHA", nullable = false)
    private LocalDate ccoFecha;

    @Size(max = 300)
    @NotNull
    @Column(name = "CCO_CONCEPTO", nullable = false, length = 300)
    private String ccoConcepto;

    @NotNull
    @Column(name = "CCO_TIPO_CAMBIO", nullable = false, precision = 17, scale = 4)
    private BigDecimal ccoTipoCambio;

    @Column(name = "CCO_TCLIPRO")
    private Boolean ccoTclipro;

    @Column(name = "CCO_CUENTA")
    private Long ccoCuenta;

    @Column(name = "CCO_TRANSACC")
    private Long ccoTransacc;

    @NotNull
    @Column(name = "CCO_ANULADO", nullable = false)
    private Boolean ccoAnulado = false;

    @Column(name = "CCO_AUT_TIPO")
    private Boolean ccoAutTipo;

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

    @Column(name = "CCO_NIVEL_APROB")
    private Short ccoNivelAprob;

    @Column(name = "CCO_FECHAFIN")
    private LocalDate ccoFechafin;

    @Column(name = "CCO_DIA")
    private Short ccoDia;

    @Column(name = "CCO_MES")
    private Short ccoMes;

    @Column(name = "CCO_ANIO")
    private Short ccoAnio;

    @Column(name = "CCO_ESTADO_LIQ")
    private Short ccoEstadoLiq;

    @Column(name = "CCO_PUENTE")
    private Boolean ccoPuente;

    @Column(name = "CCO_TIPOCALCU")
    private Boolean ccoTipocalcu;

    @Column(name = "CCO_EVENTO")
    private Long ccoEvento;

    @Column(name = "CCO_IVA")
    private Boolean ccoIva;

    @Column(name = "CCO_NODESPACHO")
    private Boolean ccoNodespacho;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CCO_MODULO", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Modulo modulo;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CCO_CADAGENTE", referencedColumnName = "CAD_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CCO_EMPRESA", referencedColumnName = "CAD_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private CadAgente cadAgente;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CCO_CATCLIENTE", referencedColumnName = "CAT_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CCO_EMPRESA", referencedColumnName = "CAT_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private CatCliente catCliente;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CCO_ANU_COMPROBA", referencedColumnName = "CCO_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CCO_EMPRESA", referencedColumnName = "CCO_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Ccomproba anulado;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CCO_VAL_COMPROBA", referencedColumnName = "CCO_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CCO_EMPRESA", referencedColumnName = "CCO_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Ccomproba ccomproba;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CCO_CIE_COMPROBA", referencedColumnName = "CCO_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CCO_EMPRESA", referencedColumnName = "CCO_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Ccomproba cierre;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CCO_REF_COMPROBA", referencedColumnName = "CCO_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CCO_EMPRESA", referencedColumnName = "CCO_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Ccomproba referencia;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CCO_AGENTE" , referencedColumnName = "AGE_CODIGO",insertable = false, updatable = false),
            @JoinColumn(name = "CCO_EMPRESA", referencedColumnName = "AGE_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Agente agente;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CCO_CHOFER" , referencedColumnName = "AGE_CODIGO",insertable = false, updatable = false),
            @JoinColumn(name = "CCO_EMPRESA", referencedColumnName = "AGE_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Agente chofer;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CCO_ADESTINO", referencedColumnName = "ALM_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CCO_EMPRESA", referencedColumnName = "ALM_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Almacen destino;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CCO_ALMACEN", referencedColumnName = "ALM_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CCO_EMPRESA", referencedColumnName = "ALM_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Almacen almacen;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CCO_CENTRO", referencedColumnName = "CEN_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CCO_EMPRESA", referencedColumnName = "CEN_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Centro centro;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CCO_CODCLIPRO", referencedColumnName = "CLI_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CCO_EMPRESA", referencedColumnName = "CLI_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Cliente cliente;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CCO_CODCLIPRO1", referencedColumnName = "CLI_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CCO_EMPRESA", referencedColumnName = "CLI_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Cliente clientePro;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CCO_PVENTA", referencedColumnName = "PVE_SECUENCIA", insertable = false, updatable = false),
            @JoinColumn(name = "CCO_ALMACEN", referencedColumnName = "PVE_ALMACEN", insertable = false, updatable = false),
            @JoinColumn(name = "CCO_EMPRESA", referencedColumnName = "PVE_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private PuntoVenta puntoVenta;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CCO_BODEGA", referencedColumnName = "BOD_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CCO_EMPRESA", referencedColumnName = "BOD_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Bodega bodega;

    @JsonBackReference
    @OneToMany(mappedBy = "ccomproba", fetch = FetchType.LAZY)
    private Set<Acceso> accesos = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "ccomproba", fetch = FetchType.LAZY)
    private Set<SriDocEleEmi> sriDocEleEmi = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "ccomproba", fetch = FetchType.LAZY)
    private Set<CcomFac> ccomFacs = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY)
    private Set<CcomFac> pedidos = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "recibo", fetch = FetchType.LAZY)
    private Set<CcomFac> recibos = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "produccion", fetch = FetchType.LAZY)
    private Set<CcomFac> producciones = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "anulado", fetch = FetchType.LAZY)
    private Set<Ccomproba> anulados = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "ccomproba", fetch = FetchType.LAZY)
    private Set<Ccomproba> ccomprobas = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "cierre", fetch = FetchType.LAZY)
    private Set<Ccomproba> cierres = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "referencia", fetch = FetchType.LAZY)
    private Set<Ccomproba> referencias = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "ccomproba", fetch = FetchType.LAZY)
    private Set<Dfactura> dfacturas = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "ccomproba1", fetch = FetchType.LAZY)
    private Set<Dfactura> dfacturas1 = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "ccomproba", fetch = FetchType.LAZY)
    private Set<Total> totales = new LinkedHashSet<>();
}
