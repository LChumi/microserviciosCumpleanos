package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.CatClienteId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "CATCLIENTE")
@Data
public class CatCliente {

    @EmbeddedId
    private CatClienteId id;

    @Size(max = 10)
    @NotNull
    @Column(name = "CAT_ID", nullable = false, length = 10)
    private String catId;

    @Size(max = 100)
    @NotNull
    @Column(name = "CAT_NOMBRE", nullable = false, length = 100)
    private String catNombre;

    @NotNull
    @Column(name = "CAT_ORDEN", nullable = false)
    private Long catOrden;

    @Column(name = "CAT_INACTIVO")
    private Boolean catInactivo;

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

    @NotNull
    @Column(name = "CAT_TIPO", nullable = false)
    private Boolean catTipo = false;
}
