package com.cumpleanos.core.models.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "PROGRAMA_W")
public class ProgramaW {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "PRW_CODIGO")
    private Long id;

    @Column(name = "PRW_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "PRW_PATH", length = 100)
    private String path;

    @ColumnDefault("0")
    @Column(name = "PRW_INACTIVO")
    private Boolean inactivo;

    @Column(name = "PRW_ID", length = 25)
    private String prwId;
}
