package com.cumpleanos.assist.persistence.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Immutable;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
@Entity
@Immutable
@Table(name = "COM_IMP_V1")
public class ComImpV1 {

    @Id
    @Column(name = "CCO_CODIGO")
    private BigInteger cco;

    @Column(name = "CCO_EMPRESA", nullable = false)
    private Long ccoEmpresa;

    @Column(name = "CCO_DSP_COMPROBANTE", length = 4000)
    private String ccoDspComprobante;

    @Column(name = "CCO_PERIODO", nullable = false)
    private Short ccoPeriodo;

    @Column(name = "CCO_FECHA", nullable = false)
    private LocalDate ccoFecha;

    @Column(name = "CCO_CONCEPTO", nullable = false, length = 300)
    private String ccoConcepto;

    @Column(name = "CCO_TIPODOC", nullable = false)
    private Long ccoTipodoc;

    @Column(name = "CCO_NUMERO", nullable = false)
    private Long ccoNumero;

    @Column(name = "CCO_SIGLA", nullable = false)
    private Long ccoSigla;

    @Column(name = "CCO_SERIE", nullable = false)
    private Long ccoSerie;

    @Column(name = "CCO_ALMACEN")
    private Long ccoAlmacen;

    @Column(name = "CREA_USR", length = 10)
    private String creaUsr;

    @Column(name = "CREA_FECHA")
    private LocalDate creaFecha;

    @Column(name = "MOD_USR", length = 10)
    private String modUsr;

    @Column(name = "MOD_FECHA")
    private LocalDate modFecha;

    @Column(name = "CCO_IMP_OBSERVACIONES", length = 100)
    private String ccoImpObservaciones;

    @Column(name = "IPT_COD_ESTADO")
    private Boolean iptCodEstado;

    @Column(name = "IPT_ESTADO", length = 12)
    private String iptEstado;

    @Column(name = "CLI_ID", nullable = false, length = 10)
    private String cliId;

    @Column(name = "CLI_NOMBRE", nullable = false, length = 100)
    private String cliNombre;
}