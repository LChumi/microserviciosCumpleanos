package com.cumpleanos.core.models.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Immutable
@Table(name = "INV_PRODINFGEN_WEB_V", schema = "DATA_USR")
public class InvProdinfgenWebV {

    @Id
    @Column(name = "SECUENCIA")
    private String secuencia;

    @Column(name = "BULTO")
    private String bulto;

    @Column(name = "UNIDAD")
    private String unidad;

    @Column(name = "STOCK_DISP")
    private Integer stockDisp;

    @Column(name = "STOCK_REAL")
    private Integer stockReal;

    @Column(name = "CANT_PEND_ING")
    private Integer cantPendIng;

    @Column(name = "BOD_CODIGO")
    private Long bodCodigo;

    @Column(name = "BOD_ID")
    private String bodId;

    @Column(name = "BOD_NOMBRE")
    private String bodNombre;

    @Column(name = "BOD_ALMACEN")
    private Long bodAlmacen;

    @Column(name = "BOD_INACTIVO")
    private Integer bodInactivo;

    @Column(name = "BOD_TIPO")
    private Integer bodTipo;

    @Column(name = "BOD_MAYORISTA")
    private Integer bodMayorista;

    @Column(name = "BOD_PROBLEMAS")
    private Integer bodProblemas;

    @Column(name = "CXB")
    private Integer cxb;

    @Column(name = "CXUEMP")
    private String cxuemp;

    @Column(name = "DESC_PVP")
    private Integer descPvp;

    @Column(name = "SEC")
    private Integer sec;

    @Column(name = "LIN_ID")
    private String linId;

    @Column(name = "PRO_ID")
    private String proId;

    @Column(name = "PRO_ID1")
    private String proId1;

    @Column(name = "PRO_NOMBRE")
    private String proNombre;

    @Column(name = "PRO_IMPUESTO")
    private Integer proImpuesto;

    @Column(name = "PRO_NOMBRE_ADUANA")
    private String proNombreAduana;

    @Column(name = "GPR_NOMBRE")
    private String gprNombre;

    @Column(name = "TPR_CODIGO")
    private Long tprCodigo;

    @Column(name = "TPR_ID")
    private String tprId;

    @Column(name = "TPR_NOMBRE")
    private String tprNombre;

    @Column(name = "PRO_EMPRESA")
    private Long proEmpresa;

    @Column(name = "PRO_CODIGO")
    private Long proCodigo;

    @Column(name = "PVP")
    private Double pvp;

    @Column(name = "PVD")
    private Double pvd;

    @Column(name = "PVS")
    private Double pvs;

    @Column(name = "PRO_ULT_FECHA_COMPRA")
    private LocalDateTime proUltFechaCompra;

    @Column(name = "UMD_CODIGO")
    private Long umdCodigo;

    @Column(name = "UMD_ID")
    private String umdId;

    @Column(name = "PRO_FOTO_S")
    private Integer proFotoS;

    @Column(name = "SIS_EMPRESA_GRUPO")
    private Long sisEmpresaGrupo;

    @Column(name = "PRO_CARGA_WEB")
    private Integer proCargaWeb;
}