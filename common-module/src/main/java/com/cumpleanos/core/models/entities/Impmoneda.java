package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.ImpmonedaId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "IMPMONEDA", schema = "DATA_USR")
public class Impmoneda {

    @EmbeddedId
    private ImpmonedaId id;

    @Column(name = "IMM_ID", nullable = false, length = 20)
    private String immId;

    @Column(name = "IMM_DESCRIPCION", nullable = false, length = 200)
    private String descripcion;

    @Column(name = "IMM_INACTIVO")
    private Boolean inactivo;

    @Column(name = "CREA_USR", length = 10)
    private String creaUsr;

    @Column(name = "CREA_FECHA")
    private LocalDate creaFecha;

    @Column(name = "MOD_USR", length = 10)
    private String modUsr;

    @Column(name = "MOD_FECHA")
    private LocalDate modFecha;
}