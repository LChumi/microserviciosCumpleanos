package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.AutclienteId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "AUTCLIENTE")
@Data
public class Autcliente {

    @EmbeddedId
    private AutclienteId id;

    @NotNull
    @Column(name = "ACL_TCLIPRO", nullable = false)
    private Boolean tclipro = false;

    @Column(name = "ACL_VAL_FECHA")
    private LocalDate valFecha;

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

    @Column(name = "ACL_INACTIVO")
    private Boolean inactivo;

    @Size(max = 9)
    @NotNull
    @Column(name = "ACL_FAC3", nullable = false, length = 9)
    private String fac3;

    @Size(max = 3)
    @NotNull
    @Column(name = "ACL_FACT1", nullable = false, length = 3)
    private String fact1;

    @Size(max = 3)
    @NotNull
    @Column(name = "ACL_FACT2", nullable = false, length = 3)
    private String fact2;

    @Size(max = 9)
    @NotNull
    @Column(name = "ACL_FACT3", nullable = false, length = 9)
    private String fact3;
}
