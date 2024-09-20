package core.cumpleanos.models.entities;

import core.cumpleanos.models.ids.TabalCoaId;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "TABLACOA", indexes = {
        @Index(name = "TABLACOA_UDX1", columnList = "TAB_NOMBRE, TAB_EMPRESA", unique = true),
        @Index(name = "TABLACOA_UDX2", columnList = "TAB_ID, TAB_EMPRESA", unique = true)
})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
        "sistema"
})
public class TablaCoa {

    @EmbeddedId
    private TabalCoaId id;

    @ColumnDefault("0")
    @Column(name = "TAB_INACTIVO")
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

    @Size(max = 100)
    @NotNull
    @Column(name = "TAB_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Size(max = 10)
    @NotNull
    @Column(name = "TAB_ID", nullable = false, length = 10)
    private String tabId;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TAB_EMPRESA", referencedColumnName = "SIS_CODIGO" , insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Sistema sistema;
}
