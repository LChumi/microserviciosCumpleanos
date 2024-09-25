package core.cumpleanos.models.entities;

import core.cumpleanos.models.ids.CentroId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "CENTRO", indexes = {
        @Index(name = "CENTRO_UIDX1", columnList = "CEN_NOMBRE, CEN_EMPRESA", unique = true),
        @Index(name = "CENTRO_UIDX2", columnList = "CEN_ORDEN, CEN_REPORTA, CEN_EMPRESA", unique = true),
        @Index(name = "CENTRO_NIDX1", columnList = "CEN_REPORTA, CEN_EMPRESA"),
        @Index(name = "CENTRO_UIDX3", columnList = "CEN_CUENTA, CEN_EMPRESA")
})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
        "cuenta", "centro"
})
public class Centro {

    @EmbeddedId
    private CentroId id;

    @Size(max = 100)
    @NotNull
    @Column(name = "CEN_NOMBRE", nullable = false, length = 100)
    private String cenNombre;

    @Size(max = 10)
    @NotNull
    @Column(name = "CEN_ID", nullable = false, length = 10)
    private String cenId;

    @Column(name = "CEN_INACTIVO")
    private Boolean cenInactivo;

    @NotNull
    @Column(name = "CEN_ORDEN", nullable = false)
    private Long cenOrden;

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
    @Column(name = "CEN_DISTRIBUIR")
    private Boolean cenDistribuir;

    @Column(name = "CEN_NORMAL", precision = 5, scale = 2)
    private BigDecimal cenNormal;

    @Column(name = "CEN_25", precision = 5, scale = 2)
    private BigDecimal cen25;

    @Column(name = "CEN_50", precision = 5, scale = 2)
    private BigDecimal cen50;

    @Column(name = "CEN_100", precision = 5, scale = 2)
    private BigDecimal cen100;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CEN_CUENTA", referencedColumnName = "CUE_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CEN_EMPRESA", referencedColumnName = "CUE_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Cuenta cuenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CEN_REPORTA", referencedColumnName = "CEN_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CEN_EMPRESA", referencedColumnName = "CEN_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Centro centro;

    /*@JsonBackReference
    @OneToMany(mappedBy = "centro", fetch = FetchType.LAZY)
    private Set<Bodega> bodegas = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "centro", fetch = FetchType.LAZY)
    private Set<Ccomproba> ccomprobas = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "centro", fetch = FetchType.LAZY)
    private Set<Centro> centros = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "centro", fetch = FetchType.LAZY)
    private Set<Dfactura> dfacturas = new LinkedHashSet<>();*/
}
