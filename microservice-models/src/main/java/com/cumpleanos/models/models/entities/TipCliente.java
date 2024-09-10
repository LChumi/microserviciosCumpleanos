package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.TipClienteId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "TIPCLIENTE")
@Data
public class TipCliente {

    @EmbeddedId
    private TipClienteId id;

    @Size(max = 10)
    @NotNull
    @Column(name = "TCL_ID", nullable = false, length = 10)
    private String tclId;

    @Size(max = 100)
    @NotNull
    @Column(name = "TCL_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "TCL_REPORTA")
    private Long reporta;

    @NotNull
    @Column(name = "TCL_ORDEN", nullable = false)
    private Long orden;

    @Column(name = "TCL_INACTIVO")
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

    @NotNull
    @Column(name = "TCL_TIPO", nullable = false)
    private Boolean tipo = false;

    @Column(name = "TCL_DESCUENTO", precision = 17, scale = 4)
    private BigDecimal descuento;

    @Column(name = "TCL_DESCUENTO_MAX", precision = 17, scale = 4)
    private BigDecimal descuentoMax;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TCL_EMPRESA", referencedColumnName = "SIS_CODIGO", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Sistema sistema;

    @OneToMany(mappedBy = "tipCliente")
    private Set<Cliente> clientes = new LinkedHashSet<>();
}
