package com.cumpleanos.models.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Table(name = "CCOMFAC")
@Data
public class CComfac {

    @Id
    @Column(name = "CFAC_CCO_COMPROBA")
    @Setter(AccessLevel.NONE)
    private BigInteger id;

    @Column(name = "CFAC_EMPRESA")
    private Long empresa;

    @Column(name = "CFAC_CCO_PEDIDO")
    private BigInteger ccoPedido;

    @Column(name = "CFAC_OTR_COMPROBA")
    private BigInteger otrComproba;

    @Column(name = "CFAC_VIGENCIA")
    private LocalDate vigencia;

    @Column(name = "CFAC_AUTORIZA")
    private Long autoriza;

    @Column(name = "CFAC_POLITICA")
    private Long politica;

    @Column(name = "CFAC_LISTA_PRECIOS")
    private Long listaPrecios;

    @Column(name = "CFAC_EST_ENTREGA")
    private Long estEntrega;

    @Column(name = "CFAC_PROC_FAC")
    private Long procFac;

    @Column(name = "CFAC_CCO_RECIBO")
    private BigInteger ccoRecibio;

    @Column(name = "CFAC_PROCESO")
    private Long proceso;

    @Column(name = "CFAC_NOMBRE")
    private String nombre;

    @Column(name = "CFAC_DIRECCION")
    private String direccion;

    @Column(name = "CFAC_TELEFONO")
    private String telefono;

    @Column(name = "CFAC_CED_RUC")
    private String cedRuc;

    @Column(name = "CFAC_CIUDAD")
    private Long ciudad;

    @Column(name = "CFAC_ANU_USR")
    private String anulaUsr;

    @Column(name = "CFAC_ANU_FECHA")
    private LocalDate anulaFecha;

    @Column(name = "CFAC_APRO_USR")
    private String aproUsr;

    @Column(name = "CFAC_APRO_FECHA")
    private LocalDate aproFecha;

    @Column(name = "CFAC_PRIORIDAD")
    private Long prioridad;

    @Column(name = "CFAC_EMPLEADO")
    private Long empleado;

    @Column(name = "CFAC_CON_CODIGO")
    private Long conCodigo;

    @Column(name = "CFAC_AREA")
    private Long area;

    @Column(name = "CFAC_DEPARTAMENTO")
    private Long departamento;

    @Column(name = "CFAC_OBSERVACIONES")
    private String observaciones;

    @Column(name = "CFAC_TIPO_ACTPRO")
    private Long tipoActpro;

    @Column(name = "CFAC_SOL_COMPROBA")
    private Long solComproba;

    @Column(name = "CFAC_IMPUESTO")
    private Long impuesto;

    @Column(name = "CFAC_PORC_IMPUESTO")
    private Long porcImmpuesto;

    @Column(name = "CFAC_FACTURA")
    private String factura;

    @Column(name = "CFAC_FACTURATRANS")
    private String facturatrans;

    @Column(name = "CFAC_TIPOEVENTO")
    private Long tipoVendido;

    @Column(name = "CFAC_TIPOGASTO")
    private Long tipoGasto;

    @Column(name = "CFAC_FECHA_FAC")
    private LocalDate fechaFac;

    @Column(name = "CFAC_MONTORECIBO")
    private BigDecimal montoRecibio;

    @Column(name = "CFAC_TIPOPAGO")
    private Long tipoPago;

    @Column(name = "CFAC_ACL_NROAUTORIZA")
    private String aclNroAutoriza;

    @Column(name = "CFAC_ACL_RETDATO")
    private Long aclRetdato;

    @Column(name = "CFAC_ACL_TABLACOA")
    private Long aclTablacoa;

    @Column(name = "CFAC_DEVOLUCIONCOA")
    private String devolucioncoa;

    @Column(name = "CFAC_CREDITOCOA")
    private String creditocoa;

    @Column(name = "CFAC_SECUENCIACOA")
    private String secuenciacoa;

    @Column(name = "CFAC_COMISION")
    private Long comision;

    @Column(name = "CFAC_PROVISION")
    private Long provision;

    @Column(name = "CFAC_PRORRATEO")
    private Long prorrateo;

    @Column(name = "CFAC_SISDISTRU")
    private Long sisdistru;

    @Column(name = "CFAC_ESEVENTO")
    private Long esEvento;

    @Column(name = "CFAC_ACTIVO")
    private Long activo;

    @Column(name = "CFAC_EMPRESA_ACT")
    private Long empresaAct;

    @Column(name = "CFAC_KILOMETRAJE_INI")
    private BigDecimal kilometrajeIni;

    @Column(name = "CFAC_KILOMETRAJE_FIN")
    private BigDecimal kilometrajeFin;

    @Column(name = "CFAC_CONTROL_TEMP")
    private Long controlTemp;

    @Column(name = "CFAC_USR_LIQUIDA")
    private Long usrLiquida;

    @Column(name = "CFAC_TIPO_BONO")
    private Long tipoBono;

    @Column(name = "CFAC_OBSERVACIONES1")
    private String observaciones1;

    @Column(name = "CFAC_TRANS_NOMBRE")
    private String transNombre;

    @Column(name = "CFAC_TRANS_CED_RUC")
    private String transCedRuc;

    @Column(name = "CFAC_EMP_REFERENCIA")
    private Long empReferencia;

    @Column(name = "CFAC_CCO_REFEREMP")
    private BigInteger ccoReferemp;

    @Column(name = "CFAC_REPLICA")
    private Long replica;

    @Column(name = "CFAC_CONCEPTO_RET")
    private Long conceptoRet;

    @Column(name = "CFAC_OPR_CCOMPROBA")
    private BigInteger oprCcomproba;

    @Column(name = "CFAC_EST_COM_ELECT")
    private Long estComElect;

    @Column(name = "CFAC_CLI_DIR_ENT")
    private String cliDirEnt;

    @Column(name = "CFAC_ACL_CLAVE")
    private String aclClave;

    @Column(name = "CFAC_ACL_MENSAJE")
    private String aclMensaje;
}
