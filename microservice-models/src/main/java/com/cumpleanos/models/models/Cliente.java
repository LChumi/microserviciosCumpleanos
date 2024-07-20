package com.cumpleanos.models.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "CLIENTE")
@Data
public class Cliente {

    @Id
    @Column(name = "CLI_CODIGO")
    private Long id;

    @Column(name = "CLI_EMPRESA")
    private Long empresa;

    @Column(name = "CLI_ID")
    private String cliId;

    @Column(name = "CLI_TIPO")
    private Long tipo;

    @Column(name = "CLI_TITULO")
    private String titulo;

    @Column(name = "CLI_NOMBRE")
    private String nombre;

    @Column(name = "CLI_RUC_CEDULA")
    private String rucCedula;

    @Column(name = "CLI_CIUDAD")
    private Long cudad;

    @Column(name = "CLI_ZONA")
    private Long zona;

    @Column(name = "CLI_DIRECCION")
    private String direccion;

    @Column(name = "CLI_TELEFONO1")
    private String telefono;

    @Column(name = "CLI_TELEFONO2")
    private String telefono2;

    @Column(name = "CLI_TELEFONO3")
    private String telefono3;

    @Column(name = "CLI_FAX")
    private String fax;

    @Column(name = "CLI_APART_POSTAL")
    private String apartPostal;

    @Column(name = "CLI_MAIL")
    private String mail;

    @Column(name = "CLI_CUENTA_DEB")
    private Long cuentaDeb;

    @Column(name = "CLI_CUENTA_CRE")
    private Long cuentaCre;

    @Column(name = "CLI_CATEGORIA")
    private Long categoria;

    @Column(name = "CLI_IMPUESTOS")
    private Long impuestos;

    @Column(name = "CLI_AGENTE")
    private Long agente;

    @Column(name = "CLI_CONTACTO")
    private Long contacto;

    @Column(name = "CLI_REF_COMERCIAL")
    private String refComercial;

    @Column(name = "CLI_REF_BANCARIA")
    private String refBancaria;

    @Column(name = "CLI_INACTIVO")
    private Boolean inactivo;

    @Column(name = "CLI_BLOQUEO")
    private Boolean bloqueio;

    @Column(name = "CLI_TARJETA")
    private Long tarjeta;

    @Column(name = "CLI_CUPO")
    private BigDecimal cupo;

    @Column(name = "CLI_ILIMITADO")
    private Boolean limitado;

    @Column(name = "CLI_POLITICAS")
    private Long politicas;

    @Column(name = "CLI_LISTAPRE")
    private Long listapre;

    @Column(name = "CLI_TIPOCED")
    private Long tipoced;

    @Column(name = "CLI_ORDEN")
    private Long orden;

    @Column(name = "CLI_CONTRIBUYENTE")
    private Long contribuyente;

    @Column(name = "CLI_RETIVA")
    private Long retiva;

    @Column(name = "CLI_RETFUENTE")
    private Long retfuente;

    @Column(name = "CLI_FECHAING")
    private LocalDate fechaIng;

    @Column(name = "CLI_PUENTE")
    private Long puente;

    @Column(name = "CLI_VISUALIZA")
    private Long visualiza;

    @Column(name = "CLI_ICENOIVA")
    private Long icenoiva;

    @Column(name = "CLI_TIPOPER")
    private Long tipoPer;

    @Column(name = "CLI_NOMBRECOM")
    private String nombreCom;

    @Column(name = "CLI_PARROQUIA")
    private Long parroquia;

    @Column(name = "CLI_CANAL")
    private Long canal;

    @Column(name = "CLI_TIPOCLI")
    private Long tipoCliente;

    @Column(name = "CLI_CUENTACLA")
    private Long cuentacla;

    //CLIRUTA -> ADELANTE

}
