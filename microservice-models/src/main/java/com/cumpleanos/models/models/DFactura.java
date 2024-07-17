package com.cumpleanos.models.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Table(name = "DFACTURA")
@Data
public class DFactura {

    @Id
    @Column(name = "DFAC_CFAC_COMPROBA")
    @Setter(AccessLevel.NONE)
    private BigDecimal id;

    @Column(name = "DFAC_EMPRESA")
    private Long empresa;

    @Column(name = "DFAC_SECUENCIA")
    private Long secuencia;

    @Column(name = "DFAC_PRODUCTO")
    private Long producto;

    @Column(name = "DFAC_CUENTA")
    private Long cuenta;

    @Column(name = "DFAC_ACTIVO")
    private Long activo;

    @Column(name = "DFAC_CATPRODUCTO")
    private Long catProducto;

    @Column(name = "DFAC_CANTIDAD")
    private BigDecimal cantidad;

    @Column(name = "DFAC_CANAPR")
    private BigDecimal canapr;

    @Column(name = "DFAC_PRECIO")
    private BigDecimal precio;

    @Column(name = "DFAC_DESCUENTO")
    private BigDecimal descuento;

    @Column(name = "DFAC_BODEGA")
    private Long bodega;

    @Column(name = "DFAC_TOTAL")
    private BigDecimal total;

    @Column(name = "DFAC_CANENT")
    private BigDecimal canEnt;

    @Column(name = "DFAC_CANDEV")
    private BigDecimal canDev;

    @Column(name = "DFAC_CANRES")
    private BigDecimal canRes;

    @Column(name = "DFAC_DSCITEM")
    private BigDecimal dscItem;

    @Column(name = "DFAC_TRAITEM")
    private BigDecimal traItem;

    @Column(name = "DFAC_COMBO")
    private Long combo;

    @Column(name = "DFAC_IVAITEM")
    private BigDecimal ivaItem;

    @Column(name = "DFAC_GRABAIVA")
    private BigDecimal grabaIva;

    @Column(name = "DFAC_TEMPERATURA")
    private BigDecimal temperatura;

    @Column(name = "DFAC_GRADO")
    private BigDecimal grado;

    @Column(name = "DFAC_UDIGITADA")
    private Long uDigitada;

    @Column(name = "DFAC_CDIGITADA")
    private Long cDigitada;

    @Column(name = "DFAC_CENTRO")
    private Long centro;

    @Column(name = "DFAC_CAPRDIGITADA")
    private BigDecimal caprDigitada;

    @Column(name = "DFAC_PDIGITADO")
    private BigDecimal pDigitada;

    @Column(name = "DFAC_GASITEM")
    private BigDecimal gasItem;

    @Column(name = "DFAC_ICEITEM")
    private BigDecimal iceItem;

    @Column(name = "DFAC_TLIQEVENTO")
    private Long tliqEvento;

    @Column(name = "DFAC_IDAUX")
    private String idaux;

    @Column(name = "DFAC_CLIENTE")
    private Long cliente;

    @Column(name = "DFAC_AGENTE")
    private Long agente;

    @Column(name = "DFAC_TIPOGASTO")
    private Long tipoGasto;

    @Column(name = "DFAC_TRANSACC")
    private Long transacc;

    @Column(name = "DFAC_ALMACEN")
    private Long almacen;

    @Column(name = "DFAC_IMPITEM")
    private BigDecimal impItem;

    @Column(name = "DFAC_DDO_COMPROBA")
    private BigInteger ddoComproba;

    @Column(name = "DFAC_DDO_DOCTRAN")
    private String ddoDoctran;

    @Column(name = "DFAC_DDO_PAGO")
    private Long ddoPago;

    @Column(name = "DFAC_DDO_TRANSACC")
    private Long ddoTransacc;

    @Column(name = "DFAC_TOTAL1")
    private BigDecimal total1;

    @Column(name = "DFAC_PRO_COMBO")
    private Long proCombo;

    @Column(name = "DFAC_KILOMETRAJE")
    private BigDecimal kilometraje;

    @Column(name = "DFAC_GASTO_VEHICULO")
    private Long gastoVehiculo;

    @Column(name = "DFAC_LIQUIDA")
    private Long liquida;

    @Column(name = "DFAC_PEDIDO_IMP")
    private  Long pedidoImp;

    @Column(name = "DFAC_CONCE_IMP")
    private Long conceImp;

    @Column(name = "DFAC_TIPO_LIQ")
    private Long tipoLiq;

    @Column(name = "DFAC_GRUPO_IMP")
    private Long grupoImp;

    @Column(name = "DFAC_FACTURA")
    private BigInteger factura;

    @Column(name = "DFAC_PROCEDENCIA")
    private Long procedencia;

    @Column(name = "DFAC_MODELO_PRODCUTO")
    private Long modeloProduto;

    @Column(name = "DFAC_SECUENCIA_PED")
    private Long secuenciaPed;

    @Column(name = "DFAC_CANBAJA")
    private BigDecimal canBaja;

    @Column(name = "DFAC_ESPECIFICACION")
    private Long especificacion;

    @Column(name = "DFAC_OBSERVACION")
    private String observacion;

    @Column(name = "DFAC_VERSION")
    private Long version;

    @Column(name = "DFAC_APROB_CRI")
    private Long aprobCri;

    @Column(name = "DFAC_BITACORA")
    private Long bitacora;

    @Column(name = "DFAC_LOTE")
    private String lote;

    @Column(name = "DFAC_PRECIO_REAL")
    private BigDecimal precioReal;

    @Column(name = "DFAC_CANT_PCL")
    private Long cantPlc;

    @Column(name = "DFAC_SECCION")
    private Long seccion;

    @Column(name = "DFAC_COMPONENTE")
    private Long componente;

    @Column(name = "DFAC_CCO_FECHA")
    private LocalDate fecha;

    @Column(name = "DFAC_CCO_ESTADO")
    private Long estado;

    @Column(name = "DFAC_CCO_TIPODOC")
    private Long tipoDoc;

    @Column(name = "DFAC_OBSEQUIO")
    private Long obsequio;

    @Column(name = "DFAC_SEC_IMP")
    private Long secImp;

    @Column(name = "DFAC_DSCITEM_LIN")
    private BigDecimal dscItemLin;

    @Column(name = "DFAC_FOTOPROD")
    private Long fotoProd;

    @Column(name = "DFAC_CANTINI")
    private BigDecimal cantIni;

    @Column(name = "DFAC_LISTAPRE")
    private Long listapre;

    @Column(name = "DFAC_DESPACHAR")
    private Long despachar;

    @Column(name = "DFAC_HOJA")
    private Long hoja;

    @Column(name = "DFAC_FECHA_DESPACHO")
    private LocalDate fechaDespacho;

    @Column(name = "DFAC_FECHA_EMPAQUE")
    private LocalDate fechaEmpaque;

    @Column(name = "DFAC_CAJAEMP")
    private String cajaEmp;
}