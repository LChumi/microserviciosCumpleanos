package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.CatClienteId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "CATCLIENTE", indexes = {
        @Index(name = "CATCLIENTE_UDX2", columnList = "CAT_ID, CAT_EMPRESA, CAT_TIPO", unique = true),
        @Index(name = "CATCLIENTE_UDX1", columnList = "CAT_NOMBRE, CAT_EMPRESA", unique = true),
        @Index(name = "CATCLIENTE_IDX1", columnList = "CAT_CODIGO, CAT_REPORTA"),
        @Index(name = "CATCLIENTE_IDX2", columnList = "CAT_REPORTA, CAT_EMPRESA"),
        @Index(name = "CATCLIENTE_NIDX3", columnList = "CAT_UMEDIDA, CAT_EMPRESA"),
        @Index(name = "CATCLIENTE_NIDX2", columnList = "CAT_LISTAPRE, CAT_EMPRESA")
})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
        "reporta", "umedida", "listaPre"
})
@SequenceGenerator(name = "CATCLIENTE_S_CODIGO", sequenceName = "CATCLIENTE_S_CODIGO", allocationSize = 1)
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "CAT_REPORTA", referencedColumnName = "CAT_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CAT_EMPRESA", referencedColumnName = "CAT_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private CatCliente reporta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "CAT_UMEDIDA", referencedColumnName = "UMD_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CAT_EMPRESA", referencedColumnName = "UMD_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Umedida umedida;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "CAT_LISTAPRE", referencedColumnName = "LPR_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CAT_EMPRESA", referencedColumnName = "LPR_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private ListaPre listaPre;

    /*@JsonBackReference
    @OneToMany(mappedBy = "reporta", fetch = FetchType.LAZY)
    private Set<CatCliente> catClientes = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "catCliente", fetch = FetchType.LAZY)
    private Set<Ccomproba> ccomprobas = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "catCliente", fetch = FetchType.LAZY)
    private Set<Cliente> clientes = new LinkedHashSet<>();*/
}
