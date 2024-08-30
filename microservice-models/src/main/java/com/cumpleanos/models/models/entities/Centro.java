package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.CentroId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "CENTRO")
@Data
public class Centro {

    @EmbeddedId
    private CentroId id;

    @Size(max = 100)
    @NotNull
    @Column(name = "CEN_NOMBRE", nullable = false, length = 100)
    private String cenNombre;

    @Size(max = 10)
    @NotNull
    @Column(name = "CEN_ID", nullable = false, length = 10)
    private String cenId;

    @Column(name = "CEN_INACTIVO")
    private Boolean cenInactivo;

    @NotNull
    @Column(name = "CEN_ORDEN", nullable = false)
    private Long cenOrden;

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
    @Column(name = "CEN_DISTRIBUIR")
    private Boolean cenDistribuir;

    @Column(name = "CEN_NORMAL", precision = 5, scale = 2)
    private BigDecimal cenNormal;

    @Column(name = "CEN_25", precision = 5, scale = 2)
    private BigDecimal cen25;

    @Column(name = "CEN_50", precision = 5, scale = 2)
    private BigDecimal cen50;

    @Column(name = "CEN_100", precision = 5, scale = 2)
    private BigDecimal cen100;

    @OneToMany(mappedBy = "centro")
    private Set<Bodega> bodegas = new LinkedHashSet<>();
}
