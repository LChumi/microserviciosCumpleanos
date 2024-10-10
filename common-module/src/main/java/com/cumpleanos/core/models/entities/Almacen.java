package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.AlmacenId;
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
@Table(name = "ALMACEN", indexes = {
        @Index(name = "ALMACEN_UIDX1", columnList = "ALM_NOMBRE, ALM_EMPRESA", unique = true),
        @Index(name = "ALMACEN_UIDX2", columnList = "ALM_ID, ALM_EMPRESA", unique = true),
        @Index(name = "ALMACEN_NIDX3", columnList = "ALM_CIUDAD, ALM_EMPRESA"),
        @Index(name = "ALMACEN_NIDX6", columnList = "ALM_ZONA, ALM_EMPRESA"),
        @Index(name = "ALMACEN_NIDX5", columnList = "ALM_LISTAPRE, ALM_EMPRESA"),
        @Index(name = "ALMACEN_NIDX4", columnList = "ALM_CLI_VARIOS, ALM_EMPRESA"),
        @Index(name = "ALMACEN_NIDX2", columnList = "ALM_BODEGA, ALM_EMPRESA"),
        @Index(name = "ALMACEN_NIDX1", columnList = "ALM_AGENTE, ALM_EMPRESA")
})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
        "agente", "bodega", "cliente", "listaPre"
})
@SequenceGenerator(name = "ALMACEN_S_CODIGO", sequenceName = "ALMACEN_S_CODIGO",initialValue = 1, allocationSize = 1)
public class Almacen {

    @EmbeddedId
    private AlmacenId id;

    @NotNull
    @Column(name = "ALM_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Size(max = 10)
    @NotNull
    @Column(name = "ALM_ID", nullable = false, length = 10)
    private String almId;

    @Size(max = 100)
    @Column(name = "ALM_GERENTE", length = 100)
    private String gerente;

    @Size(max = 100)
    @Column(name = "ALM_DIRECCION", length = 100)
    private String direccion;

    @Size(max = 12)
    @Column(name = "ALM_TELEFONO1", length = 12)
    private String telefono1;

    @Size(max = 12)
    @Column(name = "ALM_TELEFONO2", length = 12)
    private String telefono2;

    @Size(max = 12)
    @Column(name = "ALM_TELEFONO3", length = 12)
    private String telefono3;

    @Size(max = 13)
    @Column(name = "ALM_RUC", length = 13)
    private String ruc;

    @Size(max = 12)
    @Column(name = "ALM_FAX", length = 12)
    private String fax;

    @Column(name = "ALM_CENTRO")
    private Long centro;

    @Column(name = "ALM_INACTIVO")
    private Boolean inactivo;

    @Column(name = "ALM_CTACAJA")
    private Long ctacaja;

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
    @Column(name = "ALM_BLOQUEO")
    private Boolean bloqueo;

    @Size(max = 3)
    @Column(name = "ALM_SUBFIJO", length = 3)
    private String subfijo;

    @Size(max = 10)
    @Column(name = "ALM_ID_REPORTE", length = 10)
    private String idReporte;

    @Column(name = "ALM_MONTO", precision = 17, scale = 4)
    private BigDecimal almMonto;

    @Column(name = "ALM_NUMERO")
    private Long numero;

    @Column(name = "ALM_CTACAJA1")
    private Long ctacaja1;

    @Column(name = "ALM_MATRIZ")
    private Boolean matriz;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "ALM_AGENTE", referencedColumnName = "AGE_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "ALM_EMPRESA", referencedColumnName = "AGE_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Agente agente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "ALM_BODEGA", referencedColumnName = "BOD_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "ALM_EMPRESA", referencedColumnName = "BOD_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Bodega bodega;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "ALM_CLI_VARIOS", referencedColumnName = "CLI_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "ALM_EMPRESA", referencedColumnName = "CLI_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "ALM_LISTAPRE", referencedColumnName = "LPR_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "ALM_EMPRESA", referencedColumnName = "LPR_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private ListaPre listaPre;
/*
    @JsonBackReference
    @OneToMany(mappedBy = "almacen", fetch = FetchType.LAZY)
    private Set<Agente> agentes = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "almacen", fetch = FetchType.LAZY)
    private Set<Bodega> bodegas = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "destino", fetch = FetchType.LAZY)
    private Set<Ccomproba> ccomproba = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "almacen", fetch = FetchType.LAZY)
    private Set<Ccomproba> ccomprobas = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "almacen", fetch = FetchType.LAZY)
    private Set<Dfactura> dfacturas = new LinkedHashSet<>();   */
}
