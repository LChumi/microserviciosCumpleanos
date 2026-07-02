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
public class DmovinvId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "DMO_SECUENCIA", nullable = false)
    private Long secuencia;

    @Column(name = "DMO_CMO_COMPROBA", nullable = false)
    private Long cmoComproba;

    @Column(name = "DMO_EMPRESA", nullable = false)
    private Long empresa;
}
