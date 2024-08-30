package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.CcomprobaId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

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

    @OneToMany(mappedBy = "ccomproba")
    private Set<Acceso> accesos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "ccomproba")
    private Set<SriDocEleEmi> sriDocEleEmi = new LinkedHashSet<>();
}
