package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.ImppartidaId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "IMPPARTIDA", schema = "DATA_USR")
@EqualsAndHashCode(of = "id")
public class Imppartida {

    @EmbeddedId
    private ImppartidaId id;

    @Column(name = "IPR_NOMBRE", nullable = false, length = 400)
    private String nombre;

    @Column(name = "IPR_REFERENCIA", length = 100)
    private String referencia;

    @Column(name = "IPR_PORCENTAJE", precision = 5, scale = 2)
    private BigDecimal porcentaje;

    @Column(name = "CREA_USR", length = 10)
    private String creaUsr;

    @Column(name = "CREA_FECHA")
    private LocalDate creaFecha;

    @Column(name = "MOD_USR", length = 10)
    private String modUsr;

    @Column(name = "MOD_FECHA")
    private LocalDate modFecha;

    @Column(name = "IPR_ID", nullable = false, length = 30)
    private String iprId;

    @Column(name = "IPR_PORGAST", precision = 5, scale = 2)
    private BigDecimal porgast;

    @Column(name = "IPR_ARANCEL", length = 30)
    private String arancel;
}
