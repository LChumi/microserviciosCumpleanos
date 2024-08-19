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
@EqualsAndHashCode
@Embeddable
@ToString
public class DtipodocId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "DTP_EMPRESA", nullable = false)
    private Long empresa;

    @Column(name = "DTP_CTI_CODIGO", nullable = false)
    private Long ctiCodigo;

    @Column(name = "DTP_TPD_CODIGO", nullable = false)
    private Long tpdCodigo;
}
