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
public class ImporitemId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "IIT_SECUENCIA", nullable = false)
    private Long iitSecuencia;

    @Column(name = "IIT_IMP_COMPROBA", nullable = false)
    private Long iitImpComproba;

    @Column(name = "IIT_EMPRESA", nullable = false)
    private Long iitEmpresa;
}
