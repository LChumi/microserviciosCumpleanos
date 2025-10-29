package com.cumpleanos.pos.persistence.entity;

import com.cumpleanos.pos.persistence.ids.CajaPOSId;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "CAJA_POS")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class CajaPOS {

    @EmbeddedId
    private CajaPOSId id;

    @Column(name = "CAP_DESCRIPCION")
    private String descripcion;

    @Column(name = "CAP_ALMACEN")
    private Long almacen;

    @Column(name = "CAP_PVENTA")
    private Long pventa;

    @Column(name = "CAP_ID")
    private String capId;

    @Size(max = 20)
    @Column(name = "CAP_IP", length = 20)
    private String ip;

    @Size(max = 50)
    @Column(name = "CAP_NOMBRE_EQUIPO", length = 50)
    private String nombreEquipo;

    @Size(max = 10)
    @Column(name = "CAP_PUERTO", length = 10)
    private String puerto;

    @Size(max = 10)
    @Column(name = "CREA_USR", length = 10)
    private String creaUsr;

    @Column(name = "CREA_FECHA")
    private LocalDate creaFecha;

    @Size(max = 10)
    @Column(name = "MOD_USR", length = 10)
    private String modUsr;

    @Column(name = "MOD_FECHA")
    private LocalDate modFecha;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "CAP_EMPRESA", referencedColumnName = "FIN_EMPRESA", insertable = false, updatable = false),
            @JoinColumn(name = "CAP_FINANCIERA", referencedColumnName = "FIN_CODIGO", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Financiera financiera;

    @Column(name = "CAP_IP_DTF")
    private String ipDtf;

    @Column(name = "CAP_PUERTO_DTF")
    private String puertoDtf;
}
