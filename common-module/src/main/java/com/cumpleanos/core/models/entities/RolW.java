package com.cumpleanos.core.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ROL_W")
public class RolW {

    @Id
    @Column(name = "RLW_CODIGO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "RLW_ID", nullable = false, length = 25)
    private String rlwId;

    @Column(name = "RLW_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RLW_SEGURIDAD", referencedColumnName = "SEG_CODIGO")
    private Seguridad seguridad;

}