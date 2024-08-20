package com.cumpleanos.models.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Table(name = "CATCLIENTE")
@Data
public class CatCliente {

    @Id
    @Column(name = "CAT_CODIGO")
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "CAT_EMPRESA")
    private Long empresa;

    @Column(name = "CAT_ID")
    private String catId;

    @Column(name = "CAT_NOMBRE")
    private String nombre;

    @Column(name = "CAT_REPORTA")
    private Long reporta;

    @Column(name = "CAT_ORDEN")
    private Long orden;

    @Column(name = "CAT_INACTIVO")
    private Long inactivo;

    @Column(name = "CAT_TIPO")
    private Long tipo;

    @Column(name = "CAT_UMEDIDA")
    private Long udidad;

    @Column(name = "CAT_LISTAPRE")
    private Long listapre;

}
