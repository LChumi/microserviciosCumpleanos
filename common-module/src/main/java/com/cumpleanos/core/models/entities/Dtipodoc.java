package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.DtipodocId;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "DTIPODOC")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {})
public class Dtipodoc {

    @EmbeddedId
    private DtipodocId id;

    @Column(name = "CREA_USR", length = 10)
    private String creaUsr;

    @Column(name = "CREA_FECHA")
    private LocalDate creaFecha;

    @Column(name = "MOD_USR", length = 10)
    private String modUsr;

    @Column(name = "MOD_FECHA")
    private LocalDate modFecha;
}
