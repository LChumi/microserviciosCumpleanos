package com.cumpleanos.pos.persistence.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@Embeddable
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FinancieraId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "FIN_CODIGO", nullable = false)
    private Long codigo;

    @Column(name = "FIN_EMPRESA", nullable = false)
    private Long empresa;
}
