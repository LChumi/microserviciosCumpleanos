package com.cumpleanos.core.models.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
@ToString
public class ProductoId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "PRO_EMPRESA", nullable = false)
    private Long empresa;

    @NotNull
    @Column(name = "PRO_CODIGO", nullable = false)
    private Long codigo;
}
