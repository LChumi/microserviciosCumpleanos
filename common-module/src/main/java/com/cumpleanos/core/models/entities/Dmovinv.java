package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.DmovinvId;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "DMOVINV", schema = "DATA_USR", indexes = {
        @Index(name = "DMOVINV_IDX4", columnList = "DMO_CMO_COMPROBA, DMO_EMPRESA"),
        @Index(name = "DMOVINV_IDX1", columnList = "DMO_PRODUCTO, DMO_EMPRESA, DMO_DEBCRE"),
        @Index(name = "DMOVINV_NIDX2", columnList = "DMO_PRODUCTO, DMO_EMPRESA"),
        @Index(name = "DMOVINV_IDX3", columnList = "DMO_PRODUCTO, DMO_CATPRODUCTO, DMO_BODEGA, DMO_FECHA, DMO_EMPRESA"),
        @Index(name = "DMOVINV_IDX5", columnList = "DMO_PRODUCTO, DMO_BODEGA, DMO_TRANSACCION, DMO_EMPRESA"),
        @Index(name = "DMOVINV_IDX7", columnList = "DMO_PRODUCTO, DMO_UDIGITADA"),
        @Index(name = "DMOVINV_NIDX5", columnList = "DMO_CATPRODUCTO, DMO_EMPRESA"),
        @Index(name = "DMOVINV_IDX2", columnList = "DMO_TRANSACCION, DMO_EMPRESA"),
        @Index(name = "DMOVINV_NIDX1", columnList = "DMO_BODEGA, DMO_EMPRESA"),
        @Index(name = "DMOVINV_IDX6", columnList = "DMO_BODEGA, DMO_CTAINV, DMO_CMO_COMPROBA"),
        @Index(name = "DMOVINV_NIDX6", columnList = "DMO_CUENTA, DMO_EMPRESA"),
        @Index(name = "DMOVINV_NIDX3", columnList = "DMO_TIPODEV, DMO_EMPRESA"),
        @Index(name = "DMOVINV_NIDX7", columnList = "DMO_CTAINV, DMO_EMPRESA")
})
public class Dmovinv {

    @EmbeddedId
    private DmovinvId id;

    @Column(name = "DMO_TRANSACCION", nullable = false)
    private Long transaccion;

    @Column(name = "DMO_DEBCRE", nullable = false)
    private Boolean debcre = false;

    @Column(name = "DMO_CANTIDAD", nullable = false, precision = 17, scale = 4)
    private BigDecimal cantidad;

    @Column(name = "DMO_DEVUELTA", precision = 17, scale = 4)
    private BigDecimal devuelta;

    @Column(name = "DMO_DESPACHADA", precision = 17, scale = 4)
    private BigDecimal despachada;

    @Column(name = "DMO_DEVDESPA", precision = 17, scale = 4)
    private BigDecimal devdespa;

    @Column(name = "DMO_COSTO", precision = 17, scale = 4)
    private BigDecimal costo;

    @Column(name = "DMO_TOTAL", nullable = false, precision = 17, scale = 4)
    private BigDecimal total;

    @Column(name = "DMO_CANT_FISICA", precision = 17, scale = 4)
    private BigDecimal cantFisica;

    @Column(name = "DMO_CANT_TRANSITO", precision = 17, scale = 4)
    private BigDecimal cantTransito;

    @Column(name = "DMO_CANT_CONSIGNA", precision = 17, scale = 4)
    private BigDecimal cantConsigna;

    @Column(name = "CREA_USR", length = 10)
    private String creaUsr;

    @Column(name = "CREA_FECHA")
    private LocalDate creaFecha;

    @Column(name = "MOD_USR", length = 10)
    private String modUsr;

    @Column(name = "MOD_FECHA")
    private LocalDate modFecha;

    @Column(name = "DMO_CENTRO")
    private Long centro;

    @Column(name = "DMO_FECHA")
    private LocalDate fecha;

    @Column(name = "DMO_TEMPERATURA", precision = 5, scale = 2)
    private BigDecimal temperatura;

    @Column(name = "DMO_GRADO", precision = 5, scale = 2)
    private BigDecimal grado;

    @Column(name = "DMO_UDIGITADA")
    private Long udigitada;

    @Column(name = "DMO_CDIGITADA", precision = 17, scale = 4)
    private BigDecimal cdigitada;

    @Column(name = "DMO_PDIGITADO", precision = 17, scale = 4)
    private BigDecimal pdigitado;

    @Column(name = "DMO_CONAJUSTE")
    private Long conajuste;

    @Column(name = "DMO_CANT_DEVENV", precision = 17, scale = 4)
    private BigDecimal cantDevenv;

    @Column(name = "DMO_REFDESENVASE")
    private Long refdesenvase;

    @Column(name = "DMO_RECHAZO", precision = 17, scale = 4)
    private BigDecimal rechazo;

    @Column(name = "DMO_SERIE")
    private Long serie;

    @Column(name = "DMO_PRO_COMBO")
    private Long proCombo;

    @Column(name = "DMO_CANT_SOL", precision = 17, scale = 4)
    private BigDecimal cantSol;

    @Column(name = "DMO_SECUENCIA_PED")
    private Long secuenciaPed;

    @Column(name = "DMO_PDI")
    private Long pdi;

    @Column(name = "DMO_CCO_FECHA")
    private LocalDate ccoFecha;

    @Column(name = "DMO_CCO_ESTADO")
    private Boolean ccoEstado;

    @Column(name = "DMO_CCO_TIPODOC")
    private Long ccoTipodoc;

    @Column(name = "DMO_TINV_CODIGO")
    private Long tinvCodigo;

    @Column(name = "DMO_PRECIO", precision = 17, scale = 4)
    private BigDecimal precio;

    @Column(name = "DMO_PRECIO_TOTAL", precision = 17, scale = 4)
    private BigDecimal precioTotal;

    @Column(name = "DMO_PRECIO_ALT", precision = 17, scale = 4)
    private BigDecimal precioAlt;

    @Column(name = "DMO_ALICUOTA")
    private Boolean alicuota;

    @Column(name = "DMO_CANT_TRANSF", precision = 17, scale = 4)
    private BigDecimal cantTransf;

    @Column(name = "DMO_PRO_COMBO_IN")
    private Long proComboIn;

    @Column(name = "DMO_OBSERVACION", length = 500)
    private String observacion;

    @Column(name = "DMO_HOJA")
    private Long hoja;

    @Column(name = "DMO_FECHA_DESPACHO")
    private LocalDate fechaDespacho;

    @Column(name = "DMO_FECHA_EMPAQUE")
    private LocalDate fechaEmpaque;

    @Column(name = "DMO_CAJA_EMPAQUE", length = 50)
    private String cajaEmpaque;

    @Column(name = "DMO_SEC_PAQUETE")
    private Long secPaquete;

}
