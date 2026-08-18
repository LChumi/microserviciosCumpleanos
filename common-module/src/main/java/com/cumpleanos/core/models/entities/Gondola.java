package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.GondolaId;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "GONDOLA", schema = "DATA_USR", indexes = {
        @Index(name = "GONDOLA_NIDX1", columnList = "GON_BODEGA, GON_EMPRESA"),
        @Index(name = "GONDOLA_NIDX2", columnList = "GON_SECCION, GON_EMPRESA")
})
public class Gondola {

    @EmbeddedId
    private GondolaId id;

    @Column(name = "GON_ID", length = 20)
    private String gonId;

    @Column(name = "GON_NOMBRE", length = 100)
    private String nombre;

    @ColumnDefault("0")
    @Column(name = "GON_INACTIVO")
    private Boolean inactivo;

    @Column(name = "CREA_USR", length = 10)
    private String creaUsr;

    @Column(name = "CREA_FECHA")
    private LocalDate creaFecha;

    @Column(name = "MOD_USR", length = 10)
    private String modUsr;

    @Column(name = "MOD_FECHA")
    private LocalDate modFecha;

    @Column(name = "GON_USUARIO")
    private Long gonUsuario;

    @ColumnDefault("0")
    @Column(name = "GON_GENERAL")
    private Boolean gonGeneral;

    @Column(name = "GON_BODEGA")
    private Long bodega;

    @Column(name = "GON_SECCION")
    private Long seccion;

}