package core.cumpleanos.models.entities;

import core.cumpleanos.models.ids.CparametId;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "CPARAMET", indexes = {
        @Index(name = "CPARAMET_UDX1", columnList = "CPA_SECUENCIA, CPA_SIGLA, CPA_EMPRESA", unique = true)
})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
})
public class Cparamet {

    @EmbeddedId
    private CparametId id;

    @Column(name = "CPA_SIGLA", length = 3)
    private String cpaSigla;

    @Column(name = "CPA_SECUENCIA", length = 3)
    private String cpaSecuencia;

    @Column(name = "CPA_NOMBRE", nullable = false, length = 100)
    private String cpaNombre;

    @Column(name = "CPA_TEXTO", length = 20)
    private String cpaTexto;

    @Column(name = "CPA_VALOR", precision = 17, scale = 4)
    private BigDecimal cpaValor;

    @Column(name = "CPA_CUENTA")
    private Long cpaCuenta;

    @Column(name = "CPA_INACTIVO")
    private Boolean cpaInactivo;

    @Column(name = "CREA_USR", length = 10)
    private String creaUsr;

    @Column(name = "CREA_FECHA")
    private LocalDate creaFecha;

    @Column(name = "MOD_USR", length = 10)
    private String modUsr;

    @Column(name = "MOD_FECHA")
    private LocalDate modFecha;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "CPA_EMPRESA", referencedColumnName = "SIS_CODIGO", insertable = false, updatable = false)
    private Sistema sistema;
}
