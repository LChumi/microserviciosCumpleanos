package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.ImppuertoId;
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
@Table(name = "IMPPUERTO")
@EqualsAndHashCode(of = "id")
public class Imppuerto {

    @EmbeddedId
    private ImppuertoId id;

    @Column(name = "IPP_ID", nullable = false, length = 30)
    private String ippId;

    @Column(name = "IPP_DESCRIPCION", nullable = false, length = 200)
    private String descripcion;

    @Column(name = "CREA_USR", length = 10)
    private String creaUsr;

    @Column(name = "CREA_FECHA")
    private LocalDate creaFecha;

    @Column(name = "MOD_USR", length = 10)
    private String modUsr;

    @Column(name = "MOD_FECHA")
    private LocalDate modFecha;

    @Column(name = "IPP_PAIS")
    private Long pais;
}
