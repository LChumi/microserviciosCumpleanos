package com.cumpleanos.core.models.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Embeddable
@EqualsAndHashCode
public class StockOptimoId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "STO_CODIGO", nullable = false)
    private Long codigo;

    @Column(name = "STO_EMPRESA", nullable = false)
    private Long empresa;
}
