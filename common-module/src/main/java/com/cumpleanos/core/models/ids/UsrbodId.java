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
@EqualsAndHashCode
@Embeddable
@ToString
public class UsrbodId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "UBO_BODEGA", nullable = false)
    private Long bodega;

    @Column(name = "UBO_USUARIO", nullable = false)
    private Long usuario;

    @Column(name = "UBO_EMPRESA", nullable = false)
    private Long empresa;
}
