package com.cumpleanos.models.models.entities;

import com.cumpleanos.models.models.ids.AccesoId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ACCESO")
@Data
public class Acceso {

    @EmbeddedId
    private AccesoId id;


}
