package com.cumpleanos.models.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "SEGURIDAD")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
        "modulos", "programas", "usuarios"
})
public class Seguridad {

    @Id
    @Column(name = "SEG_CODIGO", nullable = false)
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "SEG_NOMBRE", nullable = false, length = 100)
    private String segNombre;

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

    @OneToMany(mappedBy = "seguridad", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Modulo> modulos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "seguridad", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Programa> programas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "seguridad", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Usuario> usuarios = new LinkedHashSet<>();
}
