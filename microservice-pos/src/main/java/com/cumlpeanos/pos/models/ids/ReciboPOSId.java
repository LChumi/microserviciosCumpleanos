package com.cumlpeanos.pos.models.ids;

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
@EqualsAndHashCode
@Embeddable
public class ReciboPOSId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "RPO_CODIGO", nullable = false)
    private Long codigo;

    @NotNull
    @Column(name = "RPO_EMPRESA", nullable = false)
    private Long empresa;
}
