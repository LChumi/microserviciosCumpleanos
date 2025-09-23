package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.ProductoPartidaId;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "PRODUCTO_PARTIDA", schema = "DATA_USR", indexes = {
        @Index(name = "PRODUCTO_PARTIDA_UIDX1", columnList = "PPA_PRODUCTO, PPA_EMPRESA"),
        @Index(name = "PRODUCTO_PARTIDA_UIDX2", columnList = "PPA_PARTIDA, PPA_EMPRESA")
})
public class ProductoPartida {

    @EmbeddedId
    private ProductoPartidaId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "PPA_PARTIDA", referencedColumnName = "IPR_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "PPA_EMPRESA", referencedColumnName = "IPR_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Imppartida partida;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "PPA_PRODUCTO", referencedColumnName = "PRO_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "PPA_EMPRESA", referencedColumnName = "PRO_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Producto producto;

    @Column(name = "PPA_INACTIVO", nullable = false)
    private Boolean inactivo = false;

    @Column(name = "CREA_USR", length = 10)
    private String creaUsr;

    @Column(name = "CREA_FECHA")
    private LocalDate creaFecha;

    @Column(name = "MOD_USR", length = 10)
    private String modUsr;

    @Column(name = "MOD_FECHA")
    private LocalDate modFecha;

    @ColumnDefault("0")
    @Column(name = "PPA_DEFAUL", nullable = false)
    private Boolean defaul = false;
}
