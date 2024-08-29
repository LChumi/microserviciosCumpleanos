package com.cumpleanos.models.models.ids;

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
public class CuentaId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "CUE_CODIGO", nullable = false)
    private Long codigo;

    @NotNull
    @Column(name = "CUE_EMPRESA", nullable = false)
    private Long empresa;
}
