package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.AlmacenId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ALMACEN")
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
}
