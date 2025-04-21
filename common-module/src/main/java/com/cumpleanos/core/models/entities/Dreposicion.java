package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.DreposicionId;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString(exclude = {})
@EqualsAndHashCode(of = "id")
@Table(name = "DREPOSICION", schema = "DATA_USR", indexes = {
        @Index(name = "DREPOSICION_NIDX2", columnList = "DRP_CREPOSICION, DRP_EMPRESA"),
        @Index(name = "DREPOSICION_NIDX3", columnList = "DRP_PRODUCTO, DRP_EMPRESA"),
        @Index(name = "DREPOSICION_NIDX1", columnList = "DRP_GONDOLA, DRP_EMPRESA"),
        @Index(name = "DREPOSICION_NIDX4", columnList = "DRP_USUARIO, DRP_EMPRESA")
})
public class Dreposicion {

    @EmbeddedId
    private DreposicionId id;

    @Column(name = "DRP_CANT_SOL", precision = 17, scale = 4)
    private BigDecimal cantSol;

    @Column(name = "DRP_CANT_APR", precision = 17, scale = 4)
    private BigDecimal cantApr;

    @Column(name = "DRP_OBSERVACION", length = 500)
    private String observacion;

    @Column(name = "CREA_USR", length = 50)
    private String creaUsr;

    @Column(name = "CREA_FECHA")
    private LocalDate creaFecha;

    @Column(name = "MOD_USR", length = 50)
    private String modUsr;

    @Column(name = "MOD_FECHA")
    private LocalDate modFecha;

    @Column(name = "DRP_USUARIO", length = 50)
    private String usuario;

    @Column(name = "DRP_PRECIO", precision = 17, scale = 4)
    private BigDecimal precio;

    @Column(name = "DRP_PORC_DESC", precision = 17, scale = 4)
    private BigDecimal porcDesc;

    @Column(name = "DRP_VAL_DESC", precision = 17, scale = 4)
    private BigDecimal valDesc;

    @Column(name = "DRP_TOTAL", precision = 17, scale = 4)
    private BigDecimal total;

    @Column(name = "DRP_CANT_DISP", precision = 17, scale = 4)
    private BigDecimal cantDisp;

    @Column(name = "DRP_PRODUCTO")
    private Long productoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "DRP_PRODUCTO", referencedColumnName = "PRO_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "DRP_EMPRESA", referencedColumnName = "PRO_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Producto producto;

    @Column(name= "DRP_CREPOSICION")
    private Long dreposicionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "DRP_CREPOSICION", referencedColumnName = "CRP_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "DRP_EMPRESA", referencedColumnName = "CRP_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Creposicion creposicion;
}
