package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.DparametId;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "DPARAMET")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
})
public class Dparamet {

    @EmbeddedId
    private DparametId id;

    @Column(name = "DPA_TEXTO", nullable = false, length = 100)
    private String dpaTexto;

    @Column(name = "DPA_VALOR", precision = 17, scale = 4)
    private BigDecimal dpaValor;

    @ColumnDefault("0")
    @Column(name = "DPA_CUENTA")
    private Long dpaCuenta;

    @Column(name = "DPA_INACTIVO")
    private Boolean dpaInactivo;

    @Column(name = "CREA_USR", length = 10)
    private String creaUsr;

    @Column(name = "CREA_FECHA")
    private LocalDate creaFecha;

    @Column(name = "MOD_USR", length = 10)
    private String modUsr;

    @Column(name = "MOD_FECHA")
    private LocalDate modFecha;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumns({
            @JoinColumn(name = "DPA_CPA_CODIGO", referencedColumnName = "CPA_CODIGO", updatable = false, insertable = false),
            @JoinColumn(name = "DPA_EMPRESA", referencedColumnName = "CPA_EMPRESA", updatable = false, insertable = false),
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Cparamet cparamet;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumns({
            @JoinColumn(name = "DPA_ALMACEN", referencedColumnName = "ALM_CODIGO", updatable = false, insertable = false),
            @JoinColumn(name = "DPA_EMPRESA", referencedColumnName = "ALM_EMPRESA", updatable = false, insertable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Almacen almacen;
}
