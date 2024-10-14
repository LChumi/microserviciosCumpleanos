package com.cumpleanos.core.models.ids;

import jakarta.persistence.*;
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
public class ClienteId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "CLI_CODIGO", nullable = false)
    private Long codigo;

    @NotNull
    @Column(name = "CLI_EMPRESA", nullable = false)
    private Long empresa;
}
