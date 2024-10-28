package com.cumpleanos.core.models.ids;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class EmpleadoId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "EMP_CODIGO", nullable = false)
    private Long codigo;

    @NotNull
    @Column(name = "EMP_EMPRESA", nullable = false)
    private Long empresa;


}
