package com.cumpleanos.pos.persistence.entity;

import com.cumpleanos.pos.persistence.ids.TipoCreditoPOSId;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "TIPO_CREDITO_POS")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"reciboPos"})
public class TipoCreditoPOS {

    @EmbeddedId
    private TipoCreditoPOSId id;

    @Column(name = "TCR_ID")
    private String tcrId;

    @Column(name = "TCR_NOMBRE")
    private String nombre;

    @Column(name = "TCR_INTERES")
    private Long interes;

    @OneToMany(mappedBy = "tipoCreditoPOS", fetch = FetchType.LAZY)
    @JsonBackReference // Evitar la recursion infinita
    @JsonIgnore
    private Set<ReciboPOS> reciboPos = new LinkedHashSet<>();
}
