package com.cumpleanos.reccomprobantes.models.xml.retencion;

import lombok.Data;

import java.util.List;

@Data
public class InfoAdicional {
    private List<CampoAdicional> campoAdicional;

    public String obtenerTelefono(){
        for (CampoAdicional campo: campoAdicional){
            if ("Telefono".equals(campo.getNombre())){
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
