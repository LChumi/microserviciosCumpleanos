package com.cumpleanos.models.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Table(name = "CCOMPROBA")
@Data
public class Ccomproba {

    @Id
    @Column(name = "CCO_CODIGO")
    @Setter(AccessLevel.NONE)
    private BigInteger id;

    @Column(name = "CCO_EMPRESA")
    private Long empresa;

    @Column(name = "CCO_PERIODO")
    private Long periodo;

    @Column(name = "CCO_SIGLA")
    private Long sigla;

    @Column(name = "CCO_ALMACEN")
    private Long almacen;

    @Column(name = "CCO_SERIE")
    private Long serie;

    @Column(name = "CCO_NUMERO")
    private Long numero;

    @Column(name = "CCO_DOCTRAN")
    private String doctran;

    @Column(name = "CCO_TIPODOC")
    private Long tipoDoc;

    @Column(name = "CCO_FECHA")
    private LocalDate fecha;

    @Column(name = "CCO_CONCEPTO")
    private String concepto;

    @Column(name = "CCO_MODULO")
    private Long modulo;

    @Column(name = "CCO_NOCONTABLE")
    private Long noContable;

    @Column(name = "CCO_ESTADO")
    private Long estado;

    @Column(name = "CCO_DESCUADRE")
    private Long descuadre;

    @Column(name = "CCO_ADESTINO")
    private Long aDestino;

    @Column(name = "CCO_PVENTA")
    private Long pventa;

    @Column(name = "CCO_CENTRO")
    private Long centro;

    @Column(name = "CCO_TIPO_CAMBIO")
    private Long tipoCambio;

    @Column(name = "CCO_TCLIPRO")
    private Long tclipro;

    @Column(name = "CCO_CODLIPRO")
    private Long codlipro;

    @Column(name = "CCO_AGENTE")
    private Long agente;

    @Column(name = "CCO_CUENTA")
    private Long cuenta;

    @Column(name = "CCO_TRANSACC")
    private Long transacc;

    @Column(name = "CCO_CODCLIPRO1")
    private Long codclipro1;

    @Column(name = "CCO_CIE_COMPROBA")
    private Long cieComproba;

    @Column(name = "CCO_REF_COMPROBA")
    private Long refComproba;

    @Column(name = "CCO_ANULADO")
    private Long anulado;

    @Column(name = "CCO_ANU_COMPROBA")
    private Long anuComproba;

    @Column(name = "CCO_AUT_TIPO")
    private Long autTipo;

    @Column(name = "CCO_NIVEL_APROB")
    private Long nivelAprob;

    @Column(name = "CCO_BODEGA")
    private Long bodega;

    @Column(name = "CCO_DIA")
    private Long dia;

    @Column(name = "CCO_MES")
    private Long mes;

    @Column(name = "CCO_ANIO")
    private Long anio;

    @Column(name = "CCO_ESTADO_LIQ")
    private Long estadoLiq;

    @Column(name = "CCO_CATCLIENTE")
    private Long catCliente;

    @Column(name ="CCO_CHOFER")
    private Long chofer;

    @Column(name = "CCO_CADAGENTE")
    private Long cadagente;

    @Column(name = "CCO_TIPOGES")
    private Long tipoGes;

    @Column(name = "CCO_VAL_COMPROBA")
    private Long valComproba;

    @Column(name = "CCO_PUENTE")
    private Long puente;

    @Column(name = "CCO_NOMFECHA")
    private Long nomfecha;

    @Column(name = "CCO_TIPOCALCU")
    private Long tipoCalcu;

    @Column(name = "CCO_EVENTO")
    private Long evento;

    @Column(name = "CCO_RUTA")
    private Long ruta;

    @Column(name = "CCO_IVA")
    private Long iva;

    @Column(name = "CCO_NODESPACHO")
    private Long noDespacho;
}