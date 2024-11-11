package com.cumpleanos.core.models.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "MENU_W")
@ToString(exclude = {"menuW"})
public class MenuW {

    @Id
    @Column(name = "MNW_CODIGO")
    @Setter(AccessLevel.NONE)
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
}
