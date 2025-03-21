package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.GproductoId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "GPRODUCTO", indexes = {
        @Index(name = "GPRODUCTO_UDX1", columnList = "GPR_EMPRESA, GPR_ID", unique = true),
        @Index(name = "GPRODUCTO_UDX2", columnList = "GPR_EMPRESA, GPR_NOMBRE", unique = true)
})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
        "sistema"
})
public class Gproducto {

    @EmbeddedId
    private GproductoId id;

    @Size(max = 10)
    @NotNull
    @Column(name = "GPR_ID", nullable = false, length = 10)
    private String gprId;

    @Size(max = 100)
    @NotNull
    @Column(name = "GPR_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "GPR_CTA_COSTO")
    private Long ctaCosto;

    @Column(name = "GPR_CTA_INV")
    private Long ctaInv;

    @Column(name = "GPR_CTA_VENTA")
    private Long ctaVenta;

    @ColumnDefault("0")
    @Column(name = "GPR_INACTIVO")
    private Boolean inactivo;

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

    @Column(name = "GPR_CTA_DES")
    private Long ctaDes;

    @Column(name = "GPR_CTA_DEV")
    private Long ctaDev;

    @Size(max = 1)
    @ColumnDefault("'N'")
    @Column(name = "GPR_STS_LNS", length = 1)
    private String stsLns;

    @Size(max = 1)
    @ColumnDefault("'N'")
    @Column(name = "GPR_STS_PRESUPUESTO", length = 1)
    private String stsPresupuesto;

    @Size(max = 1)
    @ColumnDefault("'N'")
    @Column(name = "GPR_TIENE_CENSO", length = 1)
    private String tieneCenso;

    @Column(name = "GPR_CTA_COSTO1")
    private Long ctaCosto1;

    @Column(name = "GPR_CTA_INV1")
    private Long ctaInv1;

    @Column(name = "GPR_CTA_VENTA1")
    private Long ctaVenta1;

    @Column(name = "GPR_CTA_DES1")
    private Long ctaDes1;

    @Column(name = "GPR_CTA_DEV1")
    private Long ctaDev1;

    @Size(max = 1)
    @Column(name = "GPR_VEHICULO_YN", length = 1)
    private String vehiculoYn;

    @Column(name = "GPR_CTA_CTR")
    private Long ctaCtr;

    @Column(name = "GPR_CTA_TTR")
    private Long ctaTtr;

    @Size(max = 50)
    @Column(name = "GPR_COLOR", length = 50)
    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GPR_EMPRESA", referencedColumnName = "SIS_CODIGO", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Sistema sistema;

    /*@JsonBackReference
    @OneToMany(mappedBy = "gproducto", fetch = FetchType.LAZY)
    private Set<Producto> productos = new LinkedHashSet<>();*/
}
