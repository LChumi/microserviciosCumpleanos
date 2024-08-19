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
@Table(name = "ACCESO_ROL")
@ToString(exclude = {})
@SequenceGenerator(name = "ACCESO_ROL_S_CODIGO", sequenceName = "ACCESO_ROL_S_CODIGO", allocationSize = 1)
public class AccesoRol {

    @Id
    @Setter(AccessLevel.NONE)
    @Column(name = "ACR_CODIGO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCESO_ROL_S_CODIGO")
    private Long id;

    @Column(name = "ACR_EMPRESA", nullable = false)
    private Long empresa;

    @Column(name = "ACR_ALMACEN", nullable = false)
    private Long almacen;

    @Column(name = "ACR_USUARIO", nullable = false)
    private Long usuario;

    @Column(name = "ACR_ORDEN")
    private Long orden;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ACR_ROL_W", referencedColumnName = "RLW_CODIGO")
    private RolW rolW;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ACR_EMPRESA", referencedColumnName = "SIS_CODIGO", insertable = false, updatable = false)
    private Sistema sistema;

}
