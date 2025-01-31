package com.cumpleanos.assist.persistence.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Immutable
@Table(name = "LIST_CCOMPROBA_V")
@Data
public class ListCcomprobaV {

    @Id
    @Column(name = "CCO_CODIGO", nullable = false)
    private Long ccoCodigo;

    @Column(name = "DSP_COMPROBA", length = 50)
    private String dspComproba;

    @Column(name = "CCO_FECHA", nullable = false)
    private LocalDate ccoFecha;

    @Column(name = "CCO_EMPRESA", nullable = false)
    private Long ccoEmpresa;

    @Column(name = "CCO_PERIODO", nullable = false)
    private Short ccoPeriodo;

    @Column(name = "CCO_SIGLA", nullable = false)
    private Long ccoSigla;

    @Column(name = "CCO_ALMACEN")
    private Long ccoAlmacen;

    @Column(name = "CCO_SERIE", nullable = false)
    private Long ccoSerie;

    @Column(name = "CCO_NUMERO", nullable = false)
    private Long ccoNumero;

    @Column(name = "CCO_DOCTRAN", length = 30)
    private String ccoDoctran;

    @Column(name = "CCO_TIPODOC", nullable = false)
    private Long ccoTipodoc;

    @Column(name = "CCO_CONCEPTO", nullable = false, length = 300)
    private String ccoConcepto;

    @Column(name = "CCO_MODULO", nullable = false)
    private Long ccoModulo;

    @Column(name = "CCO_NOCONTABLE", nullable = false)
    private Boolean ccoNocontable = false;

    @Column(name = "CCO_ESTADO", nullable = false)
    private Boolean ccoEstado = false;

    @Column(name = "CCO_DESCUADRE", nullable = false)
    private Boolean ccoDescuadre = false;

    @Column(name = "CCO_ADESTINO", nullable = false)
    private Long ccoAdestino;

    @Column(name = "CCO_PVENTA")
    private Long ccoPventa;

    @Column(name = "CCO_CENTRO")
    private Long ccoCentro;

    @Column(name = "CCO_TIPO_CAMBIO", nullable = false, precision = 17, scale = 4)
    private BigDecimal ccoTipoCambio;

    @Column(name = "CCO_TCLIPRO")
    private Boolean ccoTclipro;

    @Column(name = "CCO_CODCLIPRO")
    private Long ccoCodclipro;

    @Column(name = "CCO_AGENTE")
    private Long ccoAgente;

    @Column(name = "CCO_CUENTA")
    private Long ccoCuenta;

    @Column(name = "CCO_TRANSACC")
    private Long ccoTransacc;

    @Column(name = "CCO_CODCLIPRO1")
    private Long ccoCodclipro1;

    @Column(name = "CCO_CIE_COMPROBA")
    private Long ccoCieComproba;

    @Column(name = "CCO_REF_COMPROBA")
    private Long ccoRefComproba;

    @Column(name = "CCO_ANULADO", nullable = false)
    private Boolean ccoAnulado = false;

    @Column(name = "CCO_ANU_COMPROBA")
    private Long ccoAnuComproba;

    @Column(name = "CCO_AUT_TIPO")
    private Boolean ccoAutTipo;

    @Column(name = "CCO_NIVEL_APROB")
    private Short ccoNivelAprob;

    @Column(name = "TPD_ID", nullable = false, length = 10)
    private String tpdId;

    @Column(name = "TPD_NOMBRE", nullable = false, length = 100)
    private String tpdNombre;

    @Column(name = "MOD_CODIGO", nullable = false)
    private Long modCodigo;

    @Column(name = "MOD_ID", nullable = false, length = 10)
    private String modId;

    @Column(name = "MOD_NOMBRE", nullable = false, length = 100)
    private String modNombre;

    @Column(name = "CTI_ID", nullable = false, length = 10)
    private String ctiId;

    @Column(name = "CTI_NOMBRE", nullable = false, length = 100)
    private String ctiNombre;

    @Column(name = "CTI_TIPO", nullable = false)
    private Boolean ctiTipo = false;

    @Column(name = "CTI_AUTORIZA", nullable = false)
    private Boolean ctiAutoriza = false;

    @Column(name = "ALM_NOMBRE", nullable = false, length = 100)
    private String almNombre;

    @Column(name = "ALM_ID", nullable = false, length = 10)
    private String almId;

    @Column(name = "CCO_FECHAFIN")
    private LocalDate fechafin;

    @Column(name = "CCO_MES")
    private Short mes;

    @Column(name = "CLI_ID", length = 10)
    private String cliId;

    @Column(name = "CCO_NOMBRE", length = 100)
    private String ccoNombre;

    @Column(name = "CCO_VAL_COMPROBA")
    private Long valComproba;
}
