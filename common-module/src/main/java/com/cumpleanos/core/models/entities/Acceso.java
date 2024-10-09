package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.AccesoId;
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
@Table(name = "ACCESO", indexes = {
        @Index(name = "ACCESO_NIDX7", columnList = "ACC_USUARIO"),
        @Index(name = "ACCESO_NIDX5", columnList = "ACC_PROGRAMA"),
        @Index(name = "ACCESO_NIDX4", columnList = "ACC_MENU"),
        @Index(name = "ACCESO_NIDX1", columnList = "ACC_CCO_COMPROBA, ACC_EMPRESA"),
        @Index(name = "ACCESO_NIDX3", columnList = "ACC_ALMACEN, ACC_EMPRESA"),
        @Index(name = "ACCESO_NIDX2", columnList = "ACC_PVENTA, ACC_ALMACEN, ACC_EMPRESA")
})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
        "sistema", "usuario", "puntoVenta", "programa", "menu", "ccomproba"
})
public class Acceso {

    @EmbeddedId
    private AccesoId id;

    @NotNull
    @Column(name = "ACC_TIPO", nullable = false)
    private Boolean tipo = false;

    @Column(name = "ACC_INACTIVO")
    private Boolean inactivo;

    @Column(name = "ACC_INGRESA")
    private Boolean ingresa;

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

    @Column(name = "ACC_EMPRESA_DEF")
    private Boolean empresaDef;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ACC_EMPRESA", referencedColumnName = "SIS_CODIGO", insertable = false, updatable = false)
    private Sistema sistema;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ACC_USUARIO", referencedColumnName = "USR_CODIGO", insertable = false, updatable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "ACC_EMPRESA", referencedColumnName = "PVE_EMPRESA", insertable = false, updatable = false),
            @JoinColumn(name = "ACC_ALMACEN", referencedColumnName = "PVE_ALMACEN", insertable = false, updatable = false),
            @JoinColumn(name = "ACC_PVENTA", referencedColumnName = "PVE_SECUENCIA", insertable = false, updatable = false)
    })
    private PuntoVenta puntoVenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ACC_PROGRAMA",referencedColumnName = "PRG_CODIGO", insertable = false, updatable = false)
    private Programa programa;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ACC_MENU",referencedColumnName = "MNU_CODIGO", insertable = false, updatable = false)
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "ACC_CCO_COMPROBA", referencedColumnName = "CCO_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "ACC_EMPRESA", referencedColumnName = "CCO_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Ccomproba ccomproba;
}
