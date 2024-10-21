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
public class TablaCoaId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @NotNull
    @Column(name = "TAB_EMPRESA", nullable = false)
    private Long empresa;

    @NotNull
    @Column(name = "TAB_CODIGO", nullable = false)
    private Long codigo;
}
