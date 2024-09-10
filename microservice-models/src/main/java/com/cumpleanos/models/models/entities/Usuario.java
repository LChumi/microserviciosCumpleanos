package com.cumpleanos.models.models.entities;

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
@Table(name = "USUARIO")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
        "seguridad", "accesos", "agentes"
})
public class Usuario {

    @Id
    @Column(name = "USR_CODIGO", nullable = false)
    private Long id;

    @Size(max = 10)
    @NotNull
    @Column(name = "USR_ID", nullable = false, length = 10)
    private String usrId;

    @Size(max = 100)
    @NotNull
    @Column(name = "USR_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "USR_INACTIVO")
    private Boolean inactivo;

    @Size(max = 250)
    @Column(name = "USR_PATH", length = 250)
    private String path;

    @Size(max = 80)
    @Column(name = "USR_IMPRESORA", length = 80)
    private String impresora;

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

    @Size(max = 10)
    @NotNull
    @Column(name = "USR_CLAVE", nullable = false, length = 10)
    private String clave;

    @Column(name = "USR_POL_CALIFICACION")
    private Boolean polCalificacion;

    @Size(max = 80)
    @Column(name = "USR_IMPRESORA_A4", length = 80)
    private String impresoraA4;

    @Column(name = "USR_EMPRESA_DEF")
    private Long empresaDef;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USR_SEGURIDAD", referencedColumnName = "SEG_CODIGO", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Seguridad seguridad;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Set<Acceso> accesos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Set<Agente> agentes = new LinkedHashSet<>();
}
