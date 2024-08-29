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
@Table(name = "MODULO")
@Data
public class Modulo {

    @Id
    @Column(name = "MOD_CODIGO", nullable = false)
    private Long id;

    @Size(max = 10)
    @NotNull
    @Column(name = "MOD_ID", nullable = false, length = 10)
    private String modId;

    @Size(max = 100)
    @NotNull
    @Column(name = "MOD_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "MOD_INACTIVO")
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
}
