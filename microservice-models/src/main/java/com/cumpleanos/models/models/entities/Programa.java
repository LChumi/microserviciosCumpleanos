package com.cumpleanos.models.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "PROGRAMA")
@Data
public class Programa {

    @Id
    @Column(name = "PRG_CODIGO", nullable = false)
    private Long id;

    @Size(max = 20)
    @NotNull
    @Column(name = "PRG_ID", nullable = false, length = 20)
    private String prgId;

    @Size(max = 100)
    @NotNull
    @Column(name = "PRG_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @NotNull
    @Column(name = "PRG_TIPO", nullable = false)
    private Boolean tipo = false;

    @Column(name = "PRG_INACTIVO")
    private Boolean inactivo;

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

    @Size(max = 200)
    @Column(name = "PRG_ID_APEX", length = 200)
    private String idApex;
}
