package com.cumpleanos.core.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "MENU_W")
@ToString(exclude = {"menuW"})
@SequenceGenerator(name = "MENU_W_S_CODIGO", sequenceName = "MENU_W_S_CODIGO", allocationSize = 1)
public class MenuW {

    @Id
    @Column(name = "MNW_CODIGO")
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MENU_W_S_CODIGO")
    private Long id;

    @Column(name = "MNW_ID", nullable = false, length = 50)
    private String mnwId;

    @ColumnDefault("null")
    @Column(name = "MNW_INACTIVO")
    private Boolean inactivo;

    @Column(name = "MNW_NOMBRE", length = 100)
    private String nombre;

    @Column(name = "MNW_ICONO", length = 100)
    private String icono;

    @Column(name = "MNW_REPORTA")
    private Long reporta;

    @ManyToOne(fetch = FetchType.EAGER  )
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "MNW_PROGRAMA", referencedColumnName = "PRW_CODIGO")
    private ProgramaW programa;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "MNW_SEGURIDAD", referencedColumnName = "SEG_CODIGO")
    private Seguridad seguridad;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "MNW_REPORTA", referencedColumnName = "MNW_CODIGO", insertable = false, updatable = false)
    private MenuW menuW;

    @JsonBackReference
    @OneToMany(mappedBy = "menuW")
    private List<MenuW> menuWs;

    @JsonBackReference
    @OneToMany(mappedBy = "menuW")
    private List<RolMenu> rolMenus;
}
