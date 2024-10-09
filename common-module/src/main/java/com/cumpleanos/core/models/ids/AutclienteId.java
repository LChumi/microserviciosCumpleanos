package com.cumpleanos.core.models.ids;

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
@EqualsAndHashCode
@Embeddable
public class AutclienteId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Size(max = 3)
    @NotNull
    @Column(name = "ACL_FAC2", nullable = false, length = 3)
    private String fac2;

    @Size(max = 3)
    @NotNull
    @Column(name = "ACL_FAC1", nullable = false, length = 3)
    private String fac1;

    @NotNull
    @Column(name = "ACL_RETDATO", nullable = false)
    private Long retdato;

    @Size(max = 50)
    @NotNull
    @Column(name = "ACL_NRO_AUTORIZA", nullable = false, length = 50)
    private String nroAutoriza;

    @NotNull
    @Column(name = "ACL_CLIENTE", nullable = false)
    private Long cliente;

    @NotNull
    @Column(name = "ACL_EMPRESA", nullable = false)
    private Long empresa;

}
