package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.DopcionId;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "DOPCION")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
        "copcion", "programa"
})
public class Dopcion {

    @EmbeddedId
    private DopcionId id;

    @Size(max = 10)
    @NotNull
    @Column(name = "DOP_ID", nullable = false, length = 10)
    private String dopId;

    @Size(max = 50)
    @NotNull
    @Column(name = "DOP_NOMBRE", nullable = false, length = 50)
    private String dopNombre;

    @Column(name = "DOP_INACTIVO")
    private Boolean dopInactivo;

    @NotNull
    @Column(name = "DOP_TIPO", nullable = false)
    private Boolean dopTipo = false;

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
    @Column(name = "DOP_ORDEN", nullable = false)
    private Short orden;

    @Column(name = "DOP_PARAMETRO")
    private Short parametro;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DOP_COP_CODIGO", referencedColumnName = "COP_CODIGO" , insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Copcion copcion;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DOP_PROGRAMA", referencedColumnName = "PRG_CODIGO" , insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Programa programa;
}
