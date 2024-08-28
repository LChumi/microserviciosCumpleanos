package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.CuentaId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity
@Table(name = "CUENTA")
public class Cuenta {

    @EmbeddedId
    private CuentaId id;

    @Size(max = 20)
    @NotNull
    @Column(name = "CUE_ID", nullable = false, length = 20)
    private String cueId;

    @Size(max = 100)
    @NotNull
    @Column(name = "CUE_NOMBRE", nullable = false, length = 100)
    private String cueNombre;

    @ColumnDefault("0")
    @Column(name = "CUE_INACTIVO")
    private Boolean cueInactivo;

    @Column(name = "CUE_GENERO")
    private Boolean cueGenero;

    @NotNull
    @Column(name = "CUE_MOVIMIENTO", nullable = false)
    private Boolean cueMovimiento = false;

    @NotNull
    @Column(name = "CUE_ORDEN", nullable = false)
    private Long cueOrden;

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

    @ColumnDefault("0")
    @Column(name = "CUE_VISUALIZA")
    private Boolean cueVisualiza;

    @ColumnDefault("0")
    @Column(name = "CUE_NEGRITA")
    private Boolean cueNegrita;
}
