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
public class ProductoPartidaId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "PPA_PARTIDA", nullable = false)
    private Long partida;

    @Column(name = "PPA_PRODUCTO", nullable = false)
    private Long producto;

    @Column(name = "PPA_EMPRESA", nullable = false)
    private Long empresa;
}