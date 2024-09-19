package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.CadAgenteId;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "CADAGENTE", indexes = {
        @Index(name = "CADAGENTE_NIDX1", columnList = "CAD_AGENTE, CAD_EMPRESA"),
        @Index(name = "CADAGENTE_UDX1", columnList = "CAD_AGENTE, CAD_INACTIVO, CAD_EMPRESA"),
        @Index(name = "CADAGENTE_NIDX2", columnList = "CAD_REPORTA, CAD_EMPRESA")
})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
        "reporta", "agente", "cadAgentes", "ccomprobas", "puntoVentas"
})
public class CadAgente implements Serializable {

    @EmbeddedId
    private CadAgenteId id;

    @Size(max = 100)
    @Column(name = "CAD_AGENCIA", length = 100)
    private String agencia;

    @NotNull
    @Column(name = "CAD_ORDEN", nullable = false)
    private Long orden;

    @Column(name = "CAD_INACTIVO")
    private Boolean inactivo;

    @NotNull
    @Column(name = "CAD_FECHA_INICIA", nullable = false)
    private LocalDate fechaInicia;

    @Column(name = "CAD_FECHA_FINAL")
    private LocalDate fechaFinal;

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
    @Column(name = "CAD_ID", length = 10)
    private String cadId;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CAD_REPORTA", referencedColumnName = "CAD_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CAD_EMPRESA", referencedColumnName = "CAD_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private CadAgente reporta;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CAD_AGENTE", referencedColumnName = "AGE_CODIGO", insertable = false , updatable = false),
            @JoinColumn(name = "CAD_EMPRESA", referencedColumnName = "AGE_EMPRESA", insertable = false , updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Agente agente;

    @JsonBackReference
    @OneToMany(mappedBy = "reporta", fetch = FetchType.LAZY)
    private Set<CadAgente> cadAgentes = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "cadAgente", fetch = FetchType.LAZY)
    private Set<Ccomproba> ccomprobas = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "cadAgente", fetch = FetchType.LAZY)
    private Set<PuntoVenta> puntoVentas = new LinkedHashSet<>();
}
