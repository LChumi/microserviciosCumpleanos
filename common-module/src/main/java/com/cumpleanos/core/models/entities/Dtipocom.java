package com.cumpleanos.core.models.entities;

import com.cumpleanos.core.models.ids.DtipocomId;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "DTIPOCOM", indexes = {
        @Index(name = "DTIPOCOM_NIDX1", columnList = "DTI_EMPRESA")
})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {})
public class Dtipocom {

    @EmbeddedId
    private DtipocomId id;

    @Column(name = "DTI_NUMERO", nullable = false)
    private Long dtiNumero;

    @Column(name = "CREA_USR", length = 10)
    private String creaUsr;

    @Column(name = "CREA_FECHA")
    private LocalDate creaFecha;

    @Column(name = "MOD_USR", length = 10)
    private String modUsr;

    @Column(name = "MOD_FECHA")
    private LocalDate modFecha;

    @Column(name = "DTI_FECHAVALIDES")
    private LocalDate dtiFechavalides;

    @Column(name = "DTI_NROAUTORIZA", length = 15)
    private String dtiNroautoriza;

    @Column(name = "DTI_NUMEROINICIAL")
    private Long dtiNumeroinicial;

    @Column(name = "DTI_NUMEROFINAL")
    private Long dtiNumerofinal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DTI_EMPRESA", referencedColumnName = "SIS_CODIGO", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Sistema sistema;
}
