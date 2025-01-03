package com.cumpleanos.assist.persistence.entity;

import com.cumpleanos.core.models.entities.ProgramaW;
import com.cumpleanos.core.models.entities.Usuario;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "USUARIO_FAVORITOS", indexes = {
        @Index(name = "USUARIO_FAVORITO_NIDX01", columnList = "USF_USUARIO"),
        @Index(name = "USUARIO_FAVORITO_NIDX02", columnList = "USF_PROGRAMA")
})
@SequenceGenerator(name = "USUARIO_FAVORITOS_S_CODIGO", sequenceName = "USUARIO_FAVORITOS_S_CODIGO", allocationSize = 1)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UsuarioFavoritos {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_FAVORITOS_S_CODIGO")
    @Column(name = "USF_CODIGO", nullable = false)
    @Setter(AccessLevel.NONE)
    private Long codigo;

    @Column(name = "USF_EMPRESA", nullable = false)
    private Long empresa;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "USF_USUARIO",referencedColumnName = "USR_CODIGO", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "USF_PROGRAMA",referencedColumnName ="PRW_CODIGO" , nullable = false)
    private ProgramaW programa;

}
