package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.AgenteId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "AGENTE")
@Data
public class Agente {

    @EmbeddedId
    private AgenteId id;

    @Size(max = 100)
    @NotNull
    @Column(name = "AGE_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Size(max = 10)
    @NotNull
    @Column(name = "AGE_ID", nullable = false, length = 10)
    private String ageId;

    @NotNull
    @Column(name = "AGE_PORC_COMISION", nullable = false, precision = 5, scale = 2)
    private BigDecimal porcComision;

    @Column(name = "AGE_EMPLEADO")
    private Long empleado;

    @ColumnDefault("0")
    @Column(name = "AGE_INACTIVO")
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

    @Column(name = "AGE_CLIENTE")
    private Long cliente;

    @Column(name = "AGE_CUEGASTO")
    private Long ageCuegasto;

    @Column(name = "AGE_DEPARTAMENTO")
    private Boolean departamento;

    @Size(max = 10)
    @Column(name = "AGE_CEDULA", length = 10)
    private String cedula;

    @Column(name = "AGE_REPPRE")
    private Boolean reppre;

    @Column(name = "AGE_REPCOB")
    private Boolean repcob;

    @Column(name = "AGE_REPSAL")
    private Boolean repsal;

    @Column(name = "AGE_DIAS_COMISION", precision = 17, scale = 4)
    private BigDecimal diasComision;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "AGE_EMPRESA", referencedColumnName = "BOD_EMPRESA", insertable = false, updatable = false),
            @JoinColumn(name = "AGE_BODEGA" , referencedColumnName = "BOD_CODIGO", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Bodega bodega;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "AGE_EMPRESA", referencedColumnName = "ALM_EMPRESA", insertable = false, updatable = false),
            @JoinColumn(name = "AGE_ALMACEN", referencedColumnName = "ALM_CODIGO", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Almacen almacen;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "AGE_EMPRESA", referencedColumnName = "PVE_EMPRESA", insertable = false, updatable = false),
            @JoinColumn(name = "AGE_ALMACEN", referencedColumnName = "PVE_ALMACEN", insertable = false, updatable = false),
            @JoinColumn(name = "AGE_PVENTA", referencedColumnName = "PVE_SECUENCIA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private PuntoVenta puntoVenta;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "AGE_EMPRESA", referencedColumnName = "ALM_EMPRESA", insertable = false, updatable = false),
            @JoinColumn(name = "AGE_ALMACEN", referencedColumnName = "ALM_CODIGO", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Almacen almacen1;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "AGE_EMPRESA", referencedColumnName = "PVE_EMPRESA" , insertable = false, updatable = false),
            @JoinColumn(name = "AGE_ALMACEN", referencedColumnName = "PVE_ALMACEN", insertable = false, updatable = false),
            @JoinColumn(name = "AGE_PVENTA", referencedColumnName = "PVE_SECUENCIA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private PuntoVenta puntoVenta1;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AGE_USUARIO")
    private Usuario usuario;

    @OneToMany(mappedBy = "agente")
    private Set<Almacen> almacenes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "agente")
    private Set<CadAgente> cadAgentes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "agente")
    private Set<Ccomproba> ccomproba = new LinkedHashSet<>();

    @OneToMany(mappedBy = "chofer")
    private Set<Ccomproba> choferes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "agente")
    private Set<Cliente> clientes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "agente")
    private Set<Cliente> clientes2 = new LinkedHashSet<>();
}
