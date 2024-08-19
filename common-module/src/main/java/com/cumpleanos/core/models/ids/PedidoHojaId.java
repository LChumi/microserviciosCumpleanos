package com.cumpleanos.core.models.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
@ToString
public class PedidoHojaId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "PHO_EMPRESA", nullable = false)
    private Long empresa;

    @Column(name = "PHO_CCO_COMPROBA", nullable = false)
    private BigInteger ccoComproba;

    @Column(name = "PHO_HOJA", nullable = false)
    private Long hoja;
}
