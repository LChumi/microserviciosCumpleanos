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
@EqualsAndHashCode
@Embeddable
public class AccesoId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "ACC_EMPRESA", nullable = false)
    private Long empresa;

    @NotNull
    @Column(name = "ACC_USUARIO", nullable = false)
    private Long usuario;

    @NotNull
    @Column(name = "ACC_ALMACEN", nullable = false)
    private Long accAlmacen;
}
