package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.StockOptimoId;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "STOCK_OPTIMO", schema = "DATA_USR", indexes = {
        @Index(name = "STOCK_OPTIMO_IDX1", columnList = "STO_USUARIO")
}, uniqueConstraints = {
        @UniqueConstraint(name = "STOCK_OPTIMO_UK", columnNames = {"STO_PRODUCTO", "STO_GONDOLA", "STO_BODEGA", "STO_EMPRESA"})
})
@EqualsAndHashCode(of = "id")
public class StockOptimo {
    @EmbeddedId
    private StockOptimoId id;

    @ColumnDefault("0")
    @Column(name = "STO_MAXIMO", precision = 17, scale = 4)
    private Long maximo;

    @ColumnDefault("0")
    @Column(name = "STO_MINIMO", precision = 17, scale = 4)
    private Long minimo;

    @Column(name = "STO_FECHA_INI")
    private LocalDateTime fechaIni;

    @Column(name = "STO_FECHA_FIN")
    private LocalDateTime fechaFin;

    @ColumnDefault("0")
    @Column(name = "STO_INACTIVO")
    private Boolean inactivo;

    @Column(name = "CREA_USR", length = 10)
    private String creaUsr;

    @Column(name = "CREA_FECHA")
    private LocalDateTime creaFecha;

    @Column(name = "MOD_USR", length = 10)
    private String modUsr;

    @Column(name = "MOD_FECHA")
    private LocalDateTime modFecha;

    @Column(name = "STO_MES_INI")
    private Long mesIni;

    @Column(name = "STO_MES_FIN")
    private Long mesFin;

    @Column(name = "STO_BODEGA")
    private Long bodega;

    @Column(name = "STO_GONDOLA")
    private Long gondola;

    @Column(name = "STO_PRODUCTO")
    private Long producto;

    @Column(name = "STO_USUARIO")
    private Long usuario;
}