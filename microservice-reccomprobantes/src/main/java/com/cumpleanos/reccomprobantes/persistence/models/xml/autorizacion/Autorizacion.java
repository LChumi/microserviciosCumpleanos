package com.cumpleanos.reccomprobantes.persistence.models.xml.autorizacion;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlRootElement(name = "autorizacion")
@XmlAccessorType(XmlAccessType.FIELD)
public class Autorizacion {
    @XmlElement(name = "estado")
    private String estado;
    @XmlElement(name = "numeroAutorizacion")
    private String numeroAutorizacion;
    @XmlElement(name = "fechaAutorizacion")
    private String fechaAutorizacion;
    @XmlElement(name = "ambiente")
    private String ambiente;
    @XmlElement(name = "comprobante")
    private String comprobante;
}
