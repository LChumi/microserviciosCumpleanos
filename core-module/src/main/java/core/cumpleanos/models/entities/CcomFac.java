package core.cumpleanos.models.entities;

import core.cumpleanos.models.ids.CcomFacId;
import jakarta.persistence.*;
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
@Table(name = "CCOMFAC", indexes = {
        @Index(name = "CCOMFAC_NIDX1", columnList = "CFAC_CCO_PEDIDO, CFAC_EMPRESA"),
        @Index(name = "CCOMFAC_NIDX11", columnList = "CFAC_OTR_COMPROBA, CFAC_EMPRESA"),
        @Index(name = "CCOMFAC_NIDX13", columnList = "CFAC_POLITICA, CFAC_EMPRESA"),
        @Index(name = "CCOMFAC_NIDX10", columnList = "CFAC_LISTA_PRECIOS, CFAC_EMPRESA"),
        @Index(name = "CCOMFAC_NIDX2", columnList = "CFAC_CCO_RECIBO, CFAC_EMPRESA"),
        @Index(name = "CCOMFAC_NIDX6", columnList = "CFAC_CIUDAD, CFAC_EMPRESA"),
        @Index(name = "CCOMFAC_NIDX14", columnList = "CFAC_PRIORIDAD"),
        @Index(name = "CCOMFAC_NIDX8", columnList = "CFAC_EMPLEADO, CFAC_EMPRESA"),
        @Index(name = "CCOMFAC_NIDX4", columnList = "CFAC_AREA, CFAC_EMPRESA"),
        @Index(name = "CCOMFAC_NIDX7", columnList = "CFAC_DEPARTAMENTO, CFAC_EMPRESA"),
        @Index(name = "CCOMFAC_NIDX9", columnList = "CFAC_IMPUESTO, CFAC_EMPRESA"),
        @Index(name = "CCOMFAC_IDX1", columnList = "CFAC_FACTURA, CFAC_ACL_TABLACOA, CFAC_ACL_RETDATO, CFAC_CCO_COMPROBA, CFAC_EMPRESA"),
        @Index(name = "CCOMFAC_IDX2", columnList = "CFAC_FACTURATRANS"),
        @Index(name = "CCOMFAC_NIDX17", columnList = "CFAC_TIPOEVENTO, CFAC_EMPRESA"),
        @Index(name = "CCOMFAC_NIDX18", columnList = "CFAC_TIPOGASTO, CFAC_EMPRESA"),
        @Index(name = "CCOMFAC_NIDX16", columnList = "CFAC_SISDISTRU, CFAC_EMPRESA"),
        @Index(name = "CCOMFAC_UIDX1", columnList = "CFAC_CONTROL_TEMP, CFAC_EST_ENTREGA, CFAC_CCO_COMPROBA, CFAC_EMPRESA"),
        @Index(name = "CCOMFAC_NIDX19", columnList = "CFAC_TIPO_BONO, CFAC_EMPRESA"),
        @Index(name = "CCOMFAC_NIDX", columnList = "CFAC_CCO_REFEREMP, CFAC_EMP_REFERENCIA"),
        @Index(name = "CCOMFAC_NIDX20", columnList = "CFAC_CONCEPTO_RET, CFAC_EMPRESA"),
        @Index(name = "CCOMFAC_NIDX21", columnList = "CFAC_OPR_CCOMPROBA, CFAC_EMPRESA")
})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
        "politica", "listaPre", "ccomproba", "pedido", "recibo", "produccion", "ubicacion"
})
public class CcomFac {

    @EmbeddedId
    private CcomFacId id;

    @Column(name = "CFAC_VIGENCIA")
    private LocalDate vigencia;

    @Column(name = "CFAC_AUTORIZA")
    private Boolean autoriza;

    @Column(name = "CFAC_EST_ENTREGA")
    private Boolean estEntrega;

    @Column(name = "CFAC_PROC_FAC")
    private Boolean procFac;

    @Column(name = "CFAC_PROCESO")
    private Boolean proceso;

    @Size(max = 100)
    @Column(name = "CFAC_NOMBRE", length = 100)
    private String nombre;

    @Size(max = 100)
    @Column(name = "CFAC_DIRECCION", length = 100)
    private String direccion;

    @Size(max = 30)
    @Column(name = "CFAC_TELEFONO", length = 30)
    private String telefono;

    @Size(max = 13)
    @Column(name = "CFAC_CED_RUC", length = 13)
    private String cedRuc;

    @Size(max = 10)
    @Column(name = "CFAC_ANU_USR", length = 10)
    private String anuUsr;

    @Column(name = "CFAC_ANU_FECHA")
    private LocalDate anuFecha;

    @Size(max = 10)
    @Column(name = "CFAC_APRO_USR", length = 10)
    private String aproUsr;

    @Column(name = "CFAC_APRO_FECHA")
    private LocalDate aproFecha;

    @Column(name = "CFAC_CON_CODIGO")
    private Long conCodigo;

    @Size(max = 1000)
    @Column(name = "CFAC_OBSERVACIONES", length = 1000)
    private String observaciones;

    @Column(name = "CFAC_TIPO_ACTPRO")
    private Boolean tipoActpro;

    @Column(name = "CFAC_SOL_COMPROBA")
    private Long solComproba;

    @Column(name = "CFAC_PORC_IMPUESTO", precision = 5, scale = 2)
    private BigDecimal porcImpuesto;

    @Size(max = 30)
    @Column(name = "CFAC_FACTURA", length = 30)
    private String factura;

    @Size(max = 30)
    @Column(name = "CFAC_FACTURATRANS", length = 30)
    private String facturatrans;

    @Column(name = "CFAC_FECHA_FAC")
    private LocalDate fechaFac;

    @Column(name = "CFAC_MONTORECIBO", precision = 17, scale = 4)
    private BigDecimal montorecibo;

    @Column(name = "CFAC_TIPOPAGO")
    private Long tipopago;

    @Size(max = 50)
    @Column(name = "CFAC_ACL_NROAUTORIZA", length = 50)
    private String aclNroautoriza;

    @Column(name = "CFAC_ACL_RETDATO")
    private Long aclRetdato;

    @Column(name = "CFAC_ACL_TABLACOA")
    private Long aclTablacoa;

    @Size(max = 10)
    @Column(name = "CFAC_DEVOLUCIONCOA", length = 10)
    private String devolucioncoa;

    @Size(max = 10)
    @Column(name = "CFAC_CREDITOCOA", length = 10)
    private String creditocoa;

    @Size(max = 10)
    @Column(name = "CFAC_SECUENCIACOA", length = 10)
    private String secuenciacoa;

    @Column(name = "CFAC_COMISION")
    private Short comision;

    @Column(name = "CFAC_PROVISION")
    private Boolean provision;

    @Column(name = "CFAC_PRORRATEO")
    private Boolean prorrateo;

    @Column(name = "CFAC_ESEVENTO")
    private Boolean esevento;

    @Column(name = "CFAC_ACTIVO")
    private Long activo;

    @Column(name = "CFAC_EMPRESA_ACT")
    private Long empresaAct;

    @Column(name = "CFAC_KILOMETRAJE_INI", precision = 17, scale = 4)
    private BigDecimal kilometrajeIni;

    @Column(name = "CFAC_KILOMETRAJE_FIN", precision = 17, scale = 4)
    private BigDecimal kilometrajeFin;

    @Column(name = "CFAC_CONTROL_TEMP")
    private Boolean controlTemp;

    @Column(name = "CFAC_USR_LIQUIDA")
    private Long usrLiquida;

    @Size(max = 300)
    @Column(name = "CFAC_OBSERVACIONES1", length = 300)
    private String observaciones1;

    @Size(max = 100)
    @Column(name = "CFAC_TRANS_NOMBRE", length = 100)
    private String transNombre;

    @Size(max = 13)
    @Column(name = "CFAC_TRANS_CED_RUC", length = 13)
    private String transCedRuc;

    @Column(name = "CFAC_EMP_REFERENCIA")
    private Long empReferencia;

    @Column(name = "CFAC_CCO_REFEREMP")
    private Long ccoReferemp;

    @Column(name = "CFAC_REPLICA")
    private Boolean replica;

    @ColumnDefault("0")
    @Column(name = "CFAC_EST_COM_ELECT")
    private Boolean estComElect;

    @Column(name = "CFAC_CLI_DIR_ENT")
    private Long cliDirEnt;

    @Size(max = 50)
    @Column(name = "CFAC_ACL_CLAVE", length = 50)
    private String aclClave;

    @Size(max = 100)
    @Column(name = "CFAC_ACL_MENSAJE", length = 100)
    private String aclMensaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CFAC_IMPUESTO", referencedColumnName = "IMP_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CFAC_EMPRESA", referencedColumnName = "IMP_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Impuesto impuesto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CFAC_POLITICA", referencedColumnName = "POL_CODIGO" ,insertable = false, updatable = false),
            @JoinColumn(name = "CFAC_EMPRESA", referencedColumnName = "POL_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Politica politica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CFAC_LISTA_PRECIOS", referencedColumnName = "LPR_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CFAC_EMPRESA", referencedColumnName = "LPR_EMPRESA",  insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private ListaPre listaPre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CFAC_CCO_COMPROBA", referencedColumnName = "CCO_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CFAC_EMPRESA", referencedColumnName = "CCO_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Ccomproba ccomproba;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CFAC_CCO_PEDIDO", referencedColumnName = "CCO_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CFAC_EMPRESA", referencedColumnName = "CCO_EMPRESA",  insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Ccomproba pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CFAC_CCO_RECIBO", referencedColumnName = "CCO_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CFAC_EMPRESA", referencedColumnName = "CCO_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Ccomproba recibo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CFAC_OPR_CCOMPROBA", referencedColumnName = "CCO_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CFAC_EMPRESA", referencedColumnName = "CCO_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Ccomproba produccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CFAC_CIUDAD", referencedColumnName = "UBI_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CFAC_EMPRESA", referencedColumnName = "UBI_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Ubicacion ubicacion;
}