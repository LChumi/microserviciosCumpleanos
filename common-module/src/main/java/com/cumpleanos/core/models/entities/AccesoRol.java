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
public class AccesoRol {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACR_CODIGO")
    private Long id;

    @Column(name = "ACR_EMPRESA", nullable = false)
    private Long empresa;

    @Column(name = "ACR_ALMACEN", nullable = false)
    private Long almacen;

    @Column(name = "ACR_USUARIO", nullable = false)
    private Long usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ACR_ROL_W", referencedColumnName = "RLW_CODIGO")
    private RolW rolW;

}
