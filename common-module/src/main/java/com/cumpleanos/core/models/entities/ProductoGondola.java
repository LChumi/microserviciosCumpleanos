package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.ProductoGondolaId;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "PRODUCTO_GONDOLA", schema = "DATA_USR", indexes = {
        @Index(name = "PRODUCTO_GONDOLA_NIDX001", columnList = "PGO_PRODUCTO, PGO_EMPRESA"),
        @Index(name = "PRODUCTO_GONDOLA_NIDX003", columnList = "PGO_GONDOLA, PGO_EMPRESA"),
        @Index(name = "PRODUCTO_GONDOLA_NIDX002", columnList = "PGO_BODEGA, PGO_EMPRESA")
}, uniqueConstraints = {
        @UniqueConstraint(name = "PRODUCTO_GONDOLA_UK", columnNames = {"PGO_EMPRESA", "PGO_PRODUCTO", "PGO_GONDOLA", "PGO_BODEGA"})
})
public class ProductoGondola {

    @EmbeddedId
    private ProductoGondolaId id;

    @ColumnDefault("0")
    @Column(name = "PGO_INACTIVO")
    private Boolean pgoInactivo;

    @Column(name = "PGO_PRODUCTO")
    private Long producto;

    @Column(name = "PGO_GONDOLA")
    private Long gondola;

    @Column(name = "PGO_BODEGA")
    private Long bodega;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "PGO_PRODUCTO", referencedColumnName = "PRO_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "PGO_EMPRESA", referencedColumnName = "PRO_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Producto gpoProducto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "PGO_GONDOLA", referencedColumnName = "GON_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "PGO_EMPRESA", referencedColumnName = "GON_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Gondola pgoGondola;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "PGO_BODEGA", referencedColumnName = "BOD_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "PGO_EMPRESA", referencedColumnName = "BOD_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Bodega pgoBodega;

}