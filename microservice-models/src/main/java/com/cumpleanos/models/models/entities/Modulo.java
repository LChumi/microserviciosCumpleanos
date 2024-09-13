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
@Table(name = "MODULO")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
        "seguridad", "comprobas", "cuentas", "programas"
})
public class Modulo {

    @Id
    @Column(name = "MOD_CODIGO", nullable = false)
    private Long id;

    @Size(max = 10)
    @NotNull
    @Column(name = "MOD_ID", nullable = false, length = 10)
    private String modId;

    @Size(max = 100)
    @NotNull
    @Column(name = "MOD_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "MOD_INACTIVO")
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MOD_SEGURIDAD", referencedColumnName = "SEG_CODIGO", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Seguridad seguridad;

    @OneToMany(mappedBy = "modulo", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Ccomproba> comprobas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "modulo", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Cuenta> cuentas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "modulo", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Programa> programas = new LinkedHashSet<>();
}
