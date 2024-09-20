package core.cumpleanos.models.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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
public class GproductoId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "GPR_EMPRESA", nullable = false)
    private Long empresa;

    @NotNull
    @Column(name = "GPR_CODIGO", nullable = false)
    private Long codigo;
}
