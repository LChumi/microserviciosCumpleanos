package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.BodegaId;
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

import java.time.LocalDate;

@Entity
@Table(name = "BODEGA", indexes = {
        @Index(name = "BODEGA_UDX2", columnList = "BOD_ID, BOD_EMPRESA", unique = true),
        @Index(name = "BODEGA_UDX1", columnList = "BOD_NOMBRE, BOD_EMPRESA", unique = true),
        @Index(name = "BODEGA_NIDX3", columnList = "BOD_CIUDAD, BOD_EMPRESA"),
        @Index(name = "BODEGA_NIDX4", columnList = "BOD_ZONA, BOD_EMPRESA"),
        @Index(name = "BODEGA_NIDX2", columnList = "BOD_EMPLEADO, BOD_EMPRESA"),
        @Index(name = "BODEGA_NIDX1", columnList = "BOD_ALMACEN, BOD_EMPRESA"),
        @Index(name = "BODEGA_UDX3", columnList = "BOD_ALMACEN, BOD_PROBLEMAS, BOD_EMPRESA")
})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
        "centro", "ubicacion", "almacen"
})
@SequenceGenerator(name = "BODEGA_S_CODIGO", sequenceName = "BODEGA_S_CODIGO", allocationSize = 1)
public class Bodega {

    @EmbeddedId
    private BodegaId id;

    @Size(max = 10)
    @NotNull
    @Column(name = "BOD_ID", nullable = false, length = 10)
    private String bodId;

    @Size(max = 100)
    @NotNull
    @Column(name = "BOD_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "BOD_CONSIGNA")
    private Boolean consigna;

    @Size(max = 100)
    @Column(name = "BOD_UBICACION", length = 100)
    private String bodUbicacion;

    @Column(name = "BOD_INACTIVO")
    private Boolean inactivo;

    @Size(max = 100)
    @Column(name = "BOD_IMPRESORA", length = 100)
    private String impresora;

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
    @Column(name = "BOD_LIQUIDACION")
    private Boolean liquidacion = false;

    @ColumnDefault("0")
    @Column(name = "BOD_PROBLEMAS")
    private Boolean problemas = false;

    @Size(max = 50)
    @Column(name = "BOD_CUSTODIO", length = 50)
    private String custodio;

    @Column(name = "BOD_DIRECTO")
    private Short directo;

    @Column(name = "BOD_FECHA_INICIO")
    private LocalDate fechaInicio;

    @Column(name = "BOD_FECHA_FINAL")
    private LocalDate fechaFinal;

    @Column(name = "BOD_PROMOCION")
    private Short promocion;

    @Column(name = "BOD_VER_CAL")
    private Short verCal;

    @Column(name = "BOD_TIPO")
    private Short tipo;

    @Column(name = "BOD_PROVEEDOR")
    private Short proveedor;

    @Column(name = "BOD_COMPRA")
    private Short compra;

    @Column(name = "BOD_MAYORISTA")
    private Short mayorista;

    @Column(name = "BOD_BODEGA_WEB")
    private Short bodegaWeb;

    @Column(name = "BOD_BODEGA_WEB_DEF")
    private Short bodegaWebDef;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "BOD_CENTRO", referencedColumnName = "CEN_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "BOD_EMPRESA", referencedColumnName = "CEN_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Centro centro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "BOD_CIUDAD", referencedColumnName = "UBI_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "BOD_EMPRESA", referencedColumnName = "UBI_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Ubicacion ubicacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "BOD_ALMACEN", referencedColumnName = "ALM_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "BOD_EMPRESA", referencedColumnName = "ALM_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Almacen almacen;

    /*
    @JsonBackReference
    @OneToMany(mappedBy = "bodega", fetch = FetchType.LAZY)
    private Set<Agente> agentes= new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "bodega", fetch = FetchType.LAZY)
    private Set<Almacen> almacenes= new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "bodega", fetch = FetchType.LAZY)
    private Set<Ccomproba> bodegas = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "bodega", fetch = FetchType.LAZY)
    private Set<Dfactura> dfactura = new LinkedHashSet<>();*/
}
