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
public class RetDatoId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "RTD_CODIGO", nullable = false)
    private Long codigo;

    @NotNull
    @Column(name = "RTD_TABLACOA", nullable = false)
    private Long tablacoa;

    @NotNull
    @Column(name = "RTD_EMPRESA", nullable = false)
    private Long empresa;
}
