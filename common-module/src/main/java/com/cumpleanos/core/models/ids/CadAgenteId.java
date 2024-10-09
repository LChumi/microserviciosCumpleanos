package com.cumpleanos.core.models.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class CadAgenteId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CADAGENTE_S_CODIGO")
    @NotNull
    @Column(name = "CAD_CODIGO", nullable = false)
    private Long cadCodigo;

    @NotNull
    @Column(name = "CAD_EMPRESA", nullable = false)
    private Long cadEmpresa;
}
