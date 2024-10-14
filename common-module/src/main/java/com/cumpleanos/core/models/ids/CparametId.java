package com.cumpleanos.core.models.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class CparametId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "CPA_CODIGO", nullable = false)
    private Long codigo;

    @Column(name = "CPA_EMPRESA", nullable = false)
    private Long empresa;
}
