package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.UsrbodId;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "USRBOD")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class UsrBod {

    @EmbeddedId
    private UsrbodId id;

    @Column(name = "UBO_INACTIVO")
    private Boolean uboInactivo;

    @Column(name = "CREA_USR", length = 10)
    private String creaUsr;

    @Column(name = "CREA_FECHA")
    private LocalDate creaFecha;

    @Column(name = "MOD_USR", length = 10)
    private String modUsr;

    @Column(name = "MOD_FECHA")
    private LocalDate modFecha;

    @Column(name = "UBO_DEFAULT")
    private Boolean uboDefault;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "UBO_BODEGA", referencedColumnName = "BOD_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "UBO_EMPRESA", referencedColumnName = "BOD_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Bodega bodega;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "UBO_EMPRESA", referencedColumnName = "SIS_CODIGO", insertable = false, updatable = false)
    private Sistema sistema;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "UBO_USUARIO", referencedColumnName = "USR_CODIGO", insertable = false, updatable = false)
    private Usuario usuario;
}
