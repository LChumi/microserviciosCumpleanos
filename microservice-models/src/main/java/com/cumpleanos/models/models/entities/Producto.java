package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.ProductoId;
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
@Table(name = "PRODUCTO")
@Data
public class Producto {

    @EmbeddedId
    private ProductoId id;

    @Size(max = 20)
    @NotNull
    @Column(name = "PRO_ID", nullable = false, length = 20)
    private String proId;

    @Size(max = 100)
    @NotNull
    @Column(name = "PRO_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "PRO_INVENTARIO")
    private Boolean inventario;

    @Column(name = "PRO_CTA_INVENTARIO")
    private Long ctaInventario;

    @Column(name = "PRO_CTA_VENTA")
    private Long ctaVenta;

    @Column(name = "PRO_CTA_COSTO")
    private Long ctaCosto;

    @Column(name = "PRO_PORC_COMISION", precision = 5, scale = 2)
    private BigDecimal porcComision;

    @Column(name = "PRO_STOCK_MINIMO")
    private Integer stockMinimo;

    @Column(name = "PRO_STOCK_MAXIMO")
    private Integer stockMaximo;

    @Column(name = "PRO_IMPUESTO")
    private Boolean impuesto;

    @Column(name = "PRO_FECHA_LANZAM")
    private LocalDate fechaLanzam;

    @Column(name = "PRO_FECHA_VENCE")
    private LocalDate fechaVence;

    @NotNull
    @Column(name = "PRO_NIVEL", nullable = false)
    private Short nivel;

    @Column(name = "PRO_SERIE")
    private Boolean serie;

    @Column(name = "PRO_PESO", precision = 9, scale = 2)
    private BigDecimal peso;

    @Column(name = "PRO_INACTIVO")
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

    @Column(name = "PRO_UBICACION")
    private Long ubicacion;

    @Column(name = "PRO_ULT_COSTO", precision = 23, scale = 10)
    private BigDecimal ultCosto;

    @Column(name = "PRO_ALCOHOL")
    private Boolean alcohol;

    @Column(name = "PRO_GRADO", precision = 17, scale = 4)
    private BigDecimal grado;

    @ColumnDefault("0")
    @Column(name = "PRO_CRITICO")
    private Boolean critico;

    @Column(name = "PRO_TRANSPORTE")
    private Boolean transporte;

    @Size(max = 10)
    @Column(name = "PRO_ICE1", length = 10)
    private String ice1;

    @Size(max = 10)
    @Column(name = "PRO_ICE2", length = 10)
    private String ice2;

    @Size(max = 10)
    @Column(name = "PRO_ICE3", length = 10)
    private String ice3;

    @Size(max = 10)
    @Column(name = "PRO_ICE4", length = 10)
    private String ice4;

    @Size(max = 10)
    @Column(name = "PRO_ICE5", length = 10)
    private String ice5;

    @Column(name = "PRO_BUFFER")
    private Boolean buffer;

    @Size(max = 50)
    @Column(name = "PRO_ID1", length = 50)
    private String proId1;

    @Column(name = "PRO_CTA_INVENTARIO1")
    private Long ctaInventario1;

    @Column(name = "PRO_CTA_VENTA1")
    private Long ctaVenta1;

    @Column(name = "PRO_CTA_COSTO1")
    private Long ctaCosto1;

    @Column(name = "PRO_PROCEDENCIA")
    private Boolean procedencia;

    @Column(name = "PRO_DETALLE")
    private Short detalle;

    @Column(name = "PRO_GRUPO_ORDEN")
    private Long grupoOrden;

    @Column(name = "PRO_CODIGO_ORDEN")
    private Long codigoOrden;

    @Size(max = 1)
    @Column(name = "PRO_CAMBIA_PRECIO_YN", length = 1)
    private String cambiaPrecioYn;

    @Column(name = "PRO_TALLA")
    private Long talla;

    @Size(max = 1)
    @Column(name = "PRO_CALIFICACION", length = 1)
    private String calificacion;

    @Column(name = "PRO_GARANTIA")
    private Short garantia;

    @Column(name = "PRO_TIPOEMBALA")
    private Long tipoembala;

    @Column(name = "PRO_TIEMPO")
    private Short tiempo;

    @Column(name = "PRO_PORC_SEGURIDAD", precision = 5, scale = 2)
    private BigDecimal porcSeguridad;

    @Size(max = 50)
    @Column(name = "PRO_ABREVIATURA", length = 50)
    private String abreviatura;

    @Size(max = 20)
    @Column(name = "PRO_CODBARRAS", length = 20)
    private String codbarras;

    @Column(name = "PRO_ESTRUCTURA")
    private Long estructura;

    @Column(name = "PRO_CLASE")
    private Long clase;

    @Column(name = "PRO_DESTINO")
    private Long destino;

    @Column(name = "PRO_CLIENTE")
    private Long cliente;

    @Column(name = "PRO_UMEDIDA_FAB")
    private Long umedidaFab;

    @Column(name = "PRO_PATRON_RUTA")
    private Long patronRuta;

    @Column(name = "PRO_LPRODUCTO")
    private Long lproducto;

    @Column(name = "PRO_SLPRODUCTO")
    private Long slproducto;

    @Column(name = "PRO_CENTRO")
    private Long centro;

    @ColumnDefault("null")
    @Column(name = "PRO_LARGO")
    private Long largo;

    @ColumnDefault("null")
    @Column(name = "PRO_ANCHO")
    private Long ancho;

    @ColumnDefault("null")
    @Column(name = "PRO_ALTURA")
    private Long altura;

    @ColumnDefault("null")
    @Column(name = "PRO_PESO_BRUTO")
    private Long pesoBruto;

    @Column(name = "PRO_PESO_NETO")
    private Long pesoNeto;

    @Column(name = "PRO_PESO_ESPECIFICO")
    private Long pesoEspecifico;

    @Column(name = "PRO_PESO_TOTAL")
    private Long pesoTotal;

    @Column(name = "PRO_ESPESOR")
    private Long espesor;

    @Column(name = "PRO_DIAMETRO")
    private Long diametro;

    @Column(name = "PRO_SALIDA_GRANEL")
    private Boolean salidaGranel;

    @Column(name = "PRO_COSTEO")
    private Short costeo;

    @Column(name = "PRO_FOTO")
    private byte[] foto;

    @Column(name = "PRO_ESPECIFICA")
    private Long especifica;

    @Column(name = "PRO_LOTE_NP")
    private Boolean loteNp;

    @Column(name = "PRO_GS1CE")
    private Integer gs1ce;

    @Column(name = "PRO_TIPOSCRAP")
    private Long tiposcrap;

    @Column(name = "PRO_COSTOSCRAP", precision = 17, scale = 4)
    private BigDecimal costoscrap;

    @Column(name = "PRO_SECCION")
    private Long seccion;

    @Column(name = "PRO_PFCOLSEC")
    private Boolean pfcolsec;

    @Column(name = "PRO_MODPRODUCCION")
    private Long modproduccion;

    @Column(name = "PRO_MAQUILA")
    private Boolean maquila;

    @Size(max = 10)
    @Column(name = "PRO_MIGRADO", length = 10)
    private String migrado;

    @Column(name = "PRO_NOCONSUMO")
    private Boolean noconsumo;

    @Column(name = "PRO_GENERO")
    private Boolean genero;

    @Column(name = "PRO_ULT_FECHA_COMPRA")
    private LocalDate ultFechaCompra;

    @Column(name = "PRO_LINEA")
    private Long linea;

    @Column(name = "PRO_VALIDASTOCK")
    private Boolean validastock;

    @Column(name = "PRO_FOTO_S")
    private Boolean fotoS;

    @Column(name = "PRO_PRECIO1", precision = 17, scale = 4)
    private BigDecimal precio1;

    @Column(name = "PRO_PRECIO2", precision = 17, scale = 4)
    private BigDecimal precio2;

    @Column(name = "PRO_PRECIO3", precision = 17, scale = 4)
    private BigDecimal precio3;

    @Size(max = 100)
    @Column(name = "PRO_ALTO", length = 100)
    private String alto;

    @Size(max = 100)
    @Column(name = "PRO_LARGO1", length = 100)
    private String largo1;

    @Size(max = 100)
    @Column(name = "PRO_ANCHO1", length = 100)
    private String ancho1;

    @Size(max = 100)
    @Column(name = "PRO_CAPACIDAD", length = 100)
    private String capacidad;

    @Size(max = 100)
    @Column(name = "PRO_NOMBRE_ADUANA", length = 100)
    private String nombreAduana;

    @Column(name = "PRO_CARGA_WEB")
    private Boolean cargaWeb;

    @Column(name = "PRO_MINIMO_WEB", precision = 17, scale = 4)
    private BigDecimal minimoWeb;

    @Size(max = 20)
    @Column(name = "PRO_COD_ORDEN", length = 20)
    private String codOrden;

    @Column(name = "PRO_CARGA_EXTERNO")
    private Boolean cargaExterno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name ="PRO_PROVEEDOR", referencedColumnName = "CLI_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "PRO_EMPRESA", referencedColumnName = "CLI_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Cliente proveedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "PRO_GPRODUCTO", referencedColumnName = "GPR_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "PRO_EMPRESA", referencedColumnName = "GPR_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Gproducto gproducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "PRO_ENVASE", referencedColumnName = "PRO_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "PRO_EMPRESA", referencedColumnName = "PRO_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Producto envase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "PRO_UNIDAD", referencedColumnName = "UMD_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "PRO_EMPRESA", referencedColumnName = "UMD_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Umedida unidad;

    @OneToMany(mappedBy = "producto")
    private Set<Dfactura> dfacturas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "producto")
    private Set<Factor> factors = new LinkedHashSet<>();
}
