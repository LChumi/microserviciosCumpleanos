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
@Embeddable
@EqualsAndHashCode
@ToString
public class CtipocomId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "CTI_CODIGO", nullable = false)
    private Long codigo;

    @Column(name = "CTI_EMPRESA", nullable = false)
    private Long empresa;
}
