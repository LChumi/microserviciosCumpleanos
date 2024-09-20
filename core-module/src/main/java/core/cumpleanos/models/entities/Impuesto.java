package core.cumpleanos.models.entities;

import core.cumpleanos.models.ids.ImpuestoId;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "IMPUESTO", indexes = {
        @Index(name = "IMPUESTO_UIDX1", columnList = "IMP_NOMBRE, IMP_EMPRESA", unique = true),
        @Index(name = "IMPUESTO_UIDX2", columnList = "IMP_ID, IMP_EMPRESA", unique = true),
        @Index(name = "IMPUESTO_NIDX1", columnList = "IMP_CUENTA, IMP_EMPRESA")
})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Impuesto {

    @EmbeddedId
    private ImpuestoId id;

    @Column(name = "IMP_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "IMP_ID", nullable = false, length = 10)
    private String impId;

    @Column(name = "IMP_PORCENTAJE", precision = 5, scale = 2)
    private BigDecimal porcentaje;

    @Column(name = "IMP_DEBCRE")
    private Boolean debcre;

    @Column(name = "IMP_INACTIVO")
    private Boolean inactivo;

    @Column(name = "CREA_USR", length = 10)
    private String creaUsr;

    @Column(name = "CREA_FECHA")
    private LocalDate creaFecha;

    @Column(name = "MOD_USR", length = 10)
    private String modUsr;

    @Column(name = "MOD_FECHA")
    private LocalDate modFecha;

    @Column(name = "IMP_IVAFUENTE")
    private Boolean ivafuente;

    @Column(name = "IMP_CONCEPTO")
    private Long concepto;

    @Column(name = "IMP_PERSONA")
    private Boolean persona;

    @Column(name = "IMP_SERVICIO")
    private Boolean servicio;

    @ColumnDefault("0")
    @Column(name = "IMP_SIVA")
    private Boolean sIva;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "IMP_CUENTA", referencedColumnName = "CUE_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "IMP_EMPRESA", referencedColumnName = "CUE_EMPRESA", insertable = false, updatable = false),
    })
    private Cuenta cuenta;

    @JsonBackReference
    @OneToMany(mappedBy = "impuesto", fetch = FetchType.LAZY)
    private Set<CcomFac> ccomFacs = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "retFuente", fetch = FetchType.LAZY)
    private Set<Cliente> clientesFuente = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "retIva", fetch = FetchType.LAZY)
    private Set<Cliente> clientesIva = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "impuesto", fetch = FetchType.LAZY)
    private Set<Total> totales = new LinkedHashSet<>();
}
