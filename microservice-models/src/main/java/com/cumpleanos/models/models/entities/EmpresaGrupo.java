package com.cumpleanos.models.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "EMPRESA_GRUPO")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {
        "sistemas"
})
public class EmpresaGrupo {

    @Id
    @Column(name = "EMG_CODIGO", nullable = false)
    private Long id;

    @Size(max = 50)
    @Column(name = "EMG_ID", length = 50)
    private String emgId;

    @Size(max = 200)
    @Column(name = "EMG_NOMBRE", length = 200)
    private String emgNombre;

    @Size(max = 200)
    @Column(name = "EMG_NOMBRE_CORTO", length = 200)
    private String emgNombreCorto;

    @Size(max = 500)
    @Column(name = "EMG_URL_WEBSERVICE", length = 500)
    private String emgUrlWebservice;

    @Column(name = "EMG_EMPRESA_DEF")
    private Long emgEmpresaDef;

    @Size(max = 500)
    @Column(name = "EMG_RUTA_IMAGEN", length = 500)
    private String emgRutaImagen;

    @Size(max = 1000)
    @Column(name = "EMG_TOKEN", length = 1000)
    private String emgToken;

    @OneToMany(mappedBy = "empresaGrupo", fetch = FetchType.LAZY)
    private Set<Sistema> sistemas = new LinkedHashSet<>();
}
