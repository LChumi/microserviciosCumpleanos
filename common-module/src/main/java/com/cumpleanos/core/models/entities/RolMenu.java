package com.cumpleanos.core.models.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ROL_MENU")
@SequenceGenerator(name = "ROL_MENU_S_CODIGO", sequenceName = "ROL_MENU_S_CODIGO", allocationSize = 1)
public class RolMenu {

    @Id
    @Column(name = "RLM_CODIGO")
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROL_MENU_S_CODIGO")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "RLM_ROL_W", referencedColumnName = "RLW_CODIGO")
    private RolW rolW;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "RLM_MENU_W")
    private MenuW menuW;
}
