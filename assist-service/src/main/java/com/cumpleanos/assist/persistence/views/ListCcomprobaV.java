package com.cumpleanos.assist.persistence.views;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Immutable
@Table(name = "LIST_CCOMPROBA_V")
@Data
public class ListCcomprobaV {

    @Id
    @Column(name = "CCO_CODIGO", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigInteger ccoCodigo;

    @Column(name = "DSP_COMPROBA", length = 50)
    private String dspComproba;

    @Column(name = "CCO_FECHA", nullable = false)
    private LocalDate fecha;

    @Column(name = "CCO_EMPRESA", nullable = false)
    private Long empresa;

    @Column(name = "CCO_PERIODO", nullable = false)
    private Short periodo;

    @Column(name = "CCO_SIGLA", nullable = false)
    private Long sigla;

    @Column(name = "CCO_ALMACEN")
    private Long almacen;

    @Column(name = "CCO_SERIE", nullable = false)
    private Long serie;

    @Column(name = "CCO_NUMERO", nullable = false)
    private Long numero;

    @Column(name = "CCO_DOCTRAN", length = 30)
    private String doctran;

    @Column(name = "CCO_TIPODOC", nullable = false)
    private Long tipodoc;

    @Column(name = "CCO_CONCEPTO", nullable = false, length = 300)
    private String concepto;

    @Column(name = "CCO_MODULO", nullable = false)
    private Long modulo;

    @Column(name = "CCO_NOCONTABLE", nullable = false)
    private Boolean nocontable = false;

    @Column(name = "CCO_ESTADO", nullable = false)
    private Long estado;

    @Column(name = "CCO_DESCUADRE", nullable = false)
    private Boolean descuadre = false;

    @Column(name = "CCO_ADESTINO", nullable = false)
    private Long adestino;

    @Column(name = "CCO_PVENTA")
    private Long pventa;

    @Column(name = "CCO_CENTRO")
    private Long centro;

    @Column(name = "CCO_TIPO_CAMBIO", nullable = false, precision = 17, scale = 4)
    private BigDecimal tipoCambio;

    @Column(name = "CCO_TCLIPRO")
    private Boolean tclipro;

    @Column(name = "CCO_CODCLIPRO")
    private Long codclipro;

    @Column(name = "CCO_AGENTE")
    private Long agente;

    @Column(name = "CCO_CUENTA")
    private Long cuenta;

    @Column(name = "CCO_TRANSACC")
    private Long transacc;

    @Column(name = "CCO_CODCLIPRO1")
    private Long codclipro1;

    @Column(name = "CCO_CIE_COMPROBA")
    private BigInteger cieComproba;

    @Column(name = "CCO_REF_COMPROBA")
    private BigInteger refComproba;

    @Column(name = "CCO_ANULADO", nullable = false)
    private Long anulado;

    @Column(name = "CCO_ANU_COMPROBA")
    private BigInteger anuComproba;

    @Column(name = "CCO_AUT_TIPO")
    private Boolean autTipo;

    @Column(name = "CCO_NIVEL_APROB")
    private Short nivelAprob;

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
    private BigInteger valComproba;
}