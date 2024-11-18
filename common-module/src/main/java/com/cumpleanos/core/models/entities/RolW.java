package com.cumpleanos.core.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ROL_W")
@SequenceGenerator(name = "ROL_W_S_CODIGO", sequenceName = "ROL_W_S_CODIGO", allocationSize = 1)
public class RolW {

    @Id
    @Column(name = "RLW_CODIGO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROL_W_S_CODIGO")
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "RLW_ID", nullable = false, length = 25)
    private String rlwId;

    @Column(name = "RLW_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RLW_SEGURIDAD", referencedColumnName = "SEG_CODIGO")
    private Seguridad seguridad;

    @JsonBackReference
    @OneToMany(mappedBy = "rolW")
    private List<AccesoRol> accesosRol;

    @JsonBackReference
    @OneToMany(mappedBy = "rolW")
    private List<RolMenu> rolMenus;
}