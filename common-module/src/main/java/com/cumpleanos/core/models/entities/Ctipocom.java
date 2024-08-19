package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.CtipocomId;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "CTIPOCOM", schema = "DATA_USR", indexes = {
        @Index(name = "CTIPOCOM_UIDX1", columnList = "CTI_ID, CTI_EMPRESA", unique = true),
        @Index(name = "CTIPOCOM_UIDX2", columnList = "CTI_NOMBRE, CTI_EMPRESA", unique = true),
        @Index(name = "CTIPOCOM_NIDX1", columnList = "CTI_EMPRESA"),
        @Index(name = "CTIPOCOM_NIDX2", columnList = "CTI_RETDATO, CTI_TABLACOA, CTI_EMPRESA")
})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {})
public class Ctipocom {

    @EmbeddedId
    private CtipocomId id;

    @Column(name = "CTI_ID", nullable = false, length = 10)
    private String ctiId;

    @Column(name = "CTI_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "CTI_TIPO", nullable = false)
    private Boolean tipo = false;

    @Column(name = "CTI_AUTORIZA", nullable = false)
    private Boolean autoriza = false;

    @Column(name = "CTI_INACTIVO", nullable = false)
    private Boolean inactivo = false;

    @Column(name = "CREA_USR", length = 10)
    private String creaUsr;

    @Column(name = "CREA_FECHA")
    private LocalDate creaFecha;

    @Column(name = "MOD_USR", length = 10)
    private String modUsr;

    @Column(name = "MOD_FECHA")
    private LocalDate modFecha;

    @Column(name = "CTI_MANTENER_VALOR")
    private Boolean mantenerValor;

    @Column(name = "CTI_IVA")
    private Boolean iva;

    @Column(name = "CTI_DEBCRE")
    private Boolean debcre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CTI_EMPRESA", referencedColumnName = "SIS_CODIGO", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Sistema sistema;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CTI_RETDATO", referencedColumnName = "RTD_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CTI_TABLACOA", referencedColumnName = "RTD_TABLACOA", insertable = false, updatable = false),
            @JoinColumn(name = "CTI_EMPRESA", referencedColumnName = "RTD_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private RetDato retDato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "CTI_CUENTA", referencedColumnName = "CUE_CODIGO", insertable = false, updatable = false),
            @JoinColumn(name = "CTI_EMPRESA", referencedColumnName = "CUE_EMPRESA", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Cuenta cuenta;
}
