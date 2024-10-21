package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.LineaId;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "LINEA", uniqueConstraints = {
        @UniqueConstraint(name = "LINEA_UK", columnNames = {"LIN_ID", "LIN_EMPRESA"})
})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
        "sistema"
})
public class Linea {

    @EmbeddedId
    private LineaId id;

    @Size(max = 30)
    @Column(name = "LIN_ID", length = 30)
    private String linId;

    @Size(max = 100)
    @Column(name = "LIN_NOMBRE", length = 100)
    private String linNombre;

    @ColumnDefault("0")
    @Column(name = "LIN_INACTIVO")
    private Boolean linInactivo;

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

    @Column(name = "LIN_STOCK", precision = 17, scale = 4)
    private BigDecimal linStock;

    @ColumnDefault("0")
    @Column(name = "LIN_GARANTIA")
    private Boolean linGarantia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LIN_EMPRESA", referencedColumnName = "SIS_CODIGO", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Sistema sistema;
}
