package com.cumpleanos.pos.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name = "RECIBO_POS_V")
@Data
public class ReciboPOSView {

    @Id
    @Column(name = "RPO_USR_LIQUIDA")
    private Long usrLiquida;

    @Column(name = "CAP_CODIGO")
    private Long codigo;

    @Column(name = "RPO_CCO_COMPROBA")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigInteger ccoComprobaId;

    @Column(name = "CAP_EMPRESA")
    private Long empresa;

    @Column(name = "CAP_DESCRIPCION")
    private String descripcion;

    @Column(name = "CAP_ALMACEN")
    private Long almacen;

    @Column(name = "CAP_PVENTA")
    private Long pventa;

    @Column(name = "CAP_IP")
    private String ip;

    @Column(name = "CAP_ID")
    private String capId;

    @Column(name = "CAP_NOMBRE_EQUIPO")
    private String nombreEquipo;

    @Column(name = "CAP_PUERTO")
    private String puertoCom;

    @Column(name = "RPO_CODIGO")
    private Long rpoCodigo;

    @Column(name = "RPO_SUBTOTAL_0")
    private BigDecimal subtotal0;

    @Column(name = "RPO_SUBTOTAL")
    private BigDecimal subtotal;

    @Column(name = "RPO_DESCUENTO")
    private BigDecimal descuento;

    @Column(name = "RPO_IMPUESTO")
    private Long impuesto;

    @Column(name = "RPO_PORC_IMPUESTO")
    private BigDecimal porcImpuesto;

    @Column(name = "RPO_VAL_IMPUESTO")
    private BigDecimal valImpuesto;

    @Column(name = "RPO_CLIENTE")
    private Long cliente;

    @Column(name = "RPO_TARJETAHABIENTE")
    private String tarjetaHabiente;

    @Column(name = "RPO_NUM_APROB")
    private String numAprobacion;

    @Column(name = "RPO_NOM_EMISOR")
    private String nomEmisor;

    @Column(name = "RPO_REFERENCIA")
    private String referencia;

    @Column(name = "RPO_LOTE")
    private String lote;

    @Column(name = "RPO_COD_ADQUIRIENTE")
    private String codAdquiriente;

    @Column(name = "RPO_NOM_ADQUIRIENTE")
    private String nomAdquiriente;

    @Column(name = "RPO_NUM_TARJETA")
    private String numTajeta;

    @Column(name = "RPO_FECHA")
    private String fecha;

    @Column(name = "RPO_HORA")
    private String hora;

    @Column(name = "RPO_RESULTADO")
    private String resultado;

    @Column(name = "RPO_ALMACEN")
    private Long rpoAlmacen;

    @Column(name = "RPO_PVENTA")
    private Long rpoPventa;

    @Column(name = "RPO_CCO_RECIBO")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigInteger ccoRecibo;

    @Column(name = "TCR_ID")
    private String tipoCredito;

    @Column(name = "RPO_CUOTAS")
    private Long cuotas=0L;

    @Column(name = "ALM_ID")
    private String almId;

    @Column(name = "RPO_TOTAL")
    private BigDecimal total;

}
