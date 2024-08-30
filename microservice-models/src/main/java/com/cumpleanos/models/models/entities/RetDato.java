package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.RetDatoId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "RETDATO")
@Data
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

    @OneToMany(mappedBy = "retDato")
    private Set<Autcliente> autclientes = new LinkedHashSet<>();
}
