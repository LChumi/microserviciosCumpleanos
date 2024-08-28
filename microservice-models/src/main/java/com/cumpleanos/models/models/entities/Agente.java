package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.AgenteId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "AGENTE")
@Data
public class Agente {

    @EmbeddedId
    private AgenteId id;

    @Size(max = 100)
    @NotNull
    @Column(name = "AGE_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Size(max = 10)
    @NotNull
    @Column(name = "AGE_ID", nullable = false, length = 10)
    private String ageId;

    @NotNull
    @Column(name = "AGE_PORC_COMISION", nullable = false, precision = 5, scale = 2)
    private BigDecimal porcComision;

    @Column(name = "AGE_EMPLEADO")
    private Long empleado;

    @ColumnDefault("0")
    @Column(name = "AGE_INACTIVO")
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

    @Column(name = "AGE_CLIENTE")
    private Long cliente;

    @Column(name = "AGE_CUEGASTO")
    private Long ageCuegasto;

    @Column(name = "AGE_DEPARTAMENTO")
    private Boolean departamento;

    @Size(max = 10)
    @Column(name = "AGE_CEDULA", length = 10)
    private String cedula;

    @Column(name = "AGE_REPPRE")
    private Boolean reppre;

    @Column(name = "AGE_REPCOB")
    private Boolean repcob;

    @Column(name = "AGE_REPSAL")
    private Boolean repsal;

    @Column(name = "AGE_DIAS_COMISION", precision = 17, scale = 4)
    private BigDecimal diasComision;
}
