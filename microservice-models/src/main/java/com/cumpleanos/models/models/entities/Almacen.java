package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.AlmacenId;
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
@Table(name = "ALMACEN")
@Data
public class Almacen {

    @EmbeddedId
    private AlmacenId id;

    @NotNull
    @Column(name = "ALM_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Size(max = 10)
    @NotNull
    @Column(name = "ALM_ID", nullable = false, length = 10)
    private String almId;

    @Size(max = 100)
    @Column(name = "ALM_GERENTE", length = 100)
    private String gerente;

    @Size(max = 100)
    @Column(name = "ALM_DIRECCION", length = 100)
    private String direccion;

    @Size(max = 12)
    @Column(name = "ALM_TELEFONO1", length = 12)
    private String telefono1;

    @Size(max = 12)
    @Column(name = "ALM_TELEFONO2", length = 12)
    private String telefono2;

    @Size(max = 12)
    @Column(name = "ALM_TELEFONO3", length = 12)
    private String telefono3;

    @Size(max = 13)
    @Column(name = "ALM_RUC", length = 13)
    private String ruc;

    @Size(max = 12)
    @Column(name = "ALM_FAX", length = 12)
    private String fax;

    @Column(name = "ALM_CENTRO")
    private Long centro;

    @Column(name = "ALM_INACTIVO")
    private Boolean inactivo;

    @Column(name = "ALM_CTACAJA")
    private Long ctacaja;

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
    @Column(name = "ALM_BLOQUEO")
    private Boolean bloqueo;

    @Size(max = 3)
    @Column(name = "ALM_SUBFIJO", length = 3)
    private String subfijo;

    @Size(max = 10)
    @Column(name = "ALM_ID_REPORTE", length = 10)
    private String idReporte;

    @Column(name = "ALM_MONTO", precision = 17, scale = 4)
    private BigDecimal almMonto;

    @Column(name = "ALM_NUMERO")
    private Long numero;

    @Column(name = "ALM_CTACAJA1")
    private Long ctacaja1;

    @Column(name = "ALM_MATRIZ")
    private Boolean matriz;
}
