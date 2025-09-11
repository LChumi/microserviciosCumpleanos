package com.cumpleanos.core.models.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Table(name = "DMOVPROD_CON", schema = "DATA_USR", indexes = {
        @Index(name = "DMOVPROD_CON_IDX03", columnList = "DPP_PRODUCTO, DPP_PRE_SEC, DPP_PREPEDIDO, DPP_EMPRESA", unique = true),
        @Index(name = "DMOVPROD_CON_IDX05", columnList = "DPP_PRODUCTO, DPP_PRO_SEC, DPP_PROFORMA, DPP_PRO_EMP, DPP_EMPRESA, DPP_PREPEDIDO, DPP_PRE_SEC", unique = true),
        @Index(name = "DMOVPROD_CON_IDX04", columnList = "DPP_PRODUCTO, DPP_PED_SEC, DPP_PEDIDO, DPP_PED_EMP"),
        @Index(name = "DMOVPROD_CON_IDX02", columnList = "DPP_PRODUCTO, DPP_PED_SEC_REL, DPP_PEDIDO_REL, DPP_PED_EMP_REL"),
        @Index(name = "DMOVPROD_CON_IDX01", columnList = "DPP_PRODUCTO, DPP_STR_SEC, DPP_SOLTRA, DPP_STR_EMP"),
        @Index(name = "DMOVPROD_CON_IDX17", columnList = "DPP_CREPOSICION, DPP_EMPRESA"),
        @Index(name = "DMOVPROD_CON_IDX18", columnList = "DPP_CPEDIDO, DPP_EMPRESA"),
        @Index(name = "DMOVPROD_CON_IDX06", columnList = "DPP_PREPEDIDO, DPP_EMPRESA"),
        @Index(name = "DMOVPROD_CON_IDX07", columnList = "DPP_PEDIDO, DPP_PED_EMP"),
        @Index(name = "DMOVPROD_CON_IDX08", columnList = "DPP_PEDIDO_REL, DPP_PED_EMP_REL"),
        @Index(name = "DMOVPROD_CON_IDX09", columnList = "DPP_SOLTRA, DPP_STR_EMP"),
        @Index(name = "DMOVPROD_CON_IDX10", columnList = "DPP_ORDEN, DPP_ORD_EMP"),
        @Index(name = "DMOVPROD_CON_IDX11", columnList = "DPP_INGRESO, DPP_ING_EMP"),
        @Index(name = "DMOVPROD_CON_IDX12", columnList = "DPP_EGRESO, DPP_EGR_EMP"),
        @Index(name = "DMOVPROD_CON_IDX13", columnList = "DPP_VALORACION, DPP_VAL_EMP"),
        @Index(name = "DMOVPROD_CON_IDX14", columnList = "DPP_FACTURA, DPP_FAC_EMP"),
        @Index(name = "DMOVPROD_CON_IDX15", columnList = "DPP_TRANSFERENCIA, DPP_TRA_EMP"),
        @Index(name = "DMOVPROD_CON_IDX16", columnList = "DPP_PROFORMA, DPP_PRO_EMP"),
        @Index(name = "DMOVPROD_CON_IDX19", columnList = "DPP_CARRITO, DPP_EMPRESA")
})
public class DmovprodCon {
    @Column(name = "DPP_EMPRESA", nullable = false)
    private Long empresa;

    @Id
    @Column(name = "DPP_PRODUCTO", nullable = false)
    private Long producto;

    @Column(name = "DPP_BODEGA")
    private Long bodega;

    @Column(name = "DPP_AGENTE")
    private Long agente;

    @Column(name = "DPP_USUARIO")
    private Long usuario;

    @Column(name = "DPP_CREPOSICION")
    private Long creposicion;

    @Column(name = "DPP_CPEDIDO")
    private Long cpedido;

    @Column(name = "DPP_TIPO")
    private Boolean tipo;

    @Column(name = "DPP_PREPEDIDO")
    private BigInteger prepedido;

    @Column(name = "DPP_PRE_FECHA")
    private LocalDate preFecha;

    @Column(name = "DPP_PRE_SEC")
    private Long preSecuencia;

    @ColumnDefault("0")
    @Column(name = "DPP_PRE_CANT")
    private Long preCant;

    @ColumnDefault("0")
    @Column(name = "DPP_PRE_STOCK_R")
    private Long preStockR;

    @ColumnDefault("0")
    @Column(name = "DPP_PRE_STOCK_D")
    private Long preStockD;

    @Column(name = "DPP_PED_EMP")
    private Long pedEmpresa;

    @Column(name = "DPP_PEDIDO")
    private BigInteger pedido;

    @Column(name = "DPP_PED_FECHA")
    private LocalDate pedFecha;

    @Column(name = "DPP_PED_SEC")
    private Long pedSecuencia;

    @ColumnDefault("0")
    @Column(name = "DPP_PED_CANT")
    private Long pedCant;

    @ColumnDefault("0")
    @Column(name = "DPP_PED_STOCK_R")
    private Long pedStockR;

    @ColumnDefault("0")
    @Column(name = "DPP_PED_STOCK_D")
    private Long pedStockD;

    @Column(name = "DPP_PED_EMP_REL")
    private BigInteger pedEmpRel;

    @Column(name = "DPP_PEDIDO_REL")
    private Long pedidoRel;

    @Column(name = "DPP_PED_FECHA_REL")
    private LocalDate pedFechaRel;

    @Column(name = "DPP_PED_SEC_REL")
    private Long pedSecRel;

    @Column(name = "DPP_PED_CANT_REL")
    private Long pedCantRel;

    @Column(name = "DPP_PED_STOCK_R_REL")
    private Long pedStockRRel;

    @Column(name = "DPP_PED_STOCK_D_REL")
    private Long pedStockDRel;

    @Column(name = "DPP_STR_EMP")
    private Long strEmp;

    @Column(name = "DPP_SOLTRA")
    private BigInteger soltra;

    @Column(name = "DPP_STR_FECHA")
    private LocalDate strFecha;

    @Column(name = "DPP_STR_SEC")
    private Long strSec;

    @ColumnDefault("0")
    @Column(name = "DPP_STR_CANT")
    private Long strCant;

    @ColumnDefault("0")
    @Column(name = "DPP_STR_STOCK_R")
    private Long strStockR;

    @ColumnDefault("0")
    @Column(name = "DPP_STR_STOCK_D")
    private Long strStockD;

    @Column(name = "DPP_ORD_EMP")
    private Long ordEmp;

    @Column(name = "DPP_ORDEN")
    private BigInteger orden;

    @Column(name = "DPP_ORD_FECHA")
    private LocalDate ordFecha;

    @Column(name = "DPP_ORD_SEC")
    private Long ordSec;

    @ColumnDefault("0")
    @Column(name = "DPP_ORD_CANT")
    private Long ordCant;

    @ColumnDefault("0")
    @Column(name = "DPP_ORD_STOCK_R")
    private Long ordStockR;

    @ColumnDefault("0")
    @Column(name = "DPP_ORD_STOCK_D")
    private Long ordStockD;

    @Column(name = "DPP_ING_EMP")
    private Long ingEmp;

    @Column(name = "DPP_INGRESO")
    private BigInteger ingreso;

    @Column(name = "DPP_ING_FECHA")
    private LocalDate ingFecha;

    @Column(name = "DPP_ING_SEC")
    private Long ingSec;

    @ColumnDefault("0")
    @Column(name = "DPP_ING_CANT")
    private Long ingCant;

    @ColumnDefault("0")
    @Column(name = "DPP_ING_STOCK_R")
    private Long ingStockR;

    @ColumnDefault("0")
    @Column(name = "DPP_ING_STOCK_D")
    private Long ingStockD;

    @Column(name = "DPP_EGR_EMP")
    private Long egrEmp;

    @Column(name = "DPP_EGRESO")
    private BigInteger egreso;

    @Column(name = "DPP_EGR_FECHA")
    private LocalDate egrFecha;

    @Column(name = "DPP_EGR_SEC")
    private Long egrSec;

    @ColumnDefault("0")
    @Column(name = "DPP_EGR_CANT")
    private Long egrCant;

    @ColumnDefault("0")
    @Column(name = "DPP_EGR_STOCK_R")
    private Long egrStockR;

    @ColumnDefault("0")
    @Column(name = "DPP_EGR_STOCK_D")
    private Long egrStockD;

    @Column(name = "DPP_VAL_EMP")
    private Long valEmp;

    @Column(name = "DPP_VALORACION")
    private BigInteger valoracion;

    @Column(name = "DPP_VAL_FECHA")
    private LocalDate valFecha;

    @Column(name = "DPP_VAL_SEC")
    private Long valSec;

    @ColumnDefault("0")
    @Column(name = "DPP_VAL_CANT")
    private Long valCant;

    @ColumnDefault("0")
    @Column(name = "DPP_VAL_STOCK_R")
    private Long valStockR;

    @ColumnDefault("0")
    @Column(name = "DPP_VAL_STOCK_D")
    private Long valStockD;

    @Column(name = "DPP_FAC_EMP")
    private Long facEmp;

    @Column(name = "DPP_FACTURA")
    private BigInteger factura;

    @Column(name = "DPP_FAC_FECHA")
    private LocalDate facFecha;

    @Column(name = "DPP_FAC_SEC")
    private Long facSec;

    @ColumnDefault("0")
    @Column(name = "DPP_FAC_CANT")
    private Long facCant;

    @ColumnDefault("0")
    @Column(name = "DPP_FAC_STOCK_R")
    private Long facStockR;

    @ColumnDefault("0")
    @Column(name = "DPP_FAC_STOCK_D")
    private Long facStockD;

    @ColumnDefault("0")
    @Column(name = "DPP_PED_CANT_ACT")
    private Long pedCantAct;

    @Column(name = "DPP_STR_CANT_ACT")
    private Long strCantAct;

    @Column(name = "DPP_TRA_EMP")
    private Long traEmp;

    @Column(name = "DPP_TRANSFERENCIA")
    private BigInteger transferencia;

    @Column(name = "DPP_TRA_FECHA")
    private LocalDate traFecha;

    @Column(name = "DPP_TRA_SEC")
    private Long traSec;

    @ColumnDefault("0")
    @Column(name = "DPP_TRA_CANT")
    private Long traCant;

    @ColumnDefault("0")
    @Column(name = "DPP_TRA_STOCK_R")
    private Long traStockR;

    @ColumnDefault("0")
    @Column(name = "DPP_TRA_STOCK_D")
    private Long traStockD;

    @Column(name = "DPP_USR_LIQUIDA")
    private Long usrLiquida;

    @Column(name = "DPP_FAC_LIQUIDA")
    private BigInteger facLiquida;

    @Column(name = "DPP_FAC_EMP_LIQUIDA")
    private Long facEmpLiquida;

    @Column(name = "DPP_FECHA_LIQ_DESDE")
    private LocalDate fechaLiqDesde;

    @Column(name = "DPP_FECHA_LIQ_HASTA")
    private LocalDate fechaLiqHasta;

    @Column(name = "DPP_PRO_EMP")
    private Long proEmp;

    @Column(name = "DPP_PROFORMA")
    private BigInteger proforma;

    @Column(name = "DPP_PRO_FECHA")
    private LocalDate proFecha;

    @Column(name = "DPP_PRO_SEC")
    private Long proSec;

    @ColumnDefault("0")
    @Column(name = "DPP_PRO_CANT")
    private Long proCant;

    @ColumnDefault("0")
    @Column(name = "DPP_PRO_STOCK_R")
    private Long proStockR;

    @ColumnDefault("0")
    @Column(name = "DPP_PRO_STOCK_D")
    private Long proStockD;
}
