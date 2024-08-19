package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.EmpaqueCajaId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "EMPAQUE_CAJA", schema = "PRG_USR")
public class EmpaqueCaja {

    @EmbeddedId
    private EmpaqueCajaId id;

    @Column(name = "EMC_CREPOSICION")
    private Long creposicion;

    @Column(name = "EMC_CAJA")
    private Long caja;

    @Column(name = "EMC_BODEGUERO")
    private Object emcBodeguero;

    @Column(name = "EMC_PESO")
    private Object emcPeso;

    @Column(name = "EMC_ESTADO")
    private Object emcEstado;

    @Column(name = "EMC_EMP_COMPROBA")
    private Object emcEmpComproba;

    @Column(name = "EMC_CCO_COMPROBA")
    private Object emcCcoComproba;

    @Column(name = "CREA_USR")
    private Object creaUsr;

    @Column(name = "MOD_USR")
    private Object modUsr;

    @Column(name = "CREA_FECHA")
    private Object creaFecha;

    @Column(name = "MOD_FECHA")
    private Object modFecha;

    @Column(name = "EMC_CODIGO")
    private Object emcCodigo;
}
