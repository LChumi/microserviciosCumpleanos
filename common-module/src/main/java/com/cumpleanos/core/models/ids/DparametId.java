package com.cumpleanos.core.models.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class DparametId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "DPA_EMPRESA", nullable = false)
    private Long dpaEmpresa;

    @Column(name = "DPA_ALMACEN", nullable = false)
    private Long dpaAlmacen;

    @Column(name = "DPA_CPA_CODIGO", nullable = false)
    private Long dpaCpaCodigo;
}
