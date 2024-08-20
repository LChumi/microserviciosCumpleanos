package com.cumlpeanos.pos.models.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Table(name = "CAJA_POS")
@Data
public class CajaPOS {

    @Id
    @Column(name = "CAP_CODIGO")
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "CAP_EMPRESA")
    private Long empresa;

    @Column(name = "CAP_DESCRIPCION")
    private String descripcion;

    @Column(name = "CAP_ALMACEN")
    private int almacen;

    @Column(name = "CAP_PVENTA")
    private int pventa;

    @Column(name = "CAP_IP")
    private String ip;

    @Column(name = "CAP_NOMBRE_EQUIPO")
    private String nombreEquipo;

    @Column(name = "CAP_PUERTO")
    private String puerto;
}
