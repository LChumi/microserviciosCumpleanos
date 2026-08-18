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
    private Long codigo;

    @Column(name = "BOD_USUARIO")
    private Long usuario;

    @Column(name = "BOD_EMPRESA")
    private Long empresa;

    @Column(name = "BOD_ID")
    private String id;

    @Column(name = "BOD_NOMBRE")
    private String nombre;

    @Column(name = "BOD_CONSIGNA")
    private Integer consigna;

    @Column(name = "BOD_UBICACION")
    private String ubicacion;

    @Column(name = "BOD_CIUDAD")
    private Integer ciudad;

    @Column(name = "BOD_ZONA")
    private Long zona;

    @Column(name = "BOD_INACTIVO")
    private Integer inactivo;

    @Column(name = "BOD_IMPRESORA")
    private String impresora;

    @Column(name = "BOD_LIQUIDACION")
    private Integer liquidacion;

    @Column(name = "BOD_PROBLEMAS")
    private Integer problemas;

    @Column(name = "BOD_EMPLEADO")
    private Long empleado;

    @Column(name = "BOD_CUSTODIO")
    private String custodio;

    @Column(name = "BOD_DIRECTO")
    private Integer directo;

    @Column(name = "BOD_ALMACEN")
    private Long almacen;

    @Column(name = "BOD_FECHA_INICIO")
    private LocalDateTime fechaInicio;

    @Column(name = "BOD_FECHA_FINAL")
    private LocalDateTime fechaFinal;

    @Column(name = "BOD_PROMOCION")
    private Long promocion;

    @Column(name = "BOD_CENTRO")
    private Integer centro;

    @Column(name = "BOD_VER_CAL")
    private Integer bodVerCal;

    @Column(name = "BOD_TIPO")
    private Integer tipo;

    @Column(name = "BOD_PROVEEDOR")
    private Integer proveedor;

    @Column(name = "BOD_COMPRA")
    private Integer compra;

    @Column(name = "BOD_MAYORISTA")
    private Integer mayorista;

    @Column(name = "BOD_BODEGA_WEB")
    private Long bodegaWeb;

    @Column(name = "BOD_BODEGA_WEB_DEF")
    private Long bodegaWebDef;
    
}