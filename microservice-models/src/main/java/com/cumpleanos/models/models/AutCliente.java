package com.cumpleanos.models.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Setter;

import javax.lang.model.element.Name;
import java.time.LocalDate;

@Entity
@Table(name = "AUTCLIENTE")
public class AutCliente {

    @Id
    @Setter(AccessLevel.NONE)
    @Column(name = "ACL_NRO_AUTORIZA")
    private String id;

    @Column(name = "ACL_EMPRESA")
    private Long empresa;

    @Column(name = "ACL_CLIENTE")
    private Long cliente;

    @Column(name = "ACL_TCLIPRO")
    private Long tclipro;

    @Column(name = "ACL_VAL_FECHA")
    private LocalDate valfecha;

    @Column(name = "CREA_USR")
    private String creaUsr;

    @Column(name = "CREA_FECHA")
    private LocalDate creaFecha;

    @Column(name = "MOD_USR")
    private String modUsr;

    @Column(name = "MOD_FECHA")
    private LocalDate modFecha;

    @Column(name = "ACL_INACTIVO")
    private Boolean inactivo;

    @Column(name = "ACL_FAC1")
    private String fac1;

    @Column(name = "ACL_FAC2")
    private String fac2;

    @Column(name = "ACL_FAC3")
    private String fac3;

    @Column(name = "ACL_FACT1")
    private String fact1;

    @Column(name = "ACL_FACT2")
    private String fact2;

    @Column(name = "ACL_FACT3")
    private String fact3;

    @Column(name = "ACL_RETDATO")
    private Long retDato;

    @Column(name = "ACL_TABLACOA")
    private Long tablacaoa;
}
