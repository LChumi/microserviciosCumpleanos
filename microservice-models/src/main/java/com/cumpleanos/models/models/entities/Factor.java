package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.FactorId;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "FACTOR")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
        "umedida", "producto", "dfacturas"
})
public class Factor {

    @EmbeddedId
    private FactorId id;

    @NotNull
    @Column(name = "FAC_FACTOR", nullable = false, precision = 17, scale = 4)
    private BigDecimal factor;

    @Column(name = "FAC_INACTIVO")
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

    @Column(name = "FAC_DEFAULT")
    private Boolean facDefault;

    @Column(name = "FAC_DEFAULT_PRO")
    private Boolean defaultPro;

    @Column(name = "FAC_ALCOHOL", precision = 17, scale = 4)
    private BigDecimal alcohol;

    @Column(name = "FAC_PESO", precision = 17, scale = 4)
    private BigDecimal peso;

    @Column(name = "FAC_DEFAUL_CAJ")
    private Boolean defaulCaj;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "FAC_UNIDAD", referencedColumnName = "UMD_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "FAC_EMPRESA", referencedColumnName = "UMD_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Umedida umedida;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "FAC_PRODUCTO", referencedColumnName = "PRO_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "FAC_EMPRESA", referencedColumnName = "PRO_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete (action = OnDeleteAction.RESTRICT)
    private Producto producto;

    @OneToMany(mappedBy = "factor", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Dfactura> dfacturas = new LinkedHashSet<>();
}
