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

@Entity
@Table(name = "TOTAL")
@Data
public class Total {

    @Id
    @Column(name = "TOT_CCO_COMPROBA")
    @Setter(AccessLevel.NONE)
    private BigInteger ccoComproba;

    @Column(name = "TOT_EMPRESA")
    private Long empresa;

    @Column(name = "TOT_IMPUESTO")
    private Long impuesto;

    @Column(name = "TOT_POR_DESC")
    private BigDecimal porDesc;

    @Column(name = "TOT_POR_FINANC")
    private BigDecimal porFinanc;

    @Column(name = "TOT_PORC_PRO_PAGO")
    private BigDecimal porcProPago;

    @Column(name = "TOT_PORC_PAG_CONTA")
    private BigDecimal porcPagConta;

    @Column(name = "TOT_LINEA_CREDITO")
    private Long lineaCredito;

    @Column(name = "TOT_DIAS_PLAZO")
    private Long diasPlazo;

    @Column(name = "TOT_NRO_PAGOS")
    private Long nroPagos;

    @Column(name = "TOT_TRANSPORTISTA")
    private Long transportista;

    @Column(name = "TOT_SUBTOTAL")
    private BigDecimal subtotal;

    @Column(name = "TOT_DESCUENTO1")
    private BigDecimal descuento1;

    @Column(name = "TOT_DESCUENTO2")
    private BigDecimal descuento2;

    @Column(name = "TOT_TIMPUESTO")
    private BigDecimal tImpuesto;

    @Column(name = "TOT_TRANSPORTE")
    private BigDecimal transporte;

    @Column(name = "TOT_SEGURO_TRANS")
    private BigDecimal seguroTrans;

    @Column(name = "TOT_AJUSTE")
    private BigDecimal ajuste;

    @Column(name = "TOT_FINANCIA")
    private BigDecimal financia;

    @Column(name = "TOT_TOTAL")
    private BigDecimal total;

    @Column(name = "TOT_PORC_IMPUESTO")
    private BigDecimal porcImpuesto;

    @Column(name = "TOT_ICE")
    private BigDecimal ice;

    @Column(name = "TOT_NETO_CLIENTE")
    private BigDecimal netCliente;

    @Column(name = "TOT_DES1_0")
    private BigDecimal des1;

    @Column(name = "TOT_DESC2_0")
    private BigDecimal desc2;

    @Column(name = "TOT_SUBTOT_0")
    private BigDecimal subtotal0;

    @Column(name = "TOT_TIMPUESTO1")
    private BigDecimal tImpuesto1;

    @Column(name = "TOT_ANTICIPO")
    private BigDecimal anticipo;

    @Column(name = "TOT_PORC_BONO")
    private BigDecimal porcBono;

    @Column(name = "TOT_BONO")
    private BigDecimal bono;

    @Column(name = "TOT_PAGA")
    private BigDecimal paga;

    @Column(name = "TOT_RASTREADOR")
    private BigDecimal rastreador;

    @Column(name = "TOT_PRENDA")
    private BigDecimal prenda;

    @Column(name = "TOT_FINANCIA_ADI")
    private BigDecimal financiaAdi;

}
