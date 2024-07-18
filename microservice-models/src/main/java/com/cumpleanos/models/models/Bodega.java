package com.cumpleanos.models.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "BODEGA")
@Data
public class Bodega {

    @Id
    @Column(name = "BOD_CODIGO")
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "BOD_EMPRESA")
    private Long empresa;

    @Column(name = "BOD_ID")
    private String bodId;

    @Column(name = "BOD_NOMBRE")
    private String nombre;

    @Column(name = "BOD_CONSIGNA")
    private Long consignacion;

    @Column(name = "BOD_UBICACION")
    private String ubicacion;

    @Column(name = "BOD_CIUDAD")
    private Long ciudad;// CAMBIAR POR RELACION UBICACION

    @Column(name = "BOD_ZONA")
    private Long zona;

    @Column(name = "BOD_INACTIVO")
    private Long inactivo;

    @Column(name = "BOD_IMPRESORA")
    private String impresora;

    @Column(name = "BOD_LIQUIDACION")
    private Long liquidacion;

    @Column(name = "BOD_PROBLEMAS")
    private Long problemas;

    @Column(name = "BOD_EMPLEADO")
    private Long empleado;

    @Column(name = "BOD_CUSTODIO")
    private String customodio;

    @Column(name = "BOD_DIRECTO")
    private Long directo;

    @Column(name = "BOD_ALMACEN")
    private Long almacen;

    @Column(name = "BOD_PROMOCION")
    private Long promocion;

    @Column(name = "BOD_CENTRO")
    private Long centro;

    @Column(name = "BOD_VER_CAL")
    private Long verCal;

    @Column(name = "BOD_FECHA_INICIO")
    private LocalDate fechaInicio;

    @Column(name = "BOD_FECHA_FINAL")
    private LocalDate fechaFinal;
}
