package com.cumpleanos.core.models.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
@ToString
public class DtipocomId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "DTI_SERIE", nullable = false)
    private Long serie;

    @Column(name = "DTI_ALMACEN", nullable = false)
    private Long almacen;

    @Column(name = "DTI_CTI_CODIGO", nullable = false)
    private Long ctiCodigo;

    @Column(name = "DTI_PERIODO", nullable = false)
    private Short periodo;

    @Column(name = "DTI_EMPRESA", nullable = false)
    private Long empresa;
}
