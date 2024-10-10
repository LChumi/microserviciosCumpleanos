package com.cumpleanos.core.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "SEGURIDAD")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
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

    /*@JsonBackReference
    @OneToMany(mappedBy = "seguridad", fetch = FetchType.LAZY)
    private Set<Modulo> modulos = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "seguridad", fetch = FetchType.LAZY)
    private Set<Programa> programas = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "seguridad", fetch = FetchType.LAZY)
    private Set<Usuario> usuarios = new LinkedHashSet<>();*/
}
