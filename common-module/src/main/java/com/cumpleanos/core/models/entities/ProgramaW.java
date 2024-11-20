package com.cumpleanos.core.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "PROGRAMA_W")
@SequenceGenerator(name = "PROGRAMA_W_S_CODIGO", sequenceName = "PROGRAMA_W_S_CODIGO", allocationSize = 1)
@ToString(exclude = { "menuWs", })
public class ProgramaW {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROGRAMA_W_S_CODIGO")
    //@Setter(AccessLevel.NONE)
    @Column(name = "PRW_CODIGO")
    private Long id;

    @Column(name = "PRW_ID", nullable = false, length = 25)
    private String prwId;

    @Column(name = "PRW_NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "PRW_PATH", length = 100)
    private String path;

    @ColumnDefault("0")
    @Column(name = "PRW_INACTIVO")
    private Boolean inactivo;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "programa")
    private List<MenuW> menuWs;
}
