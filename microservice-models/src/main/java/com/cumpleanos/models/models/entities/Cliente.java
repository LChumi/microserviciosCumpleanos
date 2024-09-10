package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.ClienteId;
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
@Table(name = "CLIENTE")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
        "catCliente", "tipCliente", "ciudad", "parroquia", "agente", "cliente", "politica", "politicaAdi", "listaPre", "agente2", "almacenes", "ccomprobas", "ccomprobas1", "clientes", "dfacturas", "productos", "totales"
})
public class Cliente {

    @EmbeddedId
    private ClienteId id;

    @Size(max = 10)
    @NotNull
    @Column(name = "CLI_ID", nullable = false, length = 10)
    private String cliId;

    @NotNull
    @Column(name = "CLI_TIPO", nullable = false)
    private Boolean tipo = false;

    @Size(max = 10)
    @Column(name = "CLI_TITULO", length = 10)
    private String titulo;

    @Size(max = 100)
    @NotNull
    @Column(name = "CLI_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Size(max = 20)
    @Column(name = "CLI_RUC_CEDULA", length = 20)
    private String rucCedula;

    @Size(max = 100)
    @Column(name = "CLI_DIRECCION", length = 100)
    private String direccion;

    @Size(max = 20)
    @Column(name = "CLI_TELEFONO1", length = 20)
    private String telefono1;

    @Size(max = 20)
    @Column(name = "CLI_TELEFONO2", length = 20)
    private String telefono2;

    @Size(max = 20)
    @Column(name = "CLI_TELEFONO3", length = 20)
    private String telefono3;

    @Size(max = 20)
    @Column(name = "CLI_FAX", length = 20)
    private String fax;

    @Size(max = 100)
    @Column(name = "CLI_APART_POSTAL", length = 100)
    private String apartPostal;

    @Size(max = 100)
    @Column(name = "CLI_MAIL", length = 100)
    private String mail;

    @Column(name = "CLI_CUENTA_DEB")
    private Long cuentaDeb;

    @Column(name = "CLI_CUENTA_CRE")
    private Long cuentaCre;

    @Column(name = "CLI_IMPUESTOS")
    private Boolean impuestos;

    @Size(max = 100)
    @Column(name = "CLI_CONTACTO", length = 100)
    private String contacto;

    @Size(max = 150)
    @Column(name = "CLI_REF_COMERCIAL", length = 150)
    private String refComercial;

    @Size(max = 150)
    @Column(name = "CLI_REF_BANCARIA", length = 150)
    private String refBancaria;

    @Column(name = "CLI_INACTIVO")
    private Boolean inactivo;

    @Column(name = "CLI_BLOQUEO")
    private Boolean bloqueo;

    @Column(name = "CLI_TARJETA")
    private Boolean tarjeta;

    @Column(name = "CLI_CUPO", precision = 17, scale = 4)
    private BigDecimal cupo;

    @Column(name = "CLI_ILIMITADO")
    private Boolean ilimitado;

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

    @Column(name = "CLI_TIPOCED")
    private Boolean tipoced;

    @Column(name = "CLI_ORDEN")
    private Integer orden;

    @Column(name = "CLI_CONTRIBUYENTE")
    private Boolean contribuyente;

    @Column(name = "CLI_FECHAING")
    private LocalDate fechaing;

    @Column(name = "CLI_PUENTE")
    private Boolean puente;

    @Column(name = "CLI_VISUALIZA")
    private Boolean visualiza;

    @Column(name = "CLI_ICENOIVA")
    private Boolean icenoiva;

    @Column(name = "CLI_TIPOPER")
    private Boolean tipoper;

    @Size(max = 100)
    @Column(name = "CLI_NOMBRECOM", length = 100)
    private String nombrecom;

    @Column(name = "CLI_CUENTACLA")
    private Boolean cuentacla;

    @Size(max = 20)
    @Column(name = "CLI_NICK", length = 20)
    private String nick;

    @Column(name = "CLI_POTENCIAL")
    private Boolean potencial;

    @Column(name = "CLI_DOCUMENTO_ANULADO")
    private Boolean documentoAnulado;

    @Column(name = "CLI_ABIERTO")
    private Boolean abierto;

    @Column(name = "CLI_ESTABLECIMIENTO")
    private Boolean establecimiento;

    @Column(name = "CLI_CUENTA_DEB1")
    private Long cuentaDeb1;

    @Column(name = "CLI_CUENTA_CRE1")
    private Long cuentaCre1;

    @Column(name = "CLI_ALMACEN")
    private Long almacen;

    @Column(name = "CLI_RISE")
    private Boolean rise;

    @Column(name = "CLI_DIAS_ENTREGA")
    private Short diasEntrega;

    @Column(name = "CLI_MAQUILA")
    private Boolean maquila;

    @Column(name = "CLI_GENERO")
    private Boolean genero;

    @Column(name = "CLI_ESTADO_CIVIL")
    private Boolean estadoCivil;

    @Column(name = "CLI_ORIGEN_ING")
    private Boolean origenIng;

    @Column(name = "CLI_FORMAPAGO")
    private Long formapago;

    @Size(max = 16)
    @Column(name = "CLI_CLAVE", length = 16)
    private String clave;

    @Column(name = "CLI_RETIENE")
    private Boolean retiene;

    @Column(name = "CLI_TPA_RETIVA")
    private Long retiva;

    @Column(name = "CLI_TIPO_CONTRIBUYENTE")
    private Boolean tipoContribuyente;

    @Column(name = "CLI_FECHA_NAC")
    private LocalDate fechaNac;

    @Size(max = 30)
    @Column(name = "CLI_SOLCRE", length = 30)
    private String solcre;

    @Size(max = 50)
    @Column(name = "CLI_TELF_CONTACTO", length = 50)
    private String telfContacto;

    @Column(name = "CLI_IMPUESTO_DEF")
    private Long impuestoDef;

    @Column(name = "CLI_CUENTA_DEF")
    private Long cuentaDef;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CLI_CATEGORIA", referencedColumnName = "CAT_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CLI_EMPRESA", referencedColumnName = "CAT_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private CatCliente catCliente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CLI_TIPOCLI", referencedColumnName = "TCL_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CLI_EMPRESA", referencedColumnName = "TCL_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private TipCliente tipCliente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CLI_CIUDAD", referencedColumnName = "UBI_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CLI_EMPRESA", referencedColumnName = "UBI_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Ubicacion ciudad;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CLI_PARROQUIA", referencedColumnName = "UBI_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CLI_EMPRESA", referencedColumnName = "UBI_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Ubicacion parroquia;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CLI_AGENTE", referencedColumnName = "AGE_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CLI_EMPRESA", referencedColumnName = "AGE_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Agente agente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CLI_REPORTA", referencedColumnName = "CLI_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CLI_EMPRESA", referencedColumnName = "CLI_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CLI_POLITICAS", referencedColumnName = "POL_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CLI_EMPRESA", referencedColumnName = "POL_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Politica politica;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CLI_POLITICAS_ADI", referencedColumnName = "POL_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CLI_EMPRESA", referencedColumnName = "POL_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Politica politicaAdi;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CLI_LISTAPRE", referencedColumnName = "LPR_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CLI_EMPRESA", referencedColumnName = "LPR_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private ListaPre listaPre;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "CLI_AGENTE2", referencedColumnName = "AGE_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CLI_EMPRESA", referencedColumnName = "AGE_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Agente agente2;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<Almacen> almacenes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<Ccomproba> ccomprobas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "clientePro", fetch = FetchType.LAZY)
    private Set<Ccomproba> ccomprobas1 = new LinkedHashSet<>();

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<Cliente> clientes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<Dfactura> dfacturas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "proveedor", fetch = FetchType.LAZY)
    private Set<Producto> productos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "transportista", fetch = FetchType.LAZY)
    private Set<Total> totales = new LinkedHashSet<>();
}
