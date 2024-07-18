package com.cumpleanos.models.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Table(name = "ALMACEN")
@Data
public class Almacen {

    @Id
    @Column(name = "ALM_CODIGO")
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "ALM_EMPRESA")
    private Long empresa;

    @Column(name = "ALM_NOMBRE")
    private String nombre;

    @Column(name = "ALM_ID")
    private String almId;

    @Column(name = "ALM_GERENTE")
    private String gerente;

    @Column(name = "ALM_CIUDAD")
    private Long ciudad; //cambiar por relacion con Ubicacion

    @Column(name = "ALM_ZONA")
    private Long zona; //cambiar por relacion a zona

    @Column(name = "ALM_DIRECCION")
    private String direccion;

    @Column(name = "ALM_TELEFONO1")
    private String telefono;

    @Column(name = "ALM_TELEFONO2")
    private String telefono2;

    @Column(name = "ALM_TELEFONO3")
    private String telefono3;

    @Column(name = "ALM_RUC")
    private String ruc;

    @Column(name = "ALM_FAX")
    private String fax;

    @Column(name = "ALM_LISTAPRE")
    private Long listapre; //cambiar por relacion ListaPre

    @Column(name = "ALM_CLI_VARIOS")
    private Long cliVarios; //cambiar por relacion cliente

    @Column(name = "ALM_CENTRO")
    private Long centro;

    @Column(name = "ALM_INACTIVO")
    private Long inactivo;

    @Column(name = "ALM_CTACAJA")
    private Long ctacaja;

    @Column(name = "CREA_USR")
    private String creaUsr;

    @Column(name = "ALM_BLOQUEO")
    private Long bloqueo;

    @Column(name = "ALM_SUBFIJO")
    private String subfijo;

    @Column(name = "ALM_BODEGA")
    private Long bodega; //cambiar por relacion Bodega

    @Column(name = "ALM_AGENTE")
    private Long agente; // cambiar por relacion agente

    @Column(name = "ALM_ID_REPORTE")
    private String idReporte;

    @Column(name = "ALM_MONTO")
    private Long monto;

    @Column(name = "ALM_NUMERO")
    private Long numero;

    @Column(name = "ALM_CTACAJA1")
    private Long ctaCaja1;

    @Column(name = "ALM_MATRIZ")
    private Long matriz;
}
