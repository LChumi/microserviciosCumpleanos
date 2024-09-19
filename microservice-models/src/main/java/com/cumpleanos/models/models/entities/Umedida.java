package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.UmedidaId;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "UMEDIDA", indexes = {
        @Index(name = "UMEDIDA_UDX1", columnList = "UMD_NOMBRE, UMD_EMPRESA", unique = true),
        @Index(name = "UMEDIDA_UDX2", columnList = "UMD_ID, UMD_EMPRESA", unique = true),
        @Index(name = "UMEDIDA_NIDX1", columnList = "UMD_EMPRESA")
})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
        "sistema", "catClientes", "dfacturas", "factores", "productos"
})
public class Umedida {

    @EmbeddedId
    private UmedidaId id;

    @Size(max = 100)
    @NotNull
    @Column(name = "UMD_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Size(max = 10)
    @NotNull
    @Column(name = "UMD_ID", nullable = false, length = 10)
    private String umdId;

    @Column(name = "UMD_INACTIVO")
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

    @Size(max = 10)
    @Column(name = "UMD_ICE1", length = 10)
    private String ice1;

    @Size(max = 10)
    @Column(name = "UMD_SSI", length = 10)
    private String ssi;

    @Size(max = 20)
    @Column(name = "UMD_NSSI", length = 20)
    private String nssi;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UMD_EMPRESA", referencedColumnName = "SIS_CODIGO", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Sistema sistema;

    @JsonBackReference
    @OneToMany(mappedBy = "umedida", fetch = FetchType.LAZY)
    private Set<CatCliente> catClientes = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "umedida", fetch = FetchType.LAZY)
    private Set<Dfactura> dfacturas = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "umedida", fetch = FetchType.LAZY)
    private Set<Factor> factores = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "unidad", fetch = FetchType.LAZY)
    private Set<Producto> productos = new LinkedHashSet<>();

}
