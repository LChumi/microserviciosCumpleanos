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
@Table(name = "COPCION")
@Data
public class Copcion {

    @Id
    @Column(name = "COP_CODIGO", nullable = false)
    private Long id;

    @Size(max = 10)
    @NotNull
    @Column(name = "COP_ID", nullable = false, length = 10)
    private String copId;

    @Size(max = 50)
    @NotNull
    @Column(name = "COP_NOMBRE", nullable = false, length = 50)
    private String copNombre;

    @Column(name = "COP_INACTIVO")
    private Boolean copInactivo;

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
