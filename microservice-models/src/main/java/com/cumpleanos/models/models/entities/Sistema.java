package com.cumpleanos.models.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "SISTEMA")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
        "empresaGrupo", "accesos", "autclientes", "gproductos", "lineas", "listaPre", "politicas", "tablaCoas", "tipClientes", "umedidas"
})
public class Sistema {

    @Id
    @Column(name = "SIS_CODIGO")
    private Long id;

    @Size(max = 10)
    @NotNull
    @Column(name = "SIS_ID", nullable = false, length = 10)
    private String sisId;

    @Size(max = 100)
    @NotNull
    @Column(name = "SIS_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "SIS_IMPUESTO_COMPRA")
    private Long impuestoCompra;

    @NotNull
    @Column(name = "SIS_DECIMAL", nullable = false)
    private Boolean decimal = false;

    @Column(name = "SIS_EXCLUSIVO")
    private Boolean exclusivo;

    @Size(max = 50)
    @Column(name = "SIS_CALLE", length = 50)
    private String calle;

    @Size(max = 50)
    @Column(name = "SIS_TRANSVER", length = 50)
    private String transver;

    @Size(max = 10)
    @Column(name = "SIS_NUMERO", length = 10)
    private String numero;

    @Size(max = 20)
    @Column(name = "SIS_TELEFONO1", length = 20)
    private String telefono1;

    @Size(max = 20)
    @Column(name = "SIS_TELEFONO2", length = 20)
    private String telefono2;

    @Size(max = 20)
    @Column(name = "SIS_TELEFONO3", length = 20)
    private String telefono3;

    @Column(name = "SIS_CIUDAD")
    private Long ciudad;

    @Size(max = 15)
    @Column(name = "SIS_CASILLA", length = 15)
    private String casilla;

    @Size(max = 50)
    @Column(name = "SIS_EMAIL", length = 50)
    private String email;

    @Size(max = 15)
    @Column(name = "SIS_RUC", length = 15)
    private String ruc;

    @Size(max = 100)
    @Column(name = "SIS_REPLEGAL", length = 100)
    private String replegal;

    @Size(max = 100)
    @Column(name = "SIS_DIRREPRE", length = 100)
    private String dirrepre;

    @Size(max = 20)
    @Column(name = "SIS_TELREPRE", length = 20)
    private String telrepre;

    @Column(name = "SIS_INACTIVO")
    private Boolean inactivo;

    @Column(name = "SIS_IMPUESTO_VENTA")
    private Long impuestoVenta;

    @Size(max = 15)
    @Column(name = "SIS_AUTORIZA", length = 15)
    private String autoriza;

    @Column(name = "SIS_FECHA_AUTO")
    private LocalDate fechaAuto;

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

    @Size(max = 100)
    @Column(name = "SIS_CONTADOR", length = 100)
    private String contador;

    @Size(max = 30)
    @Column(name = "SIS_NOMBRECORTO", length = 30)
    private String nombrecorto;

    @Size(max = 30)
    @Column(name = "SIS_FORMATOIMP", length = 30)
    private String formatoimp;

    @Column(name = "SIS_VALORACCION", precision = 17, scale = 4)
    private BigDecimal valoraccion;

    @Column(name = "SIS_SUBIRACCIONES")
    private Boolean subiracciones;

    @Size(max = 15)
    @Column(name = "SIS_AUTORIZA2", length = 15)
    private String autoriza2;

    @Column(name = "SIS_FECHA_AUTO2")
    private LocalDate fechaAuto2;

    @Column(name = "SIS_FECHA_INICIO")
    private LocalDate fechaInicio;

    @Column(name = "SIS_FECHA_FIN")
    private LocalDate fechaFin;

    @Size(max = 13)
    @Column(name = "SIS_RUC_REPLEGAL", length = 13)
    private String rucReplegal;

    @Size(max = 13)
    @Column(name = "SIS_RUC_CONTADOR", length = 13)
    private String rucContador;

    @Size(max = 1)
    @Column(name = "SIS_TIPOID_REPLEGAL", length = 1)
    private String tipoidReplegal;

    @Column(name = "SIS_IMP_VENTA_SIVA")
    private Long impVentaSiva;

    @Column(name = "SIS_IMP_COMPRA_SIVA")
    private Long impCompraSiva;

    @Column(name = "SIS_REPLICA_VENTA")
    private Boolean replicaVenta;

    @Column(name = "SIS_IMPRESION")
    private Boolean impresion;

    @Column(name = "SIS_RESOLUCION")
    private Short resolucion;

    @Size(max = 2)
    @Column(name = "SIS_CONTABILIDAD", length = 2)
    private String contabilidad;

    @Column(name = "SIS_AMBIENTE")
    private Long ambiente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SIS_EMPRESA_GRUPO", referencedColumnName = "EMG_CODIGO")
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private EmpresaGrupo empresaGrupo;

    @OneToMany(mappedBy = "sistema", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Acceso> accesos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "sistema", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Autcliente> autclientes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "sistema", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Gproducto> gproductos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "sistema", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Linea> lineas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "sistema", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<ListaPre> listaPre = new LinkedHashSet<>();

    @OneToMany(mappedBy = "sistema", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Politica> politicas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "sistema", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<TablaCoa> tablaCoas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "sistema", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<TipCliente> tipClientes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "sistema", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Umedida>  umedidas = new LinkedHashSet<>();
}
