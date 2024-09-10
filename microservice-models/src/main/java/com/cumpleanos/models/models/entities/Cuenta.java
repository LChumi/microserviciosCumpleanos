package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.CuentaId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "CUENTA")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
        "modulo", "cuenta", "centros", "cuentas"
})
public class Cuenta {

    @EmbeddedId
    private CuentaId id;

    @Size(max = 20)
    @NotNull
    @Column(name = "CUE_ID", nullable = false, length = 20)
    private String cueId;

    @Size(max = 100)
    @NotNull
    @Column(name = "CUE_NOMBRE", nullable = false, length = 100)
    private String cueNombre;

    @ColumnDefault("0")
    @Column(name = "CUE_INACTIVO")
    private Boolean cueInactivo;

    @Column(name = "CUE_GENERO")
    private Boolean cueGenero;

    @NotNull
    @Column(name = "CUE_MOVIMIENTO", nullable = false)
    private Boolean cueMovimiento = false;

    @NotNull
    @Column(name = "CUE_ORDEN", nullable = false)
    private Long cueOrden;

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

    @ColumnDefault("0")
    @Column(name = "CUE_VISUALIZA")
    private Boolean cueVisualiza;

    @ColumnDefault("0")
    @Column(name = "CUE_NEGRITA")
    private Boolean cueNegrita;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUE_MODULO")
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Modulo modulo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CUE_REPORTA", referencedColumnName = "CUE_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CUE_EMPRESA", referencedColumnName = "CUE_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Cuenta cuenta;

    @OneToMany(mappedBy = "cuenta", fetch = FetchType.LAZY)
    private Set<Centro> centros = new LinkedHashSet<>();

    @OneToMany(mappedBy = "cuenta", fetch = FetchType.LAZY)
    private Set<Cuenta> cuentas = new LinkedHashSet<>();
}
