package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.CadAgenteId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "CADAGENTE")
public class CadAgente implements Serializable {

    @EmbeddedId
    private CadAgenteId id;

    @Size(max = 100)
    @Column(name = "CAD_AGENCIA", length = 100)
    private String agencia;

    @NotNull
    @Column(name = "CAD_ORDEN", nullable = false)
    private Long orden;

    @Column(name = "CAD_INACTIVO")
    private Boolean inactivo;

    @NotNull
    @Column(name = "CAD_FECHA_INICIA", nullable = false)
    private LocalDate fechaInicia;

    @Column(name = "CAD_FECHA_FINAL")
    private LocalDate fechaFinal;

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

    @Size(max = 10)
    @Column(name = "CAD_ID", length = 10)
    private String cadId;
}
