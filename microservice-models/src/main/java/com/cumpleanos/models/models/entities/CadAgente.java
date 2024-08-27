package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.CadAgenteId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "CADAGENTE")
public class CadAgente implements Serializable {

    @EmbeddedId
    private CadAgenteId id;
}
