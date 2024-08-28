package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.LineaId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "LINEA")
@Data
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
}
