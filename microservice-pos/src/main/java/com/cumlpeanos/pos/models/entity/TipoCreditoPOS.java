package com.cumlpeanos.pos.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Table(name = "TIPO_CREDITO_POS")
@Data
public class TipoCreditoPOS {

    @Id
    @Column(name = "TCR_CODIGO")
    @Setter(AccessLevel.NONE)
    private Long codigo;

    @Column(name = "TCR_EMPRESA")
    private Long empresa;

    @Column(name = "TCR_ID")
    private String tcrId;

    @Column(name = "TCR_NOMBRE")
    private String nombre;

    @Column(name = "TCR_INTERES")
    private Long interes;

}
