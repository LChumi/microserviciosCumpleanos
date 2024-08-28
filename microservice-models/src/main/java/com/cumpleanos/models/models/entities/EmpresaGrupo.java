package com.cumpleanos.models.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "EMPRESA_GRUPO")
@Data
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
}
