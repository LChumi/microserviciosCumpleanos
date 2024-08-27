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
@Table(name = "MODULO")
@Data
public class Modulo {

    @Id
    @Column(name = "MOD_CODIGO")
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "MOD_ID")
    private String modId;

    @Column(name = "MOD_NOMBRE")
    private String nombre;

    @Column(name = "MOD_SEGURIDAD")
    private Long seguridad;

    @Column(name = "MOD_INACTIVO")
    private Boolean intivo;

    @Column(name = "CREA_USR")
    private LocalDate creaUsr;

    @Column(name = "MOD_USR")
    private String modUsr;

    @Column(name = "MOD_FECHA")
    private LocalDate fecha;
}
