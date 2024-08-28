package com.cumpleanos.models.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "SEGURIDAD")
public class Seguridad {

    @Id
    @Column(name = "SEG_CODIGO", nullable = false)
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "SEG_NOMBRE", nullable = false, length = 100)
    private String segNombre;

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
