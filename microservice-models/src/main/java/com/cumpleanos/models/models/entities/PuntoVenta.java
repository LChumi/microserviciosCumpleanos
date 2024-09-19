package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.PuntoVentaId;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "PUNTOVENTA", indexes = {
        @Index(name = "PUNTOVENTA_UIDX1", columnList = "PVE_NOMBRE, PVE_ALMACEN, PVE_EMPRESA", unique = true),
        @Index(name = "PUNTOVENTA_UIDX2", columnList = "PVE_ID, PVE_ALMACEN, PVE_EMPRESA", unique = true),
        @Index(name = "PUNTOVENTA_NIDX2", columnList = "PVE_ALMACEN, PVE_EMPRESA"),
        @Index(name = "PUNTOVENTA_NIDX3", columnList = "PVE_CADAGENTE, PVE_EMPRESA"),
        @Index(name = "PUNTOVENTA_NIDX1", columnList = "PVE_AGENTE, PVE_EMPRESA")
})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
        "cadAgente", "agente", "almacen", "accesos", "ccomprobas"
})
public class PuntoVenta {

    @EmbeddedId
    private PuntoVentaId id;

    @Size(max = 100)
    @NotNull
    @Column(name = "PVE_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Size(max = 10)
    @NotNull
    @Column(name = "PVE_ID", nullable = false, length = 10)
    private String pveId;

    @Size(max = 100)
    @Column(name = "PVE_RESPONSABLE", length = 100)
    private String responsable;

    @Column(name = "PVE_INACTIVO")
    private Boolean inactivo;

    @Column(name = "PVE_CENTRO")
    private Long centro;

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

    @Column(name = "PVE_FECHAVALIDEZ")
    private LocalDate fechavalidez;

    @Size(max = 15)
    @Column(name = "PVE_NROAUTORIZA", length = 15)
    private String nroautoriza;

    @Column(name = "PVE_MULTIAGENTE")
    private Short multiagente;

    @Size(max = 10)
    @Column(name = "PVE_MARCA", length = 10)
    private String marca;

    @Size(max = 15)
    @Column(name = "PVE_MODELO", length = 15)
    private String modelo;

    @Size(max = 20)
    @Column(name = "PVE_SERIE", length = 20)
    private String serie;

    @Column(name = "PVE_ELECTRONICO")
    private Boolean electronico;

    @ColumnDefault("1")
    @Column(name = "PVE_REPORTE")
    private Boolean reporte;

    @ColumnDefault("1")
    @Column(name = "PVE_IMPRESORA")
    private Boolean impresora;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "PVE_CADAGENTE", referencedColumnName = "CAD_CODIGO" ,insertable = false,updatable = false),
            @JoinColumn(name = "PVE_EMPRESA", referencedColumnName = "CAD_EMPRESA", insertable = false,updatable = false),
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private CadAgente cadAgente;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "PVE_AGENTE", referencedColumnName = "AGE_CODIGO", insertable = false,updatable = false),
            @JoinColumn(name = "PVE_EMPRESA", referencedColumnName = "AGE_EMPRESA", insertable = false,updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Agente agente;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "PVE_ALMACEN", referencedColumnName = "ALM_CODIGO", insertable = false,updatable = false),
            @JoinColumn(name = "PVE_EMPRESA", referencedColumnName = "ALM_EMPRESA", insertable = false,updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Almacen almacen;

    @JsonBackReference
    @OneToMany(mappedBy = "puntoVenta", fetch = FetchType.LAZY)
    private Set<Acceso> accesos = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "puntoVenta", fetch = FetchType.LAZY)
    private Set<Ccomproba> ccomprobas = new LinkedHashSet<>();
}

