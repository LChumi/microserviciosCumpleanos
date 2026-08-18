package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.EmpaqueCajaId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

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
    private Long emcBodeguero;

    @Column(name = "EMC_PESO")
    private Integer emcPeso;

    @Column(name = "EMC_ESTADO")
    private Integer emcEstado;

    @Column(name = "EMC_EMP_COMPROBA")
    private Long emcEmpComproba;

    @Column(name = "EMC_CCO_COMPROBA")
    private BigInteger emcCcoComproba;
}
