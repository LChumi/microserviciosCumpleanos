package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.UmedidaId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "UMEDIDA")
@Data
public class Umedida {

    @EmbeddedId
    private UmedidaId id;

    @Size(max = 100)
    @NotNull
    @Column(name = "UMD_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Size(max = 10)
    @NotNull
    @Column(name = "UMD_ID", nullable = false, length = 10)
    private String umdId;

    @Column(name = "UMD_INACTIVO")
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

    @Size(max = 10)
    @Column(name = "UMD_ICE1", length = 10)
    private String ice1;

    @Size(max = 10)
    @Column(name = "UMD_SSI", length = 10)
    private String ssi;

    @Size(max = 20)
    @Column(name = "UMD_NSSI", length = 20)
    private String nssi;
}
