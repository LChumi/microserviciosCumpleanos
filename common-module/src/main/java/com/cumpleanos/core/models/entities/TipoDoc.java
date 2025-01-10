package com.cumpleanos.core.models.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "TIPODOC", indexes = {
        @Index(name = "TIPODOC_UIDX1", columnList = "TPD_ID", unique = true),
        @Index(name = "TIPODOC_UIDX2", columnList = "TPD_NOMBRE", unique = true),
        @Index(name = "TIPODOC_NIDX1", columnList = "TPD_MODULO"),
        @Index(name = "TIPODOC_NIDX4", columnList = "TPD_PROG_IMP"),
        @Index(name = "TIPODOC_NIDX3", columnList = "TPD_PROG_EJE"),
        @Index(name = "TIPODOC_NIDX2", columnList = "TPD_PROG_CON")
})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {})
public class TipoDoc {

    @Id
    @Column(name = "TPD_CODIGO", nullable = false)
    private Long id;

    @Column(name = "TPD_ID", nullable = false, length = 10)
    private String tpdId;

    @Column(name = "TPD_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "TPD_LINEA")
    private Short linea;

    @Column(name = "TPD_NRO_COPIA", nullable = false)
    private Short nroCopia;

    @Column(name = "TPD_NOCONTABLE", nullable = false)
    private Boolean nocontable = false;

    @Column(name = "CREA_USR", length = 10)
    private String creaUsr;

    @Column(name = "CREA_FECHA")
    private LocalDate creaFecha;

    @Column(name = "MOD_USR", length = 10)
    private String modUsr;

    @Column(name = "MOD_FECHA")
    private LocalDate modFecha;

    @Column(name = "TPD_NIVEL_APROB")
    private Short nivelAprob;

    @Column(name = "TPD_SRI")
    private Short sri;

    @Column(name = "TPD_TABLA", length = 30)
    private String tabla;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TPD_MODULO")
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Modulo modulo;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "TPD_PROG_IMP",referencedColumnName = "PRG_CODIGO")
    private Programa programaImp;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "TPD_PROG_EJE",referencedColumnName = "PRG_CODIGO")
    private Programa programaEje;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "TPD_PROG_CON",referencedColumnName = "PRG_CODIGO")
    private Programa programaCon;
}
