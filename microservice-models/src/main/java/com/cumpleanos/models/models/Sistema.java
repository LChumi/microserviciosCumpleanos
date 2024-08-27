package com.cumpleanos.models.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "SISTEMA")
@Data
public class Sistema {

    @Id
    @Column(name = "SIS_CODIGO")
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "SIS_ID")
    private String sisId;

    @Column(name = "SIS_NOMBRE")
    private String nombre;

    @Column(name = "SIS_IMPUESTO_COMPRA")
    private Long impuestoCompra;

    @Column(name = "SIS_DECIMAL")
    private Long decimal;

    @Column(name = "SIS_EXCLUSIVO")
    private Boolean exclusivo;

    @Column(name = "SIS_CALLE")
    private String calle;

    @Column(name = "SIS_TRANSVER")
    private String transver;

    @Column(name = "SIS_NUMERO")
    private String numero;

    @Column(name = "SIS_TELEFONO1")
    private String telefono1;

    @Column(name = "SIS_TELEFONO2")
    private String telefono2;

    @Column(name = "SIS_TELEFONO3")
    private String telefono3;

    @Column(name = "SIS_CIUDAD")
    private Long ciudad;

    @Column(name = "SIS_CASILLA")
    private String casilla;

    @Column(name = "SIS_EMAIL")
    private String email;

    @Column(name = "SIS_RUC")
    private String ruc;

    @Column(name = "SIS_REPLEGAL")
    private String replegal;

    @Column(name = "SIS_DIRREPRE")
    private String dirrepre;

    @Column(name = "SIS_TELREPRE")
    private String telrepre;

    @Column(name = "SIS_INACTIVO")
    private Boolean inivo;

    @Column(name = "SIS_IMPUESTO_VENTA")
    private Long impuestoVenta;

    @Column(name = "SIS_AUTORIZA")
    private String autoriza;

    @Column(name = "SIS_FECHA_AUTO")
    private LocalDate fechaAuto;

    @Column(name = "CREA_USR")
    private String creaUsr;

    @Column(name = "CREA_FECHA")
    private LocalDate creaFecha;

    @Column(name = "MOD_USR")
    private String modUsr;

    @Column(name = "MOD_FECHA")
    private LocalDate modFecha;

    @Column(name = "SIS_CONTADOR")
    private String contador;

    @Column(name = "SIS_NOMBRECORTO")
    private String nombrecorto;

    @Column(name = "SIS_FORMATOIMP")
    private String formato;

    @Column(name = "SIS_VALORACCION")
    private BigDecimal valoracion;

    @Column(name = "SIS_SUBIRACCIONES")
    private Long subiracciones;

    @Column(name = "SIS_AUTORIZA2")
    private String autoriza2;

    @Column(name = "SIS_FECHA_AUTO2")
    private LocalDate fechaAuto2;

    @Column(name = "SIS_FECHA_INICIO")
    private LocalDate fechaInicio;

    @Column(name = "SIS_FECHA_FIN")
    private LocalDate fechaFin;

    @Column(name = "SIS_RUC_REPLEGAL")
    private String rucRepuestal;

    @Column(name = "SIS_RUC_CONTADOR")
    private String rucContador;

    @Column(name = "SIS_TIPOID_REPLEGAL")
    private String tipoIdReplegal;

    @Column(name = "SIS_IMP_VENTA_SIVA")
    private Long impVentaSiva;

    @Column(name = "SIS_IMP_COMPRA_SIVA")
    private Long impCompraSiva;

    @Column(name = "SIS_REPLICA_VENTA")
    private Long replicaVenta;

    @Column(name = "SIS_IMPRESION")
    private Long impresion;

    @Column(name = "SIS_RESOLUCION")
    private Long resolucion;

    @Column(name = "SIS_CONTABILIDAD")
    private String contabilidad;

    @Column(name = "SIS_AMBIENTE")
    private Long ambiente;

    @Column(name = "SIS_EMPRESA_GRUPO")
    private Long empresaGrupo;

    @Column(name = "SIS_RESOLUCION_RET")
    private Long resolucionRet;
}
