package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.ImpformapagoId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "IMPFORMPAGO")
@EqualsAndHashCode(of = "id")
public class Impformapago {

    @EmbeddedId
    private ImpformapagoId id;

    @Column(name = "IFM_DESCRIPCION", nullable = false, length = 100)
    private String descripcion;

    @Column(name = "IFM_ID", nullable = false, length = 10)
    private String ifmId;

    @Column(name = "CREA_USR", length = 10)
    private String creaUsr;

    @Column(name = "CREA_FECHA")
    private LocalDate creaFecha;

    @Column(name = "MOD_USR", length = 10)
    private String modUsr;

    @Column(name = "MOD_FECHA")
    private LocalDate modFecha;

    @ColumnDefault("0")
    @Column(name = "IFM_INACTIVO")
    private Boolean inactivo;
}
