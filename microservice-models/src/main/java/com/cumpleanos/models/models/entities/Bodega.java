package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.BodegaId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "BODEGA")
@Data
public class Bodega {

    @EmbeddedId
    private BodegaId id;

    @Size(max = 10)
    @NotNull
    @Column(name = "BOD_ID", nullable = false, length = 10)
    private String bodId;

    @Size(max = 100)
    @NotNull
    @Column(name = "BOD_NOMBRE", nullable = false, length = 100)
    private String bodNombre;

    @Column(name = "BOD_CONSIGNA")
    private Boolean bodConsigna;

    @Size(max = 100)
    @Column(name = "BOD_UBICACION", length = 100)
    private String bodUbicacion;
}
