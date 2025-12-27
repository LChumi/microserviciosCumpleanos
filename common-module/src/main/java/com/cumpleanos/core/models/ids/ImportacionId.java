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
public class ImportacionId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "IPT_CCO_COMPROBA", nullable = false)
    private Long ccoComproba;

    @Column(name = "IPT_EMPRESA", nullable = false)
    private Long empresa;
}