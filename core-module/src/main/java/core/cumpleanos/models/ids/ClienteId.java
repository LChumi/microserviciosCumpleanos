package core.cumpleanos.models.ids;

import jakarta.persistence.*;
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
public class ClienteId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENTE_S_CODIGO")
    @NotNull
    @Column(name = "CLI_CODIGO", nullable = false)
    private Long codigo;

    @NotNull
    @Column(name = "CLI_EMPRESA", nullable = false)
    private Long empresa;
}
