package com.cumlpeanos.pos.models.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name = "RECIBO_POS")
@Data
public class ReciboPOS {

    @Id
    @Column(name = "RPO_CODIGO")
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "RPO_EMPRESA")
    private Long empresa;

    @Column(name = "RPO_USR_LIQUIDA")
    private int usr_liquida;

    @Column(name = "RPO_CCO_COMPROBA")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigInteger ccoComproba;

    @Column(name = "RPO_SUBTOTAL_0")
    private BigDecimal subtotal0;

    @Column(name = "RPO_SUBTOTAL")
    private BigDecimal subtotal;

    @Column(name = "RPO_DESCUENTO")
    private BigDecimal descuento;

    @Column(name = "RPO_IMPUESTO")
    private int impuesto;

    @Column(name = "RPO_PORC_IMPUESTO")
    private BigDecimal porcImpuesto;

    @Column(name = "RPO_VAL_IMPUESTO")
    private BigDecimal valImpuesto;

    @Column(name = "RPO_CLIENTE")
    private Long cliente;

    @Column(name = "RPO_TARJETAHABIENTE")
    private String tarjetaHabiente;

    @Column(name = "RPO_NUM_APROB")
    private String num_aprob;

    @Column(name = "RPO_NOM_EMISOR")
    private String nom_emisor;

    @Column(name = "RPO_REFERENCIA")
    private String referencia;

    @Column(name = "RPO_LOTE")
    private String lote;

    @Column(name = "RPO_COD_ADQUIRIENTE")
    private String codAdquiriente;

    @Column(name = "RPO_NOM_ADQUIRIENTE")
    private String nomAdquiriente;

    @Column(name = "RPO_NUM_TARJETA")
    private String num_tarjeta;

    @Column(name = "RPO_FECHA")
    private String fecha;

    @Column(name = "RPO_HORA")
    private String hora;

    @Column(name = "RPO_RESULTADO")
    private String resultado;

    @Column(name = "RPO_ALMACEN")
    private int almacen;

    @Column(name = "RPO_PVENTA")
    private int pventa;

    @Column(name = "RPO_CCO_RECIBO")
    private BigInteger ccoRecibo;

    @Column(name = "RPO_USRLIQ_FAC")
    private Long usrLiqFac;

    @Column(name = "RPO_ANULADO")
    private Long anulado;

    @Column(name = "RPO_CUOTAS")
    private Long cuotas;

    @OneToOne
    @JoinColumn(name = "RPO_TIPO_CREDITO_POS")
    private TipoCreditoPOS tipoCreditoPOS;
}
