package com.cumpleanos.core.models.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Immutable
@Table(name = "FAC_DESPROD_WEB_V", schema = "DATA_USR")
public class FacDesprodWebV {

    @Id
    @Column(name = "DPR_ROWID")
    private String dprRowid;

    @Column(name = "DPR_COMPROBANTE", length = 4000)
    private String dprComprobante;

    @Column(name = "DPR_EMPRESA")
    private Long dprEmpresa;

    @Column(name = "DPR_CCO_CODIGO")
    private Long dprCcoCodigo;

    @Column(name = "DPR_FECHA")
    private LocalDate dprFecha;

    @Column(name = "DPR_TIPODOC")
    private Long dprTipodoc;

    @Column(name = "DPR_CEDULA_RUC", length = 15)
    private String dprCedulaRuc;

    @Column(name = "DPR_SECUENCIA")
    private Long dprSecuencia;

    @Column(name = "DPR_PRO_CODIGO")
    private Long dprProCodigo;

    @Column(name = "DPR_PRO_ID", length = 20)
    private String dprProId;

    @Column(name = "DPR_PRO_ID1", length = 50)
    private String dprProId1;

    @Column(name = "DPR_PRO_NOMBRE", length = 100)
    private String dprProNombre;

    @Column(name = "DPR_PRO_PRECIO2")
    private Long dprProPrecio2;

    @Column(name = "DPR_HOJA")
    private Long dprHoja;

    @Column(name = "DPR_CDIGITADA", precision = 17, scale = 4)
    private BigDecimal dprCdigitada;

    @Column(name = "DPR_CANAPR", precision = 17, scale = 4)
    private BigDecimal dprCanapr;

    @Column(name = "DPR_CANEMP", precision = 17, scale = 4)
    private BigDecimal dprCanemp;

    @Column(name = "DPR_OBSERVACION", length = 500)
    private String dprObservacion;

    @Column(name = "DPR_BOD_CODIGO")
    private Long dprBodCodigo;

    @Column(name = "DPR_BOD_ID", length = 10)
    private String dprBodId;

    @Column(name = "DPR_BOD_NOMBRE", length = 100)
    private String dprBodNombre;

    @Column(name = "DPR_DESPACHAR")
    private Long dprDespachar;

    @Column(name = "DPR_CAJA_EMPAQUE", length = 100)
    private String dprCajaEmpaque;

    @Column(name = "DPR_DESPACHADOR", length = 100)
    private String dprDespachador;

    @Column(name = "DPR_RESPONSABLE", length = 4000)
    private String dprResponsable;

    @Column(name = "DPR_SECCION", length = 4000)
    private String dprSeccion;

    @Column(name = "DPR_CXB")
    private Long dprCxb;

    @Column(name = "DPR_UBI_BLT", length = 4000)
    private String dprUbiBlt;

    @Column(name = "DPR_UBI_UNI", length = 4000)
    private String dprUbiUni;

    @Column(name = "DPR_STOCK")
    private Long dprStock;
}
