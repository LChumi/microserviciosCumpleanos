package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.PuntoVentaId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity
@Table(name = "PUNTOVENTA")
@Data
public class PuntoVenta {

    @EmbeddedId
    private PuntoVentaId id;

    @Size(max = 100)
    @NotNull
    @Column(name = "PVE_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Size(max = 10)
    @NotNull
    @Column(name = "PVE_ID", nullable = false, length = 10)
    private String pveId;

    @Size(max = 100)
    @Column(name = "PVE_RESPONSABLE", length = 100)
    private String responsable;

    @Column(name = "PVE_INACTIVO")
    private Boolean inactivo;

    @Column(name = "PVE_CENTRO")
    private Long centro;

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

    @Column(name = "PVE_FECHAVALIDEZ")
    private LocalDate fechavalidez;

    @Size(max = 15)
    @Column(name = "PVE_NROAUTORIZA", length = 15)
    private String nroautoriza;

    @Column(name = "PVE_MULTIAGENTE")
    private Short multiagente;

    @Size(max = 10)
    @Column(name = "PVE_MARCA", length = 10)
    private String marca;

    @Size(max = 15)
    @Column(name = "PVE_MODELO", length = 15)
    private String modelo;

    @Size(max = 20)
    @Column(name = "PVE_SERIE", length = 20)
    private String serie;

    @Column(name = "PVE_ELECTRONICO")
    private Boolean electronico;

    @ColumnDefault("1")
    @Column(name = "PVE_REPORTE")
    private Boolean reporte;

    @ColumnDefault("1")
    @Column(name = "PVE_IMPRESORA")
    private Boolean impresora;
}
