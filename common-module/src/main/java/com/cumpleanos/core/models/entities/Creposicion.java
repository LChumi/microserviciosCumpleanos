package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.CreposicionId;
import jakarta.persistence.*;
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
@Getter
@Setter
@ToString(exclude = {})
@EqualsAndHashCode(of = "id")
@Table(name = "CREPOSICION", indexes = {
        @Index(name = "CREPOSICION_UIDX7", columnList = "CRP_CCO_COMPROBA, CRP_EMP_REFER"),
        @Index(name = "CREPOSICION_UIDX2", columnList = "CRP_ALMACEN, CRP_EMPRESA"),
        @Index(name = "CREPOSICION_UIDX3", columnList = "CRP_BODEGA, CRP_EMPRESA"),
        @Index(name = "CREPOSICION_NIDX1", columnList = "CRP_GONDOLA, CRP_EMPRESA"),
        @Index(name = "CREPOSICION_UIDX5", columnList = "CRP_CLIENTE, CRP_EMPRESA"),
        @Index(name = "CREPOSICION_UIDX6", columnList = "CRP_PRODUCTO, CRP_EMPRESA"),
        @Index(name = "CREPOSICION_UIDX1", columnList = "CRP_EMPLEADO, CRP_EMPRESA")
})
public class Creposicion {

    @EmbeddedId
    private CreposicionId id;

    @Column(name = "CRP_USUARIO", nullable = false, length = 50)
    private String usuario;

    @Column(name = "CRP_OBSERVACION", length = 200)
    private String observacion;

    @Column(name = "CRP_FECHA")
    private LocalDate fecha;

    @Column(name = "CRP_ESTADO")
    private Boolean estado;

    @Column(name = "CRP_FINALIZADO")
    private Boolean finalizado;

    @Column(name = "CRP_USR_LIQUIDA")
    private Long usrLiquida;

    @Column(name = "CRP_TIPO")
    private Long tipo;

    @ColumnDefault("0")
    @Column(name = "CRP_URGENTE")
    private Boolean urgente;

    @Column(name = "CRP_CLI_DIR_ENTREGA", length = 100)
    private String cliDirEntrega;

    @Column(name = "CRP_LATITUD", precision = 10, scale = 8)
    private BigDecimal latitud;

    @Column(name = "CRP_LONGITUD", precision = 10, scale = 8)
    private BigDecimal longitud;

    @Column(name = "CRP_SUBTOTAL", precision = 17, scale = 4)
    private BigDecimal subtotal;

    @Column(name = "CRP_DESCUENTO", precision = 17, scale = 4)
    private BigDecimal descuento;

    @Column(name = "CRP_PORCIMPUESTO", precision = 17, scale = 4)
    private BigDecimal porcimpuesto;

    @Column(name = "CRP_VAL_IMPUESTO", precision = 17, scale = 4)
    private BigDecimal valImpuesto;

    @Column(name = "CRP_TOTAL", precision = 17, scale = 4)
    private BigDecimal total;

    @Column(name = "CRP_SUBTOTAL_0", precision = 17, scale = 4)
    private BigDecimal subtotal0;

    @Column(name = "CRP_DESCUENTO_0", precision = 17, scale = 4)
    private BigDecimal descuento0;

    @Column(name = "CRP_UBICACION", length = 500)
    private String ubicacion;

    @Column(name = "CRP_REFERENCIA", length = 100)
    private String referencia;

    @Column(name = "CRP_TRANSPORTE", precision = 17, scale = 4)
    private BigDecimal transporte;

    @Column(name = "CRP_SECUENCIA")
    private Long secuencia;

    @Column(name = "CRP_NRO_SOLICITUD", length = 100)
    private String nroSolicitud;

    @Column(name = "CRP_NRO_FACTURA", length = 100)
    private String nroFactura;

    @Column(name = "CRP_PESO", precision = 17, scale = 4)
    private BigDecimal peso;

    @ColumnDefault("0")
    @Column(name = "CRP_ESTADO_GAR")
    private Short estadoGar;

    @Column(name = "CRP_TRANSPORTISTA", length = 200)
    private String transportista;

    @Column(name = "CRP_TIPO_TRANSPORTE")
    private Boolean tipoTransporte;

    @Column(name = "CRP_FECHA_FAC")
    private LocalDate pFechaFac;

    @Column(name = "CRP_LINK_DATOS", length = 100)
    private String linkDatos;

    @Column(name = "CRP_LINK_FACTURA", length = 100)
    private String linkFactura;

    @Column(name = "CRP_LINK_FOTO", length = 100)
    private String linkFoto;

    @Column(name = "CRP_TIPO_RETIRO")
    private Boolean tipoRetiro;

    @Column(name = "CRP_REFERENCIA_ENTREGA", length = 300)
    private String referenciaEntrega;

    @Column(name = "CRP_CODIGO_POSTAL", length = 100)
    private String codigoPostal;

    @Column(name = "CRP_LOG_ERROR", length = 3000)
    private String logError;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CRP_CCO_COMPROBA", referencedColumnName = "CCO_CODIGO"),
            @JoinColumn(name = "CRP_EMP_REFER", referencedColumnName = "CCO_EMPRESA")
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Ccomproba ccomproba;

    @Column(name = "CRP_EMPLEADO")
    private Long empleadoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CRP_EMPLEADO", referencedColumnName = "EMP_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CRP_EMPRESA", referencedColumnName = "EMP_EMPRESA", insertable = false, updatable = false)
    })
    private Empleado empleado;

    @Column(name = "CRP_BODEGA")
    private Long bodegaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CRP_BODEGA", referencedColumnName = "BOD_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CRP_EMPRESA", referencedColumnName = "BOD_EMPRESA", insertable = false, updatable = false)
    })
    private Bodega bodega;

    @Column(name = "CRP_GONDOLA")
    private Long gondolaId;

    @Column(name = "CRP_BODEGA_FIN")
    private Long bodegaFinId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CRP_BODEGA_FIN", referencedColumnName = "BOD_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CRP_EMPRESA", referencedColumnName = "BOD_EMPRESA", insertable = false, updatable = false)
    })
    private Bodega bodegaFin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CRP_EMPRESA_GRUPO", referencedColumnName = "EMG_CODIGO")
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private EmpresaGrupo empresaGrupo;

    @Column(name = "CRP_CLIENTE")
    private Long clienteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CRP_CLIENTE", referencedColumnName = "CLI_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CRP_EMPRESA", referencedColumnName = "CLI_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Cliente cliente;

    @Column(name = "CRP_PRODUCTO")
    private Long productoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CRP_PRODUCTO", referencedColumnName = "PRO_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CRP_EMPRESA", referencedColumnName = "PRO_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete (action = OnDeleteAction.RESTRICT)
    private Producto producto;

    @Column(name = "CRP_ALMACEN")
    private Long almacenId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "CRP_EMPRESA", referencedColumnName = "ALM_EMPRESA", insertable = false, updatable = false),
            @JoinColumn(name = "CRP_ALMACEN", referencedColumnName = "ALM_CODIGO", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Almacen almacen;

}
