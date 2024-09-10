package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.BodegaId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "BODEGA")
@Data
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
    private Boolean directo;

    @Column(name = "BOD_FECHA_INICIO")
    private LocalDate fechaInicio;

    @Column(name = "BOD_FECHA_FINAL")
    private LocalDate fechaFinal;

    @Column(name = "BOD_PROMOCION")
    private Boolean promocion;

    @Column(name = "BOD_VER_CAL")
    private Boolean verCal;

    @Column(name = "BOD_TIPO")
    private Boolean tipo;

    @Column(name = "BOD_PROVEEDOR")
    private Boolean proveedor;

    @Column(name = "BOD_COMPRA")
    private Boolean compra;

    @Column(name = "BOD_MAYORISTA")
    private Boolean mayorista;

    @Column(name = "BOD_BODEGA_WEB")
    private Boolean bodegaWeb;

    @Column(name = "BOD_BODEGA_WEB_DEF")
    private Boolean bodegaWebDef;

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

    @OneToMany(mappedBy = "bodega")
    private Set<Agente> agentes= new LinkedHashSet<>();

    @OneToMany(mappedBy = "bodega")
    private Set<Almacen> almacenes= new LinkedHashSet<>();

    @OneToMany(mappedBy = "bodega")
    private Set<Ccomproba> bodega = new LinkedHashSet<>();

    @OneToMany(mappedBy = "bodega")
    private Set<Dfactura> dfactura = new LinkedHashSet<>();

}
