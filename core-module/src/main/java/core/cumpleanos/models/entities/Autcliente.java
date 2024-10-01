package core.cumpleanos.models.entities;

import core.cumpleanos.models.ids.AutclienteId;
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
@Table(name = "AUTCLIENTE", indexes = {
        @Index(name = "AUTCLIENTE_NIDX2", columnList = "ACL_EMPRESA"),
        @Index(name = "AUTCLIENTE_NIDX1", columnList = "ACL_CLIENTE, ACL_EMPRESA"),
        @Index(name = "AUTCLIENTE_NIDX3", columnList = "ACL_RETDATO, ACL_TABLACOA, ACL_EMPRESA")
})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
        "retDato", "sistema"
})
public class Autcliente {

    @EmbeddedId
    private AutclienteId id;

    @NotNull
    @Column(name = "ACL_TCLIPRO", nullable = false)
    private Short tclipro;

    @Column(name = "ACL_VAL_FECHA")
    private LocalDate valFecha;

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

    @Column(name = "ACL_INACTIVO")
    private Boolean inactivo;

    @Size(max = 9)
    @NotNull
    @Column(name = "ACL_FAC3", nullable = false, length = 9)
    private String fac3;

    @Size(max = 3)
    @NotNull
    @Column(name = "ACL_FACT1", nullable = false, length = 3)
    private String fact1;

    @Size(max = 3)
    @NotNull
    @Column(name = "ACL_FACT2", nullable = false, length = 3)
    private String fact2;

    @Size(max = 9)
    @NotNull
    @Column(name = "ACL_FACT3", nullable = false, length = 9)
    private String fact3;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "ACL_RETDATO", referencedColumnName = "RTD_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "ACL_TABLACOA", referencedColumnName = "RTD_TABLACOA", insertable = false, updatable = false),
            @JoinColumn(name = "ACL_EMPRESA", referencedColumnName = "RTD_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private RetDato retDato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACL_EMPRESA", referencedColumnName = "SIS_CODIGO", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Sistema sistema;
}
