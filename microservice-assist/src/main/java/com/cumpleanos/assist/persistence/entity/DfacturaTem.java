package com.cumpleanos.assist.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "DFACTURA_TEM")
public class DfacturaTem {

    @Column(name = "DFAC_EMPRESA", nullable = false)
    private Long dfacEmpresa;

    @Id
    @Column(name = "DFAC_CFAC_COMPROBA", nullable = false)
    private Long dfacCfacComproba;

    @Column(name = "DFAC_SECUENCIA", nullable = false)
    private Long dfacSecuencia;

    @Column(name = "DFAC_PRODUCTO")
    private Long dfacProducto;

    @Column(name = "DFAC_CUENTA")
    private Long dfacCuenta;

    @Column(name = "DFAC_ACTIVO")
    private Long dfacActivo;

    @Column(name = "DFAC_CATPRODUCTO")
    private Long dfacCatproducto;

    @Column(name = "DFAC_CANTIDAD", nullable = false, precision = 17, scale = 4)
    private BigDecimal dfacCantidad;

    @Column(name = "DFAC_CANAPR", nullable = false, precision = 17, scale = 4)
    private BigDecimal dfacCanapr;

    @Column(name = "DFAC_PRECIO", nullable = false, precision = 20, scale = 7)
    private BigDecimal dfacPrecio;

    @Column(name = "DFAC_DESCUENTO", precision = 5, scale = 2)
    private BigDecimal dfacDescuento;

    @Column(name = "DFAC_BODEGA", nullable = false)
    private Long dfacBodega;

    @Column(name = "DFAC_TOTAL", nullable = false, precision = 17, scale = 4)
    private BigDecimal dfacTotal;

    @Column(name = "DFAC_CANENT", nullable = false, precision = 17, scale = 4)
    private BigDecimal dfacCanent;

    @Column(name = "DFAC_CANDEV", nullable = false, precision = 17, scale = 4)
    private BigDecimal dfacCandev;

    @Column(name = "DFAC_CANRES", nullable = false, precision = 17, scale = 4)
    private BigDecimal dfacCanres;

    @Column(name = "DFAC_DSCITEM", nullable = false, precision = 17, scale = 4)
    private BigDecimal dfacDscitem;

    @Column(name = "DFAC_TRAITEM", nullable = false, precision = 17, scale = 4)
    private BigDecimal dfacTraitem;

    @Column(name = "DFAC_COMBO")
    private Boolean dfacCombo;

    @Column(name = "CREA_USR", length = 10)
    private String creaUsr;

    @Column(name = "CREA_FECHA")
    private LocalDate creaFecha;

    @Column(name = "MOD_USR", length = 10)
    private String modUsr;

    @Column(name = "MOD_FECHA")
    private LocalDate modFecha;

    @Column(name = "DFAC_IVAITEM", nullable = false, precision = 20, scale = 7)
    private BigDecimal dfacIvaitem;

    @Column(name = "DFAC_GRABAIVA")
    private Boolean dfacGrabaiva;

    @Column(name = "DFAC_TEMPERATURA", precision = 5, scale = 2)
    private BigDecimal dfacTemperatura;

    @Column(name = "DFAC_GRADO", precision = 5, scale = 2)
    private BigDecimal dfacGrado;

    @Column(name = "DFAC_UDIGITADA")
    private Long dfacUdigitada;

    @Column(name = "DFAC_CDIGITADA", precision = 17, scale = 4)
    private BigDecimal dfacCdigitada;

    @Column(name = "DFAC_CENTRO")
    private Long dfacCentro;

    @Column(name = "DFAC_CAPRDIGITADA", precision = 17, scale = 4)
    private BigDecimal dfacCaprdigitada;

    @Column(name = "DFAC_PDIGITADO", precision = 20, scale = 7)
    private BigDecimal dfacPdigitado;

    @Column(name = "DFAC_GASITEM", precision = 17, scale = 4)
    private BigDecimal dfacGasitem;

    @Column(name = "DFAC_ICEITEM", precision = 17, scale = 4)
    private BigDecimal dfacIceitem;

    @Column(name = "DFAC_TLIQEVENTO")
    private Long dfacTliqevento;

    @Column(name = "DFAC_IDAUX", length = 20)
    private String dfacIdaux;

    @Column(name = "DFAC_CLIENTE")
    private Long dfacCliente;

    @Column(name = "DFAC_AGENTE")
    private Long dfacAgente;

    @Column(name = "DFAC_TIPOGASTO")
    private Long dfacTipogasto;

    @Column(name = "DFAC_TRANSACC")
    private Long dfacTransacc;

    @Column(name = "DFAC_ALMACEN")
    private Long dfacAlmacen;

    @Column(name = "DFAC_IMPITEM", precision = 20, scale = 7)
    private BigDecimal dfacImpitem;

    @Column(name = "DFAC_DDO_COMPROBA")
    private Long dfacDdoComproba;

    @Column(name = "DFAC_DDO_DOCTRAN", length = 30)
    private String dfacDdoDoctran;

    @Column(name = "DFAC_DDO_PAGO")
    private Long dfacDdoPago;

    @Column(name = "DFAC_DDO_TRANSACC")
    private Long dfacDdoTransacc;

    @Column(name = "DFAC_TOTAL1", precision = 17, scale = 4)
    private BigDecimal dfacTotal1;

    @Column(name = "DFAC_PRO_COMBO")
    private Long dfacProCombo;

    @Column(name = "DFAC_KILOMETRAJE", precision = 17, scale = 4)
    private BigDecimal dfacKilometraje;

    @Column(name = "DFAC_GASTO_VEHICULO")
    private Long dfacGastoVehiculo;

    @Column(name = "DFAC_LIQUIDA")
    private Boolean dfacLiquida;

    @Column(name = "DFAC_PEDIDO_IMP")
    private Long dfacPedidoImp;

    @Column(name = "DFAC_CONCE_IMP")
    private Long dfacConceImp;

    @Column(name = "DFAC_TIPO_LIQ_IMP")
    private Long dfacTipoLiqImp;

    @Column(name = "DFAC_GRUPO_IMP")
    private Long dfacGrupoImp;

    @Column(name = "DFAC_FACTURA")
    private Long dfacFactura;

    @Column(name = "DFAC_PROCEDENCIA")
    private Long dfacProcedencia;
}
