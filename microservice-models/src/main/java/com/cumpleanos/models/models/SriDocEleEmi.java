package com.cumpleanos.models.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "SRI_DOC_ELE_EMI")
@Data
public class SriDocEleEmi {

    @Id
    @Column(name = "SRI_NUMERO_AUTORIZACION")
    @Setter(AccessLevel.NONE)
    private String id;

    @Column(name = "SRI_EMPRESA")
    private Long empresa;

    @Column(name = "SRI_FECHA")
    private LocalDate fecha;

    @Column(name = "SRI_COMPROBANTE")
    private String comprobante;

    @Column(name = "SRI_SERIE_COMPROBANTE")
    private String serieComprobante;

    @Column(name = "SRI_RUC_EMISOR")
    private String rucEmisor;

    @Column(name = "SRI_RAZON_SOCIAL_EMISOR")
    private String razonSocialEmisor;

    @Column(name = "SRI_FECHA_EMISION")
    private LocalDate fechaEmision;

    @Column(name = "SRI_FECHA_AUTORIZACION")
    private LocalDate fechaAutorizacion;

    @Column(name = "SRI_TIPO_EMISION")
    private String tipoEmision;

    @Column(name = "SRI_IDENTIFICACION_RECEPTOR")
    private String identificacionReceptor;

    @Column(name = "SRI_CLAVE_ACCESO")
    private String claveAcceso;

    @Column(name = "SRI_NUMERO_AUTORIZACION")
    private Long numeroAutorizacion;

}
