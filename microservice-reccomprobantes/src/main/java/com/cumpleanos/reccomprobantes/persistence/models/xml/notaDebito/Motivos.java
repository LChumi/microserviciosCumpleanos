package com.cumpleanos.reccomprobantes.persistence.models.xml.notaDebito;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@XmlRootElement(name = "motivos")
@XmlAccessorType(XmlAccessType.FIELD)
public class Motivos {
    @XmlElement(name = "motivo")
    public List<Motivo> motivos;

}
