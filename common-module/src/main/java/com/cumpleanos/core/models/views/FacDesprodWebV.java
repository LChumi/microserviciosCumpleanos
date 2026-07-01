package com.cumpleanos.core.models.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Immutable
@Table(name = "FAC_DESPROD_WEB_V", schema = "DATA_USR")
public class FacDesprodWebV {

    @Id
    @Column(name = "DPR_ROWID")
    private String rowid;

    @Column(name = "DPR_COMPROBANTE", length = 4000)
    private String comprobante;

    @Column(name = "DPR_EMPRESA")
    private Long empresa;

    @Column(name = "DPR_CCO_CODIGO")
    private BigInteger ccoCodigo;

    @Column(name = "DPR_FECHA")
    private LocalDate dprFecha;

    @Column(name = "DPR_TIPODOC")
    private Long tipodoc;

    @Column(name = "DPR_CEDULA_RUC", length = 15)
    private String cedulaRuc;

    @Column(name = "DPR_SECUENCIA")
    private Long secuencia;

    @Column(name = "DPR_PRO_CODIGO")
    private Long proCodigo;

    @Column(name = "DPR_PRO_ID", length = 20)
    private String proId;

    @Column(name = "DPR_PRO_ID1", length = 50)
    private String proId1;

    @Column(name = "DPR_PRO_NOMBRE", length = 100)
    private String proNombre;

    @Column(name = "DPR_PRO_PRECIO2")
    private Long proPrecio2;

    @Column(name = "DPR_HOJA")
    private Long hoja;

    @Column(name = "DPR_CDIGITADA", precision = 17, scale = 4)
    private BigDecimal cdigitada;

    @Column(name = "DPR_CANAPR", precision = 17, scale = 4)
    private BigDecimal canapr;

    @Column(name = "DPR_CANEMP", precision = 17, scale = 4)
    private BigDecimal canemp;

    @Column(name = "DPR_OBSERVACION", length = 500)
    private String observacion;

    @Column(name = "DPR_BOD_CODIGO")
    private Long bodCodigo;

    @Column(name = "DPR_BOD_ID", length = 10)
    private String bodId;

    @Column(name = "DPR_BOD_NOMBRE", length = 100)
    private String bodNombre;

    @Column(name = "DPR_DESPACHAR")
    private Long despachar;

    @Column(name = "DPR_CAJA_EMPAQUE", length = 100)
    private String cajaEmpaque;

    @Column(name = "DPR_DESPACHADOR", length = 100)
    private String despachador;

    @Column(name = "DPR_RESPONSABLE", length = 4000)
    private String responsable;

    @Column(name = "DPR_SECCION", length = 4000)
    private String seccion;

    @Column(name = "DPR_CXB")
    private Long cxb;

    @Column(name = "DPR_UBI_BLT", length = 4000)
    private String ubiBlt;

    @Column(name = "DPR_UBI_UNI", length = 4000)
    private String ubiUni;

    @Column(name = "DPR_STOCK")
    private Long stock;
}
