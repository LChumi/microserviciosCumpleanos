package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.CatClienteId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "CATCLIENTE")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
        "catCliente", "umedida", "listaPre", "catClientes", "clientes", "ccomprobas"
})
public class CatCliente {

    @EmbeddedId
    private CatClienteId id;

    @Size(max = 10)
    @NotNull
    @Column(name = "CAT_ID", nullable = false, length = 10)
    private String catId;

    @Size(max = 100)
    @NotNull
    @Column(name = "CAT_NOMBRE", nullable = false, length = 100)
    private String catNombre;

    @NotNull
    @Column(name = "CAT_ORDEN", nullable = false)
    private Long catOrden;

    @Column(name = "CAT_INACTIVO")
    private Boolean catInactivo;

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

    @NotNull
    @Column(name = "CAT_TIPO", nullable = false)
    private Boolean catTipo = false;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CAT_REPORTA", referencedColumnName = "CAT_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CAT_EMPRESA", referencedColumnName = "CAT_EMPRESA", insertable = false, updatable = false)
    })
    private CatCliente catCliente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CAT_UMEDIDA", referencedColumnName = "UMD_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CAT_EMPRESA", referencedColumnName = "UMD_EMPRESA", insertable = false, updatable = false)
    })
    private Umedida umedida;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CAT_LISTAPRE", referencedColumnName = "LPR_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CAT_EMPRESA", referencedColumnName = "LPR_EMPRESA", insertable = false, updatable = false)
    })
    private ListaPre listaPre;

    @OneToMany(mappedBy = "catCliente", fetch = FetchType.LAZY)
    private Set<CatCliente> catClientes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "catCliente", fetch = FetchType.LAZY)
    private Set<Ccomproba> ccomprobas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "catCliente", fetch = FetchType.LAZY)
    private Set<Cliente> clientes = new LinkedHashSet<>();

}
