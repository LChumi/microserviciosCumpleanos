package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.CcomprobaId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "CCOMPROBA")
@Data
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CCO_MODULO")
    private Modulo modulo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CCO_CADAGENTE", referencedColumnName = "CAD_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CCO_EMPRESA", referencedColumnName = "CAD_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private CadAgente cadAgente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CCO_CATCLIENTE", referencedColumnName = "CAT_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CCO_EMPRESA", referencedColumnName = "CAT_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private CatCliente catCliente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CCO_ANU_COMPROBA", referencedColumnName = "CCO_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CCO_EMPRESA", referencedColumnName = "CCO_EMPRESA")
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Ccomproba anulado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CCO_VAL_COMPROBA", referencedColumnName = "CCO_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CCO_EMPRESA", referencedColumnName = "CCO_EMPRESA")
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Ccomproba ccomproba;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CCO_CIE_COMPROBA", referencedColumnName = "CCO_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CCO_EMPRESA", referencedColumnName = "CCO_EMPRESA")
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Ccomproba cierre;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CCO_REF_COMPROBA", referencedColumnName = "CCO_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CCO_EMPRESA", referencedColumnName = "CCO_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Ccomproba referencia;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CCO_AGENTE" , referencedColumnName = "AGE_CODIGO",insertable = false, updatable = false),
            @JoinColumn(name = "CCO_EMPRESA", referencedColumnName = "AGE_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Agente agente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CCO_CHOFER" , referencedColumnName = "AGE_CODIGO",insertable = false, updatable = false),
            @JoinColumn(name = "CCO_EMPRESA", referencedColumnName = "AGE_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Agente chofer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CCO_ADESTINO", referencedColumnName = "ALM_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CCO_EMPRESA", referencedColumnName = "ALM_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Almacen destino;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CCO_ALMACEN", referencedColumnName = "ALM_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CCO_EMPRESA", referencedColumnName = "ALM_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Almacen almacen;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CCO_CENTRO", referencedColumnName = "CEN_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CCO_EMPRESA", referencedColumnName = "CEN_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Centro centro;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CCO_CODCLIPRO", referencedColumnName = "CLI_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CCO_EMPRESA", referencedColumnName = "CLI_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CCO_CODCLIPRO1", referencedColumnName = "CLI_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CCO_EMPRESA", referencedColumnName = "CLI_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Cliente clientePro;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CCO_PVENTA", referencedColumnName = "PVE_SECUENCIA", insertable = false, updatable = false),
            @JoinColumn(name = "CCO_ALMACEN", referencedColumnName = "PVE_ALMACEN", insertable = false, updatable = false),
            @JoinColumn(name = "CCO_EMPRESA", referencedColumnName = "PVE_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private PuntoVenta puntoVenta;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CCO_BODEGA", referencedColumnName = "BOD_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CCO_EMPRESA", referencedColumnName = "BOD_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Bodega bodega;

    @OneToMany(mappedBy = "ccomproba")
    private Set<Acceso> accesos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "ccomproba")
    private Set<SriDocEleEmi> sriDocEleEmi = new LinkedHashSet<>();

    @OneToMany(mappedBy = "ccomproba")
    private Set<CcomFac> ccomFacs = new LinkedHashSet<>();

    @OneToMany(mappedBy = "pedido")
    private Set<CcomFac> pedidos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "recibo")
    private Set<CcomFac> recibos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "produccion")
    private Set<CcomFac> producciones = new LinkedHashSet<>();

    @OneToMany(mappedBy = "anulado")
    private Set<Ccomproba> anulados = new LinkedHashSet<>();

    @OneToMany(mappedBy = "ccomproba")
    private Set<Ccomproba> ccomprobas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "cierre")
    private Set<Ccomproba> cierres = new LinkedHashSet<>();

    @OneToMany(mappedBy = "referencia")
    private Set<Ccomproba> referencias = new LinkedHashSet<>();

}
