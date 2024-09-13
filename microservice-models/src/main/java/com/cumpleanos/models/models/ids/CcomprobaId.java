package com.cumpleanos.models.models.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class CcomprobaId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "CCO_CODIGO", nullable = false)
    private BigInteger codigo;

    @NotNull
    @Column(name = "CCO_EMPRESA", nullable = false)
    private Long empresa;
}
