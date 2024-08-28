package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.FactorId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "FACTOR")
public class Factor {

    @EmbeddedId
    private FactorId id;

    @NotNull
    @Column(name = "FAC_FACTOR", nullable = false, precision = 17, scale = 4)
    private BigDecimal factor;

    @Column(name = "FAC_INACTIVO")
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

    @Column(name = "FAC_DEFAULT")
    private Boolean facDefault;

    @Column(name = "FAC_DEFAULT_PRO")
    private Boolean defaultPro;

    @Column(name = "FAC_ALCOHOL", precision = 17, scale = 4)
    private BigDecimal alcohol;

    @Column(name = "FAC_PESO", precision = 17, scale = 4)
    private BigDecimal peso;

    @Column(name = "FAC_DEFAUL_CAJ")
    private Boolean defaulCaj;
}
