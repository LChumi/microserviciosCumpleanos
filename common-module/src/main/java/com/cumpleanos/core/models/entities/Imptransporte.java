package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.ImptransporteId;
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
@Table(name = "IMPTRANSPORTE")
@EqualsAndHashCode(of = "id")
public class Imptransporte {

    @EmbeddedId
    private ImptransporteId id;

    @Column(name = "IMT_ID", nullable = false, length = 30)
    private String imtId;

    @Column(name = "IMT_DESCRIPCION", nullable = false, length = 200)
    private String descripcion;

    @Column(name = "IMT_INACTIVO")
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
