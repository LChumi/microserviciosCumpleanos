package com.cumpleanos.models.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "MENU")
@Data
public class Menu {

    @Id
    @Column(name = "MNU_CODIGO", nullable = false)
    private Long id;

    @Size(max = 10)
    @NotNull
    @Column(name = "MNU_ID", nullable = false, length = 10)
    private String mnuId;

    @Size(max = 50)
    @NotNull
    @Column(name = "MNU_NOMBRE", nullable = false, length = 50)
    private String mnuNombre;

    @Column(name = "MNU_INACTIVO")
    private Boolean mnuInactivo;

    @NotNull
    @Column(name = "MNU_ORDEN", nullable = false)
    private Long mnuOrden;

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
    @Column(name = "MNU_SEGURIDAD", nullable = false)
    private Boolean mnuSeguridad = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MNU_REPORTA", referencedColumnName = "MNU_CODIGO", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Menu reporte;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MNU_COPCION", referencedColumnName = "COP_CODIGO", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Copcion copcion;

    @OneToMany(mappedBy = "menu")
    private Set<Acceso> accesos= new LinkedHashSet<>();


}
