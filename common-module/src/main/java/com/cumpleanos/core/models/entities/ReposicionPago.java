package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.ReposicionPagoId;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString(exclude = {})
@EqualsAndHashCode(of = "id")
@Table(name = "REPOSICION_PAGO")
public class ReposicionPago {

    @EmbeddedId
    private ReposicionPagoId id;

    @Column(name = "RPA_TIPOPAGO", length = 50)
    private String tipopago;

    @Column(name = "RPA_EMISOR", length = 200)
    private String emisor;

    @Column(name = "RPA_NRO_CUENTA", length = 50)
    private String nroCuenta;

    @Column(name = "RPA_NRO_DOCUM", length = 50)
    private String nroDocum;

    @Column(name = "RPA_FECHA")
    private LocalDate fecha;

    @Column(name = "RPA_MONTO", precision = 17, scale = 4)
    private BigDecimal monto;

    @Column(name = "RPA_LOTE", precision = 17, scale = 4)
    private Integer lote;

    @Column(name = "RPA_BANCO", length = 20)
    private String banco;

    @Column(name = "RPA_CREPOSICION")
    private Long creposicionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "RPA_CREPOSICION", referencedColumnName = "CRP_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "RPA_EMPRESA", referencedColumnName = "CRP_EMPRESA", insertable = false, updatable = false)
    })
    private Creposicion creposicion;
}
