package com.cumpleanos.models.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "SISTEMA")
@Data
public class Sistema {

    @Id
    @Column(name = "SIS_CODIGO")
    private Long id;

    @Size(max = 10)
    @NotNull
    @Column(name = "SIS_ID", nullable = false, length = 10)
    private String sisId;

    @Size(max = 100)
    @NotNull
    @Column(name = "SIS_NOMBRE", nullable = false, length = 100)
    private String sisNombre;

    @Column(name = "SIS_IMPUESTO_COMPRA")
    private Long sisImpuestoCompra;

    @NotNull
    @Column(name = "SIS_DECIMAL", nullable = false)
    private Boolean sisDecimal = false;

    @Column(name = "SIS_EXCLUSIVO")
    private Boolean sisExclusivo;

    @Size(max = 50)
    @Column(name = "SIS_CALLE", length = 50)
    private String sisCalle;

    @Size(max = 50)
    @Column(name = "SIS_TRANSVER", length = 50)
    private String sisTransver;

    @Size(max = 10)
    @Column(name = "SIS_NUMERO", length = 10)
    private String sisNumero;

    @Size(max = 20)
    @Column(name = "SIS_TELEFONO1", length = 20)
    private String sisTelefono1;

    @Size(max = 20)
    @Column(name = "SIS_TELEFONO2", length = 20)
    private String sisTelefono2;

    @Size(max = 20)
    @Column(name = "SIS_TELEFONO3", length = 20)
    private String sisTelefono3;

    @Column(name = "SIS_CIUDAD")
    private Long sisCiudad;

    @Size(max = 15)
    @Column(name = "SIS_CASILLA", length = 15)
    private String sisCasilla;

    @Size(max = 50)
    @Column(name = "SIS_EMAIL", length = 50)
    private String sisEmail;

    @Size(max = 15)
    @Column(name = "SIS_RUC", length = 15)
    private String sisRuc;

    @Size(max = 100)
    @Column(name = "SIS_REPLEGAL", length = 100)
    private String sisReplegal;

    @Size(max = 100)
    @Column(name = "SIS_DIRREPRE", length = 100)
    private String sisDirrepre;

    @Size(max = 20)
    @Column(name = "SIS_TELREPRE", length = 20)
    private String sisTelrepre;

    @Column(name = "SIS_INACTIVO")
    private Boolean sisInactivo;

    @Column(name = "SIS_IMPUESTO_VENTA")
    private Long sisImpuestoVenta;

    @Size(max = 15)
    @Column(name = "SIS_AUTORIZA", length = 15)
    private String sisAutoriza;

    @Column(name = "SIS_FECHA_AUTO")
    private LocalDate sisFechaAuto;

    @Size(max = 10)
    @Column(name = "CREA_USR", length = 10)
    private String creaUsr;

    @Column(name = "CREA_FECHA")
    private LocalDate creaFecha;

    @Size(max = 10)
    @Column(name = "MOD_USR", length = 10)
    private String modUsr;

    @Column(name = "MOD_FECHA")
    private LocalDate modFecha;

    @Size(max = 100)
    @Column(name = "SIS_CONTADOR", length = 100)
    private String sisContador;

    @Size(max = 30)
    @Column(name = "SIS_NOMBRECORTO", length = 30)
    private String sisNombrecorto;

    @Size(max = 30)
    @Column(name = "SIS_FORMATOIMP", length = 30)
    private String sisFormatoimp;

    @Column(name = "SIS_VALORACCION", precision = 17, scale = 4)
    private BigDecimal sisValoraccion;

    @Column(name = "SIS_SUBIRACCIONES")
    private Boolean sisSubiracciones;

    @Size(max = 15)
    @Column(name = "SIS_AUTORIZA2", length = 15)
    private String sisAutoriza2;

    @Column(name = "SIS_FECHA_AUTO2")
    private LocalDate sisFechaAuto2;

    @Column(name = "SIS_FECHA_INICIO")
    private LocalDate sisFechaInicio;

    @Column(name = "SIS_FECHA_FIN")
    private LocalDate sisFechaFin;

    @Size(max = 13)
    @Column(name = "SIS_RUC_REPLEGAL", length = 13)
    private String sisRucReplegal;

    @Size(max = 13)
    @Column(name = "SIS_RUC_CONTADOR", length = 13)
    private String sisRucContador;

    @Size(max = 1)
    @Column(name = "SIS_TIPOID_REPLEGAL", length = 1)
    private String sisTipoidReplegal;

    @Column(name = "SIS_IMP_VENTA_SIVA")
    private Long sisImpVentaSiva;

    @Column(name = "SIS_IMP_COMPRA_SIVA")
    private Long sisImpCompraSiva;

    @Column(name = "SIS_REPLICA_VENTA")
    private Boolean sisReplicaVenta;

    @Column(name = "SIS_IMPRESION")
    private Boolean sisImpresion;

    @Column(name = "SIS_RESOLUCION")
    private Short sisResolucion;

    @Size(max = 2)
    @Column(name = "SIS_CONTABILIDAD", length = 2)
    private String sisContabilidad;

    @Column(name = "SIS_AMBIENTE")
    private Long sisAmbiente;

}
