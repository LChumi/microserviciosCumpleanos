package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.TotalId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Entity
@Table(name = "TOTAL")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
        "transportista", "ccomproba"
})
public class Total {

    @EmbeddedId
    private TotalId id;

    @Column(name = "TOT_PORC_DESC", precision = 5, scale = 2)
    private BigDecimal porcDesc;

    @Column(name = "TOT_PORC_FINANC", precision = 5, scale = 2)
    private BigDecimal porcFinanc;

    @Column(name = "TOT_PORC_PRO_PAGO", precision = 5, scale = 2)
    private BigDecimal porcProPago;

    @Column(name = "TOT_PORC_PAG_CONTA", precision = 5, scale = 2)
    private BigDecimal porcPagConta;

    @Column(name = "TOT_LINEA_CREDITO")
    private Integer lineaCredito;

    @Column(name = "TOT_DIAS_PLAZO")
    private Integer diasPlazo;

    @Column(name = "TOT_NRO_PAGOS")
    private Integer nroPagos;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "TOT_SUBTOTAL", nullable = false, precision = 17, scale = 4)
    private BigDecimal subtotal;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "TOT_DESCUENTO1", nullable = false, precision = 17, scale = 4)
    private BigDecimal descuento1;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "TOT_DESCUENTO2", nullable = false, precision = 17, scale = 4)
    private BigDecimal descuento2;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "TOT_TIMPUESTO", nullable = false, precision = 17, scale = 4)
    private BigDecimal timpuesto;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "TOT_TRANSPORTE", nullable = false, precision = 17, scale = 4)
    private BigDecimal transporte;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "TOT_SEGURO_TRANS", nullable = false, precision = 17, scale = 4)
    private BigDecimal seguroTrans;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "TOT_AJUSTE", nullable = false, precision = 17, scale = 4)
    private BigDecimal ajuste;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "TOT_FINANCIA", nullable = false, precision = 17, scale = 4)
    private BigDecimal financia;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "TOT_TOTAL", nullable = false, precision = 17, scale = 4)
    private BigDecimal total;

    @ColumnDefault("0")
    @Column(name = "TOT_PORC_IMPUESTO", precision = 5, scale = 2)
    private BigDecimal porcImpuesto;

    @ColumnDefault("0")
    @Column(name = "TOT_ICE", precision = 17, scale = 4)
    private BigDecimal ice;

    @ColumnDefault("0")
    @Column(name = "TOT_NETO_CLIENTE", precision = 17, scale = 4)
    private BigDecimal netoCliente;

    @ColumnDefault("0")
    @Column(name = "TOT_DESC1_0", precision = 17, scale = 4)
    private BigDecimal desc10;

    @ColumnDefault("0")
    @Column(name = "TOT_DESC2_0", precision = 17, scale = 4)
    private BigDecimal desc20;

    @ColumnDefault("0")
    @Column(name = "TOT_SUBTOT_0", precision = 17, scale = 4)
    private BigDecimal subtot0;

    @ColumnDefault("0")
    @Column(name = "TOT_TIMPUESTO1", precision = 17, scale = 4)
    private BigDecimal timpuesto1;

    @ColumnDefault("0")
    @Column(name = "TOT_ANTICIPO", precision = 17, scale = 4)
    private BigDecimal anticipo;

    @ColumnDefault("0")
    @Column(name = "TOT_PORC_BONO", precision = 5, scale = 2)
    private BigDecimal totPorcBono;

    @ColumnDefault("0")
    @Column(name = "TOT_BONO", precision = 17, scale = 4)
    private BigDecimal bono;

    @ColumnDefault("0")
    @Column(name = "TOT_PAGA", precision = 17, scale = 4)
    private BigDecimal paga;

    @ColumnDefault("0")
    @Column(name = "TOT_RASTREADOR", precision = 17, scale = 4)
    private BigDecimal rastreador;

    @ColumnDefault("0")
    @Column(name = "TOT_PRENDA", precision = 17, scale = 4)
    private BigDecimal prenda;

    @ColumnDefault("0")
    @Column(name = "TOT_FINANCIA_ADI", precision = 17, scale = 4)
    private BigDecimal financiaAdi;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "TOT_CCO_COMPROBA", referencedColumnName = "CCO_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "TOT_EMPRESA", referencedColumnName =  "CCO_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Ccomproba ccomproba;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "TOT_TRANSPORTISTA", referencedColumnName = "CLI_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "TOT_EMPRESA", referencedColumnName = "CLI_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Cliente transportista;
}
