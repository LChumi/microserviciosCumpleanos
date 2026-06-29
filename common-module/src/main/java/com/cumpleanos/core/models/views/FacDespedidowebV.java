package com.cumpleanos.core.models.views;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Immutable
@Table(name = "FAC_DESPEDIDOWEB_V", schema = "DATA_USR")
public class FacDespedidowebV {

    @Id
    @Column(name = "DPW_CCO_CODIGO")
    private BigInteger ccoCodigo;

    @Column(name = "DPW_USR_CODIGO")
    private Long usrCodigo;

    @Column(name = "DPW_USR_ID", length = 10)
    private String usrId;

    @Column(name = "DPW_USR_NOMBRE", length = 100)
    private String usrNombre;

    @Column(name = "DPW_COMPROBANTE", length = 4000)
    private String comprobante;

    @Column(name = "DPW_EMPRESA")
    private Long empresa;

    @Column(name = "DPW_TIPODOC")
    private Long tipodoc;

    @Column(name = "DPW_EST_PEDIDO")
    private Long estPedido;

    @Column(name = "DPW_FECHA_PEDIDO")
    private LocalDate fechaPedido;

    @Column(name = "DPW_CLIENTE", length = 100)
    private String cliente;

    @Column(name = "DPW_CEDULA_RUC", length = 15)
    private String cedulaRuc;

    @Column(name = "DPW_FECHA_ASIGNACION")
    private LocalDate fechaAsignacion;

    @Column(name = "DPW_CIUDAD", length = 100)
    private String ciudad;

    @Column(name = "DPW_URGENTE")
    private Long urgente;

    @Column(name = "DPW_PED_PEND")
    private Boolean pedPend;

    @Column(name = "DPW_HOJA")
    private Long hoja;

    @Column(name = "DPW_ESTADO")
    private Integer estado;

    @Column(name = "DPW_USR_BLOQUEA", length = 50)
    private String usrBloquea;

    @Column(name = "DPW_HOJAS_DESP")
    private Long hojasDesp;

    @Column(name = "DPW_TOT_HOJAS")
    private Long totHojas;

    @Column(name = "DPW_PEDIDO_INTERNO")
    private Long pedidoInterno;

    @Column(name = "DPW_ROWID", length = 58)
    private String rowid;

    @Column(name = "DPW_RESPONSABLE", length = 4000)
    private String responsable;

    @Lob
    @Column(name = "DPW_SECCION")
    private String seccion;

    @Column(name = "DPW_EMPRESA_GRUPO")
    private Long empresaGrupo;

    @Column(name = "DPW_BOD_SOLICITA", length = 100)
    private String bodSolicita;

}