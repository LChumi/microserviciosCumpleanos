package com.cumpleanos.pos.persistence.entity;

import com.cumpleanos.pos.persistence.ids.ReciboPOSId;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Table(name = "RECIBO_POS")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = { "tipoCreditoPOS" })
public class ReciboPOS {

    @EmbeddedId
    private ReciboPOSId id;

    @Column(name = "RPO_USR_LIQUIDA")
    private int usrLiquida;

    @Column(name = "RPO_CCO_COMPROBA")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigInteger ccoComproba;

    @Column(name = "RPO_SUBTOTAL_0", precision = 17, scale = 4)
    private BigDecimal subtotal0;

    @Column(name = "RPO_SUBTOTAL", precision = 17, scale = 4)
    private BigDecimal subtotal;

    @Column(name = "RPO_DESCUENTO", precision = 17, scale = 4)
    private BigDecimal descuento;

    @Column(name = "RPO_IMPUESTO")
    private Long impuesto;

    @Column(name = "RPO_PORC_IMPUESTO", precision = 17, scale = 4)
    private Long porcImpuesto;

    @Column(name = "RPO_VAL_IMPUESTO", precision = 17, scale = 4)
    private BigDecimal valImpuesto;

    @Size(max = 50)
    @Column(name = "RPO_TARJETAHABIENTE", length = 50)
    private String tarjetaHabiente;

    @Size(max = 50)
    @Column(name = "RPO_NUM_APROB", length = 50)
    private String numAprob;

    @Size(max = 50)
    @Column(name = "RPO_NOM_EMISOR", length = 50)
    private String nomEmisor;

    @Size(max = 50)
    @Column(name = "RPO_REFERENCIA", length = 50)
    private String referencia;

    @Size(max = 50)
    @Column(name = "RPO_LOTE", length = 50)
    private String lote;

    @Size(max = 50)
    @Column(name = "RPO_COD_ADQUIRIENTE", length = 50)
    private String codAdquiriente;

    @Size(max = 50)
    @Column(name = "RPO_NOM_ADQUIRIENTE", length = 50)
    private String nomAdquiriente;

    @Size(max = 50)
    @Column(name = "RPO_NUM_TARJETA", length = 50)
    private String numTarjeta;

    @Size(max = 50)
    @Column(name = "RPO_FECHA", length = 50)
    private String fecha;

    @Size(max = 50)
    @Column(name = "RPO_HORA", length = 50)
    private String hora;

    @Size(max = 50)
    @Column(name = "RPO_RESULTADO", length = 50)
    private String resultado;

    @Size(max = 10)
    @Column(name = "CREA_USR", length = 10)
    private String creaUsr;

    @Column(name = "CREA_FECHA")
    private LocalDate creaFecha;

    @Size(max = 10)
    @Column(name = "MOD_USR", length = 10)
    private String modUsr;

    @Column(name = "MOD_FECHA")
    private LocalDate modFecha;

    @Column(name = "RPO_USRLIQ_FAC")
    private Long usrLiqFac;

    @Column(name = "RPO_ANULADO")
    private Boolean anulado;

    @Column(name = "RPO_CUOTAS")
    private Long cuotas;

    @Column(name = "RPO_TOTAL", precision = 17, scale = 4)
    private BigDecimal rpoTotal;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumns({
            @JoinColumn(name = "RPO_TIPO_CREDITO_POS", referencedColumnName = "TCR_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "RPO_EMPRESA", referencedColumnName = "TCR_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private TipoCreditoPOS tipoCreditoPOS;
}
