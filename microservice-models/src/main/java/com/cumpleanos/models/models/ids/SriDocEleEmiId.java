package com.cumpleanos.models.models.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class SriDocEleEmiId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Size(max = 50)
    @NotNull
    @Column(name = "SRI_NUMERO_AUTORIZACION", nullable = false, length = 50)
    private String numeroAutorizacion;

    @NotNull
    @Column(name = "SRI_EMPRESA", nullable = false)
    private Long empresa;
}
