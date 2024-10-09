package com.cumpleanos.core.models.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class FactorId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "FAC_UNIDAD", nullable = false)
    private Long unidad;

    @NotNull
    @Column(name = "FAC_PRODUCTO", nullable = false)
    private Long producto;

    @NotNull
    @Column(name = "FAC_EMPRESA", nullable = false)
    private Long empresa;
}
