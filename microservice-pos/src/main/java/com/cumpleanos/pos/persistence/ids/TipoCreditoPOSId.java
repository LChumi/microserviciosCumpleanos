package com.cumpleanos.pos.persistence.ids;

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
public class TipoCreditoPOSId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "TCR_CODIGO", nullable = false)
    private Long codigo;

    @NotNull
    @Column(name = "TCR_EMPRESA", nullable = false)
    private Long empresa;
}
