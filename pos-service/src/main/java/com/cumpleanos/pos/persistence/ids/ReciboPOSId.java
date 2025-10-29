package com.cumpleanos.pos.persistence.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@Embeddable
@ToString
@NoArgsConstructor
@AllArgsConstructor
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
