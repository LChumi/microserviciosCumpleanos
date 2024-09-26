package core.cumpleanos.models.entities;

import core.cumpleanos.models.ids.PoliticaId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "POLITICA", indexes = {
        @Index(name = "POLITICA_UIDX1", columnList = "POL_NOMBRE, POL_TIPOCLI, POL_EMPRESA", unique = true),
        @Index(name = "POLITICA_UIDX2", columnList = "POL_ID, POL_TIPOCLI, POL_EMPRESA", unique = true),
        @Index(name = "POLITICA_NIDX1", columnList = "POL_EMPRESA")
})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
        "sistema"
})
public class Politica {

    @EmbeddedId
    private PoliticaId id;

    @Size(max = 100)
    @NotNull
    @Column(name = "POL_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Size(max = 10)
    @NotNull
    @Column(name = "POL_ID", nullable = false, length = 10)
    private String polId;

    @NotNull
    @Column(name = "POL_VALOR_DESDE", nullable = false, precision = 17, scale = 4)
    private BigDecimal valorDesde;

    @NotNull
    @Column(name = "POL_VALOR_HASTA", nullable = false, precision = 17, scale = 4)
    private BigDecimal valorHasta;

    @NotNull
    @Column(name = "POL_PORC_DESC", nullable = false, precision = 5, scale = 2)
    private BigDecimal porcDesc;

    @NotNull
    @Column(name = "POL_PORC_FINANC", nullable = false, precision = 5, scale = 2)
    private BigDecimal porcFinanc;

    @NotNull
    @Column(name = "POL_PORC_PAG_CONTA", nullable = false, precision = 5, scale = 2)
    private BigDecimal porcPagConta;

    @NotNull
    @Column(name = "POL_PORC_PRO_PAGO", nullable = false, precision = 5, scale = 2)
    private BigDecimal porcProPago;

    @NotNull
    @Column(name = "POL_DIAS_PLAZO", nullable = false)
    private Short diasPlazo;

    @NotNull
    @Column(name = "POL_NRO_PAGOS", nullable = false)
    private Short nroPagos;

    @NotNull
    @Column(name = "POL_LINEA_CREDITO", nullable = false)
    private Short lineaCredito;

    @Column(name = "POL_INACTIVO")
    private Boolean inactivo;

    @NotNull
    @Column(name = "POL_TIPOCLI", nullable = false)
    private Boolean tipocli = false;

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

    @ColumnDefault("0")
    @Column(name = "POL_DESC_MAXIMO", precision = 5, scale = 2)
    private BigDecimal descMaximo;

    @Column(name = "POL_AUTOVENTA")
    private Boolean autoventa;

    @Column(name = "POL_PREVENTA")
    private Boolean preventa;

    @Column(name = "POL_CALIFICACION")
    private Boolean calificacion;

    @Column(name = "POL_NIVEL")
    private Short nivel;

    @Column(name = "POL_PORC_BONO", precision = 5, scale = 2)
    private BigDecimal porcBono;

    @Column(name = "POL_BONO_MAXIMO", precision = 5, scale = 2)
    private BigDecimal bonoMaximo;

    @Column(name = "POL_PORC_INTERES", precision = 5, scale = 2)
    private BigDecimal porcInteres;

    @Column(name = "POL_TIPO_PRECIO")
    private Boolean tipoPrecio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "POL_EMPRESA", referencedColumnName = "SIS_CODIGO" , insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Sistema sistema;

    /*@JsonBackReference
    @OneToMany(mappedBy = "politica", fetch = FetchType.LAZY)
    private Set<CcomFac> ccomFacs = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "politica", fetch = FetchType.LAZY)
    private Set<Cliente> clientes = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "politicaAdi", fetch = FetchType.LAZY)
    private Set<Cliente> clientesAdi = new LinkedHashSet<>();*/
}
