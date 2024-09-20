package core.cumpleanos.models.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
public class ImpuestoId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "IMP_CODIGO", nullable = false)
    private Long impCodigo;

    @Column(name = "IMP_EMPRESA", nullable = false)
    private Long impEmpresa;
}
