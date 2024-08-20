package com.cumpleanos.models.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Table(name = "TIPCLIENTE")
@Data
public class TipCliente {

    @Id
    @Column(name = "TCL_CODIGO")
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "TCL_ID")
    private String tclId;

    @Column(name = "TCL_NOMBRE")
    private String nombre;

    @Column(name = "TCL_REPORTA")
    private Long reporta;

    @Column(name = "TCL_ORDEN")
    private Long orden;

    @Column(name = "TCL_INACTIVO")
    private Boolean intivo;

    @Column(name = "TCL_TIPO")
    private Long tipo;


}
