package com.cumpleanos.core.models.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
@ToString
public class AutclienteId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "ACL_EMPRESA", nullable = false)
    private Long empresa;

    @NotNull
    @Column(name = "ACL_CLIENTE", nullable = false)
    private Long cliente;

    @Size(max = 50)
    @NotNull
    @Column(name = "ACL_NRO_AUTORIZA", nullable = false, length = 50)
    private String nroAutoriza;

    @Size(max = 3)
    @NotNull
    @Column(name = "ACL_FAC1", nullable = false, length = 3)
    private String fac1;

    @Size(max = 3)
    @NotNull
    @Column(name = "ACL_FAC2", nullable = false, length = 3)
    private String fac2;

    @NotNull
    @Column(name = "ACL_RETDATO", nullable = false)
    private Long retdato;
}
