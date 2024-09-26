package core.cumpleanos.models.entities;

import core.cumpleanos.models.ids.ListaPreId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "LISTAPRE", indexes = {
        @Index(name = "LISTAPRE_UIDX1", columnList = "LPR_NOMBRE, LPR_EMPRESA", unique = true),
        @Index(name = "LISTAPRE_UIDX2", columnList = "LPR_ID, LPR_EMPRESA", unique = true),
        @Index(name = "LISTAPRE_NIDX1", columnList = "LPR_EMPRESA"),
        @Index(name = "LISTAPRE_NIDX2", columnList = "LPR_MONEDA, LPR_EMPRESA")
})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
        "sistema"
})
public class ListaPre {

    @EmbeddedId
    private ListaPreId id;

    @Size(max = 100)
    @NotNull
    @Column(name = "LPR_NOMBRE", nullable = false, length = 100)
    private String lprNombre;

    @Size(max = 10)
    @NotNull
    @Column(name = "LPR_ID", nullable = false, length = 10)
    private String lprId;

    @Column(name = "LPR_INACTIVO")
    private Boolean lprInactivo;

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

    @Column(name = "LPR_NUMERO")
    private Long lprNumero;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "LPR_SUPERVISOR", nullable = false)
    private Boolean lprSupervisor = false;

    @ColumnDefault("0")
    @Column(name = "LPR_DECLARAR")
    private Boolean lprDeclarar;

    @Column(name = "LPR_PRECIO1")
    private Boolean lprPrecio1;

    @Column(name = "LPR_PRECIO2")
    private Boolean lprPrecio2;

    @Column(name = "LPR_PRECIO3")
    private Boolean lprPrecio3;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LPR_EMPRESA", referencedColumnName = "SIS_CODIGO", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Sistema sistema;

    /*@JsonBackReference
    @OneToMany(mappedBy = "listaPre", fetch = FetchType.LAZY)
    private Set<Almacen> almacenes = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "listaPre", fetch = FetchType.LAZY)
    private Set<CatCliente> catClientes = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "listaPre", fetch = FetchType.LAZY)
    private Set<CcomFac> ccomFacs = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "listaPre", fetch = FetchType.LAZY)
    private Set<Cliente> clientes = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "listaPre", fetch = FetchType.LAZY)
    private Set<Dfactura> dfacturas = new LinkedHashSet<>();*/
}
