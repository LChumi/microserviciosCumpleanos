package core.cumpleanos.models.entities;

import core.cumpleanos.models.ids.AgenteId;
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
@Table(name = "AGENTE",indexes = {
        @Index(name = "AGENTE_UIDX1", columnList = "AGE_NOMBRE, AGE_EMPRESA", unique = true),
        @Index(name = "AGENTE_UIDX2", columnList = "AGE_ID, AGE_EMPRESA", unique = true),
        @Index(name = "AGENTE_NIDX3", columnList = "AGE_BODEGA, AGE_EMPRESA"),
        @Index(name = "AGENTE_NIDX4", columnList = "AGE_CLIENTE, AGE_EMPRESA"),
        @Index(name = "AGENTE_NIDX1", columnList = "AGE_ALMACEN, AGE_EMPRESA"),
        @Index(name = "AGENTE_NIDX5", columnList = "AGE_PVENTA, AGE_ALMACEN, AGE_EMPRESA"),
        @Index(name = "AGENTE_NIDX2", columnList = "AGE_ALMACEN1, AGE_EMPRESA"),
        @Index(name = "AGENTE_NIDX6", columnList = "AGE_PVENTA1, AGE_ALMACEN1, AGE_EMPRESA")
})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
        "bodega", "almacen", "puntoVenta", "almacen1", "puntoVenta1", "usuario"
})
public class Agente {

    @EmbeddedId
    private AgenteId id;

    @Size(max = 100)
    @NotNull
    @Column(name = "AGE_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Size(max = 10)
    @NotNull
    @Column(name = "AGE_ID", nullable = false, length = 10)
    private String ageId;

    @NotNull
    @Column(name = "AGE_PORC_COMISION", nullable = false, precision = 5, scale = 2)
    private BigDecimal porcComision;

    @Column(name = "AGE_EMPLEADO")
    private Long empleado;

    @ColumnDefault("0")
    @Column(name = "AGE_INACTIVO")
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

    @Column(name = "AGE_CLIENTE")
    private Long cliente;

    @Column(name = "AGE_CUEGASTO")
    private Long ageCuegasto;

    @Column(name = "AGE_DEPARTAMENTO")
    private Boolean departamento;

    @Size(max = 10)
    @Column(name = "AGE_CEDULA", length = 10)
    private String cedula;

    @Column(name = "AGE_REPPRE")
    private Boolean reppre;

    @Column(name = "AGE_REPCOB")
    private Boolean repcob;

    @Column(name = "AGE_REPSAL")
    private Boolean repsal;

    @Column(name = "AGE_DIAS_COMISION", precision = 17, scale = 4)
    private BigDecimal diasComision;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "AGE_EMPRESA", referencedColumnName = "BOD_EMPRESA", insertable = false, updatable = false),
            @JoinColumn(name = "AGE_BODEGA" , referencedColumnName = "BOD_CODIGO", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Bodega bodega;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "AGE_EMPRESA", referencedColumnName = "ALM_EMPRESA", insertable = false, updatable = false),
            @JoinColumn(name = "AGE_ALMACEN", referencedColumnName = "ALM_CODIGO", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Almacen almacen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "AGE_EMPRESA", referencedColumnName = "PVE_EMPRESA", insertable = false, updatable = false),
            @JoinColumn(name = "AGE_ALMACEN", referencedColumnName = "PVE_ALMACEN", insertable = false, updatable = false),
            @JoinColumn(name = "AGE_PVENTA", referencedColumnName = "PVE_SECUENCIA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private PuntoVenta puntoVenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "AGE_EMPRESA", referencedColumnName = "ALM_EMPRESA", insertable = false, updatable = false),
            @JoinColumn(name = "AGE_ALMACEN", referencedColumnName = "ALM_CODIGO", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Almacen almacen1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "AGE_EMPRESA", referencedColumnName = "PVE_EMPRESA" , insertable = false, updatable = false),
            @JoinColumn(name = "AGE_ALMACEN", referencedColumnName = "PVE_ALMACEN", insertable = false, updatable = false),
            @JoinColumn(name = "AGE_PVENTA", referencedColumnName = "PVE_SECUENCIA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private PuntoVenta puntoVenta1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AGE_USUARIO")
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Usuario usuario;

    /*@JsonBackReference
    @OneToMany(mappedBy = "agente", fetch = FetchType.LAZY)
    private Set<Almacen> almacenes = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "agente", fetch = FetchType.LAZY)
    private Set<CadAgente> cadAgentes = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "agente", fetch = FetchType.LAZY)
    private Set<Ccomproba> ccomproba = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "chofer", fetch = FetchType.LAZY)
    private Set<Ccomproba> choferes = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "agente", fetch = FetchType.LAZY)
    private Set<Cliente> clientes = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "agente", fetch = FetchType.LAZY)
    private Set<Cliente> clientes2 = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "agente", fetch = FetchType.LAZY)
    private Set<Dfactura> dfacturas = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "agente", fetch = FetchType.LAZY)
    private Set<PuntoVenta> puntoventas = new LinkedHashSet<>();*/
}
