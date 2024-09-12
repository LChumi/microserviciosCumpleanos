package com.cumpleanos.models.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "PROGRAMA")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
        "modulo", "seguridad", "accesos", "dopciones"
})
public class Programa {

    @Id
    @Column(name = "PRG_CODIGO", nullable = false)
    private Long id;

    @Size(max = 20)
    @NotNull
    @Column(name = "PRG_ID", nullable = false, length = 20)
    private String prgId;

    @Size(max = 100)
    @NotNull
    @Column(name = "PRG_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @NotNull
    @Column(name = "PRG_TIPO", nullable = false)
    private Boolean tipo = false;

    @Column(name = "PRG_INACTIVO")
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

    @Size(max = 200)
    @Column(name = "PRG_ID_APEX", length = 200)
    private String idApex;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRG_MODULO", referencedColumnName = "MOD_CODIGO", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Modulo modulo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRG_SEGURIDAD", referencedColumnName = "SEG_CODIGO", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Seguridad seguridad;

    @OneToMany(mappedBy = "programa", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Acceso> accesos= new LinkedHashSet<>();

    @OneToMany(mappedBy = "programa", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Dopcion> dopciones= new LinkedHashSet<>();
}
