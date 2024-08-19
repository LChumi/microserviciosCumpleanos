package com.cumpleanos.reccomprobantes.models.xml.notaCredito;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class InfoAdicional {
    @XmlElement(name = "campoAdicional")
    private List<CampoAdicional> campoAdicional;

    public String obtenerTelefono(){
        for (CampoAdicional campo: campoAdicional){
            if ("Telefono".equals(campo.getNombre())){
                return campo.getValor();
            }
        }
        return null;
    }
    public String obtenerVendedor(){
        for (CampoAdicional campo: campoAdicional){
            if ("Vendedor".equals(campo.getNombre())){
                return campo.getValor();
            }
        }
        return null;
    }
    public String obtenerFpago(){
        for (CampoAdicional campo: campoAdicional){
            if ("Fpago".equals(campo.getNombre())){
                return campo.getValor();
            }
        }
        return null;
    }
    public String obtenerClavecliente(){
        for (CampoAdicional campo: campoAdicional){
            if ("Clavecliente".equals(campo.getNombre())){
                return campo.getValor();
            }
        }
        return null;
    }
    public String obtenerTransporte(){
        for (CampoAdicional campo: campoAdicional){
            if ("Transporte".equals(campo.getNombre())){
                return campo.getValor();
            }
        }
        return null;
    }
    public String obtenerObservacion(){
        for (CampoAdicional campo: campoAdicional){
            if ("Observacion".equals(campo.getNombre())){
                return campo.getValor();
            }
        }
        return null;
    }
    public String obtenerDireccion(){
        for (CampoAdicional campo: campoAdicional){
            if ("Direccion".equals(campo.getNombre())){
                return campo.getValor();
            }
        }
        return null;
    }
    public String obtenerCiudad(){
        for (CampoAdicional campo: campoAdicional){
            if ("Ciudad".equals(campo.getNombre())){
                return campo.getValor();
            }
        }
        return null;
    }

}
