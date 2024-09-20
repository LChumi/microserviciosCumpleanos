package core.cumpleanos.models.entities;

import core.cumpleanos.models.ids.RetDatoId;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "RETDATO", indexes = {
        @Index(name = "RETDATOS_UDX1", columnList = "RTD_ID, RTD_TABLACOA, RTD_EMPRESA", unique = true),
        @Index(name = "RETDATO_NIDX1", columnList = "RTD_EMPRESA"),
        @Index(name = "RETDATO_NIDX2", columnList = "RTD_TABLACOA, RTD_EMPRESA")
})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
        "autclientes"
})
public class RetDato {

    @EmbeddedId
    private RetDatoId id;

    @Size(max = 10)
    @NotNull
    @Column(name = "RTD_ID", nullable = false, length = 10)
    private String rtdId;

    @Size(max = 100)
    @NotNull
    @Column(name = "RTD_CAMPO", nullable = false, length = 100)
    private String campo;

    @ColumnDefault("0")
    @Column(name = "RTD_INACTIVO")
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
    @Column(name = "RTD_NOMBRE", length = 100)
    private String nombre;

    @Column(name = "RTD_CREDITO")
    private Boolean credito;

    @JsonBackReference
    @OneToMany(mappedBy = "retDato", fetch = FetchType.LAZY)
    private Set<Autcliente> autclientes = new LinkedHashSet<>();
}
