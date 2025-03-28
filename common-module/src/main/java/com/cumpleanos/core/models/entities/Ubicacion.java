package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.UbicacionId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "UBICACION", indexes = {
        @Index(name = "USUARIO_UIDX1", columnList = "USR_ID", unique = true),
        @Index(name = "USUARIO_UIDX2", columnList = "USR_NOMBRE", unique = true)
})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {})
public class Ubicacion {

    @EmbeddedId
    private UbicacionId id;

    @Size(max = 10)
    @NotNull
    @Column(name = "UBI_ID", nullable = false, length = 10)
    private String ubiId;

    @Size(max = 100)
    @NotNull
    @Column(name = "UBI_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "UBI_ORDEN")
    private Long orden;

    @Column(name = "UBI_INACTIVO")
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

    @Size(max = 10)
    @Column(name = "UBI_COD_SRI", length = 10)
    private String codSri;

    @Column(name = "UBI_REGION")
    private Boolean region;

    @Size(max = 10)
    @Column(name = "UBI_COD_DNT", length = 10)
    private String codDnt;

    /*@JsonBackReference
    @OneToMany(mappedBy = "ubicacion", fetch = FetchType.LAZY)
    private Set<CcomFac> ccomFacs = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "ciudad", fetch = FetchType.LAZY)
    private Set<Cliente> clientes = new LinkedHashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "parroquia", fetch = FetchType.LAZY)
    private Set<Cliente> clientesParroquia = new LinkedHashSet<>();*/
}
