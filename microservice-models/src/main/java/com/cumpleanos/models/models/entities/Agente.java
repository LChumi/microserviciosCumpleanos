package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.AgenteId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "AGENTE")
@Data
public class Agente {

    @EmbeddedId
    private AgenteId id;

}
