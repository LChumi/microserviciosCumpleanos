package core.cumpleanos.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "COPCION")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@SequenceGenerator(name = "COPCION_S_CODIGO", sequenceName = "COPCION_S_CODIGO", allocationSize = 1)
public class Copcion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COPCION_S_CODIGO")
    @Column(name = "COP_CODIGO", nullable = false)
    private Long id;

    @Size(max = 10)
    @NotNull
    @Column(name = "COP_ID", nullable = false, length = 10)
    private String copId;

    @Size(max = 50)
    @NotNull
    @Column(name = "COP_NOMBRE", nullable = false, length = 50)
    private String copNombre;

    @Column(name = "COP_INACTIVO")
    private Boolean copInactivo;

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

    /*@JsonBackReference
    @OneToMany(mappedBy = "copcion", fetch = FetchType.LAZY)
    private Set<Dopcion> dopciones = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "copcion", fetch = FetchType.LAZY)
    private Set<Menu> menus = new LinkedHashSet<>();*/
}
