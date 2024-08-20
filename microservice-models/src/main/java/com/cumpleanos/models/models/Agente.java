package com.cumpleanos.models.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "AGENTE")
@Data
public class Agente {

    @Id
    @Column(name = "AGE_CODIGO")
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "AGE_EMPRESA")
    private Long empresa;

    @Column(name = "AGE_NOMBRE")
    private String nombre;

    @Column(name = "AGE_ID")
    private String ageId;

    @Column(name = "AGE_PORC_COMISION")
    private String porcComision;

    @Column(name = "AGE_EMPLEADO")
    private Long empleado;

    @Column(name = "AGE_INACTIVO")
    private Long inactivo;

    @Column(name = "AGE_BODEGA")
    private Long Bodega;//cambiar por realcion Bodega

    @Column(name = "AGE_CLIENTE")
    private Long cliente;

    @Column(name = "AGE_CUEGASTO")
    private Long cuegasto;

    @Column(name = "AGE_ALMACEN")
    private Long almacen;

    @Column(name = "AGE_PVENTA")
    private Long pVenta;

    @Column(name = "AGE_ALMACEN1")
    private Long almacen1;

    @Column(name = "AGE_PVENTA1")
    private Long pVenta1;

    @Column(name = "AGE_DEPARTAMENTO")
    private Long departamento;

    @Column(name = "AGE_CEDULA")
    private String cedula;

    @Column(name = "AGE_REPRE")
    private Long repre;

    @Column(name = "AGE_REPCOB")
    private Long repcob;

    @Column(name = "AGE_REPSAL")
    private Long repsal;

    @Column(name = "AGE_USUARIO")
    private Long usuario;

    @Column(name = "AGE_DIAS_COMISION")
    private BigDecimal diasComision;
}
