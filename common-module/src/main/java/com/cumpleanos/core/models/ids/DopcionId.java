package com.cumpleanos.core.models.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class DopcionId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DOPCION_S_CODIGO")
    @NotNull
    @Column(name = "DOP_COP_CODIGO", nullable = false)
    private Long copCodigo;

    @NotNull
    @Column(name = "DOP_CODIGO", nullable = false)
    private Long codigo;
}
