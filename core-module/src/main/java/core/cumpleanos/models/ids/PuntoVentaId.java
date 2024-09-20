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
public class PuntoVentaId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "PVE_SECUENCIA", nullable = false)
    private Long secuencia;

    @NotNull
    @Column(name = "PVE_ALMACEN", nullable = false)
    private Long almacen;

    @NotNull
    @Column(name = "PVE_EMPRESA", nullable = false)
    private Long empresa;
}
