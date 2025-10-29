package com.cumpleanos.reccomprobantes.persistence.models.xml.notaDebito;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Motivo {
    public String razon;
    public String valor;
}
