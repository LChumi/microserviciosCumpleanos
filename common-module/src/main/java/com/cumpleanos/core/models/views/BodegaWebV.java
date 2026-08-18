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
@Table(name = "BODEGA_WEB_V", schema = "PRG_USR")
public class BodegaWebV {

    @Id
    @Column(name = "BOD_CODIGO")
    private Long bodCodigo;

    @Column(name = "BOD_USUARIO")
    private Long bodUsuario;

    @Column(name = "BOD_EMPRESA")
    private Long bodEmpresa;

    @Column(name = "BOD_ID")
    private String bodId;

    @Column(name = "BOD_NOMBRE")
    private String bodNombre;

    @Column(name = "BOD_CONSIGNA")
    private Integer bodConsigna;

    @Column(name = "BOD_UBICACION")
    private String bodUbicacion;

    @Column(name = "BOD_CIUDAD")
    private Integer bodCiudad;

    @Column(name = "BOD_ZONA")
    private Long bodZona;

    @Column(name = "BOD_INACTIVO")
    private Integer bodInactivo;

    @Column(name = "BOD_IMPRESORA")
    private String bodImpresora;

    @Column(name = "BOD_LIQUIDACION")
    private Integer bodLiquidacion;

    @Column(name = "BOD_PROBLEMAS")
    private Integer bodProblemas;

    @Column(name = "BOD_EMPLEADO")
    private Long bodEmpleado;

    @Column(name = "BOD_CUSTODIO")
    private String bodCustodio;

    @Column(name = "BOD_DIRECTO")
    private Integer bodDirecto;

    @Column(name = "BOD_ALMACEN")
    private Long bodAlmacen;

    @Column(name = "BOD_FECHA_INICIO")
    private LocalDateTime bodFechaInicio;

    @Column(name = "BOD_FECHA_FINAL")
    private LocalDateTime bodFechaFinal;

    @Column(name = "BOD_PROMOCION")
    private Long bodPromocion;

    @Column(name = "BOD_CENTRO")
    private Integer bodCentro;

    @Column(name = "BOD_VER_CAL")
    private Integer bodVerCal;

    @Column(name = "BOD_TIPO")
    private Integer bodTipo;

    @Column(name = "BOD_PROVEEDOR")
    private Integer bodProveedor;

    @Column(name = "BOD_COMPRA")
    private Integer bodCompra;

    @Column(name = "BOD_MAYORISTA")
    private Integer bodMayorista;

    @Column(name = "BOD_BODEGA_WEB")
    private Long bodBodegaWeb;

    @Column(name = "BOD_BODEGA_WEB_DEF")
    private Long bodBodegaWebDef;
    
}