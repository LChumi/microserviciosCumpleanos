package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.ListaPreId;
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
@Table(name = "LISTAPRE")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
        "sistema", "almacenes", "catClientes", "ccomFacs", "clientes", "dfacturas"
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LPR_EMPRESA", referencedColumnName = "SIS_CODIGO", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Sistema sistema;

    @OneToMany(mappedBy = "listaPre", fetch = FetchType.LAZY)
    private Set<Almacen> almacenes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "listaPre", fetch = FetchType.LAZY)
    private Set<CatCliente> catClientes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "listaPre", fetch = FetchType.LAZY)
    private Set<CcomFac> ccomFacs = new LinkedHashSet<>();

    @OneToMany(mappedBy = "listaPre", fetch = FetchType.LAZY)
    private Set<Cliente> clientes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "listaPre", fetch = FetchType.LAZY)
    private Set<Dfactura> dfacturas = new LinkedHashSet<>();
}
