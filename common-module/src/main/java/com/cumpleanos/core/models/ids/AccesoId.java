package com.cumpleanos.core.models.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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
@Embeddable
@ToString
public class AccesoId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "ACC_USUARIO", nullable = false)
    private Long usuario;

    @NotNull
    @Column(name = "ACC_EMPRESA", nullable = false)
    private Long empresa;

    @NotNull
    @Column(name = "ACC_ALMACEN", nullable = false)
    private Long almacen;
}
