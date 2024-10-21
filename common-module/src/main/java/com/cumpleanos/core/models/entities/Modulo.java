package com.cumpleanos.core.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "MODULO", indexes = {
        @Index(name = "MODULO_UIDX2", columnList = "MOD_NOMBRE, MOD_ID", unique = true),
        @Index(name = "MODULO_UIDX1", columnList = "MOD_NOMBRE", unique = true),
        @Index(name = "MODULO_NIDX1", columnList = "MOD_SEGURIDAD")
})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
        "seguridad"
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
    @JoinColumn(name = "MOD_SEGURIDAD", referencedColumnName = "SEG_CODIGO")
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Seguridad seguridad;

    /*@JsonBackReference
    @OneToMany(mappedBy = "modulo", fetch = FetchType.LAZY)
    private Set<Ccomproba> comprobas = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "modulo", fetch = FetchType.LAZY)
    private Set<Cuenta> cuentas = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "modulo", fetch = FetchType.LAZY)
    private Set<Programa> programas = new LinkedHashSet<>();*/
}
