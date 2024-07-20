package com.cumpleanos.models.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "CENTRO")
@Data
public class Centro {

    @Id
    @Column(name = "CEN_CODIGO")
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "CEN_EMPRESA")
    private Long empresa;

    @Column(name = "CEN_ID")
    private String cenId;

    @Column(name = "CEN_INACTIVO")
    private Boolean inivo;

    @Column(name = "CEN_REPORTA")
    private Long reporta;

    @Column(name = "CEN_ORDEN")
    private Long orden;

    @Column(name = "CEN_DISTRIBUIR")
    private Long distribuir;

    @Column(name = "CEN_CUENTA")
    private Long cuenta;

    @Column(name = "CEN_NORMAL")
    private Long normal;

    @Column(name = "CEN_25")
    private BigDecimal cen25;

    @Column(name = "CEN_50")
    private BigDecimal cen50;

    @Column(name = "CEN_100")
    private BigDecimal cen100;
}
