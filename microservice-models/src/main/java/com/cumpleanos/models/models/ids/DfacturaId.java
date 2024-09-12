package com.cumpleanos.models.models.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class DfacturaId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "DFAC_SECUENCIA", nullable = false)
    private Long secuencia;

    @NotNull
    @Column(name = "DFAC_CFAC_COMPROBA", nullable = false)
    private BigInteger cfacComproba;

    @NotNull
    @Column(name = "DFAC_EMPRESA", nullable = false)
    private Long empresa;

}
