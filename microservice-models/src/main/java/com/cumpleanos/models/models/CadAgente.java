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
@Table(name = "CADAGENTE")
@Data
public class CadAgente {

    @Id
    @Column(name = "CAD_CODIGO")
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "CAD_EMPRESA")
    private Long empresa;

    @Column(name = "CAD_AGENTE")
    private Long agente;

    @Column(name = "CAD_AGENCIA")
    private String agencia;

    @Column(name = "CAD_REPORTA")
    private Long reporta;

    @Column(name = "CAD_ORDEN")
    private Long orden;

    @Column(name = "CAD_INACTIVO")
    private Long inactivo;

    @Column(name = "CAD_FECHA_INICIAL")
    private LocalDate fechaInicial;

    @Column(name = "CAD_FECHA_FINAL")
    private LocalDate fechaFinal;

    @Column(name = "CAD_ID")
    private String cadId;
}
