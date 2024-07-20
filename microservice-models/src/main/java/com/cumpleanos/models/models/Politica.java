package com.cumpleanos.models.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "POLITICA")
@Data
public class Politica {

    @Id
    @Column(name = "POL_CODIGO")
    @Setter(AccessLevel.NONE)
    private Long codigo;

    @Column(name = "POL_EMPRESA")
    private Long empresa;

    @Column(name = "POL_NOMBRE")
    private String nombre;

    @Column(name = "POL_ID")
    private String polId;

    @Column(name = "POL_VALOR_DESDE")
    private BigDecimal valorDesde;

    @Column(name = "POL_VALOR_HASTA")
    private BigDecimal valorHasta;

    @Column(name = "POL_PORC_DESC")
    private BigDecimal porcDescuento;

    @Column(name = "POL_PORC_FINANC")
    private BigDecimal porcFinanc;

    @Column(name = "POL_PORC_PAG_CONTA")
    private BigDecimal porcPagConta;

    @Column(name = "POL_DIAS_PLAZO")
    private Long diasPlazo;

    @Column(name = "POL_NRO_PAGOS")
    private Long nroPagos;

    @Column(name = "POL_LINEA_CREDITO")
    private BigDecimal lineaCredito;

    @Column(name = "POL_INACTIVO")
    private Boolean intivo;

    @Column(name = "POL_TIPOCLI")
    private Long tipCliente;
}
